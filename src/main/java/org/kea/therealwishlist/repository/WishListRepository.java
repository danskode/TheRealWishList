package org.kea.therealwishlist.repository;

import org.kea.therealwishlist.model.Wish;
import org.kea.therealwishlist.model.WishList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class WishListRepository {

    private final JdbcTemplate jdbcTemplate;

    // Injicer JdbcTemplate i konstruktøren ...
    public WishListRepository(org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Metode til at oprette en ny ønskeliste i databasen ...
    public void createWishList(String wishListName, int userID){
        String sql = "INSERT INTO wish_list (list_name, `user_id`) VALUES (?, ?)";
        jdbcTemplate.update(sql, wishListName, userID);
        // return wishListName + " er blevet oprettet med succes.";
    }

    // Metode til at hente alle ønskelister fra en specifik bruger (userID) ...
    public List<WishList> getAllWishListsFromUserID(int userID){
        String sql = "SELECT * FROM wish_list WHERE `user_id` = ?";

        // Definerer en RowMapper for at mappe resultaterne til WishList objekter
        RowMapper<WishList> rowMapper = (resultSet, rowNum) -> new WishList(
                resultSet.getInt("id"),
                resultSet.getInt("user_id"),
                resultSet.getString("list_name")
        );

        // Brug JdbcTemplate til at udføre forespørgslen og returnere listen over wishlists
        return jdbcTemplate.query(sql, rowMapper, userID);
    }

    // Hent en specifik ønskeliste. Listen skal tilhøre brugeren, der er logget ind ...
    public WishList getWishListFromWishListIDAndUserID(int wishListID, int userID) {

        String sql = "SELECT wl.id AS wishlist_id, wl.user_id, wl.list_name, w.id AS wish_id, w.wish_name, w.wish_url, w.wish_price, w.wish_reserved FROM wish_list wl LEFT JOIN wish w ON wl.id = w.wish_list_id WHERE wl.id = ? AND wl.user_id = ? ORDER BY w.id";

        return jdbcTemplate.query(sql, new Object[]{wishListID, userID}, rs -> {
        //return jdbcTemplate.query(sql, new Object[]{wishListID, userID}, rs -> {
            WishList wishList = null;
            List<Wish> wishes = new ArrayList<>();

            while (rs.next()) {
                if (wishList == null) {
                    // Opretter WishList én gang
                    wishList = new WishList(
                            rs.getInt("wishlist_id"),
                            rs.getInt("wl.user_id"),
                            rs.getString("wl.list_name"),
                            wishes  // Initialiserer listen
                    );
                }

                // Henter wish-data og tilføjer det til wishes-listen, hvis de findes
                int wishId = rs.getInt("wish_id");
                if (wishId > 0) { // Sikrer, at der er en tilknyttet wish
                    Wish wish = new Wish(

                            // int wishID, String wishName, String url, float price, boolean reserved

                            wishId,
                            rs.getString("w.wish_name"),
                            rs.getString("w.wish_url"),
                            rs.getFloat("w.wish_price"),
                            rs.getBoolean("w.wish_reserved")
                    );
                    wishes.add(wish);
                }
            }

            return wishList;
        });
    }

    // metode til at slette en wishlist ...
    public void deleteWishListFromWishListID(int wishListID) {

        String sql = "DELETE FROM wish WHERE wish_list_id = ?";
        jdbcTemplate.update(sql, wishListID);
        String sql2 = "DELETE FROM wish_list WHERE id = ?";
        jdbcTemplate.update(sql2, wishListID);
    }

    public void updateWishList(WishList wishList) {

        String newName = wishList.getWishListName();
        int wishlstID = wishList.getId();

        String sql = "UPDATE wish_list SET list_name = ? WHERE id = ?";
        jdbcTemplate.update(sql, newName, wishlstID);
    }

    // Hent en enkel ønskeliste med kun et ID ...
    public WishList getWishListById(int wishListID) {

        String sql = "SELECT wl.id AS wishlist_id, wl.user_id, wl.list_name, w.id AS wish_id, w.wish_name, w.wish_url, w.wish_price, w.wish_reserved FROM wish_list wl LEFT JOIN wish w ON wl.id = w.wish_list_id WHERE wl.id = ? ORDER BY w.id";

        //"SELECT * FROM wish_list wl LEFT JOIN wish w ON wl.id=w.wish_list_id WHERE wl.id = ?"; // LEFT JOIN for at sikre, at også lister uden ønsker kommer med


        return jdbcTemplate.query(sql, new Object[]{wishListID}, rs -> {
            //return jdbcTemplate.query(sql, new Object[]{wishListID, userID}, rs -> {
            WishList wishList = null;
            List<Wish> wishes = new ArrayList<>();

            while (rs.next()) {
                if (wishList == null) {
                    // Opretter WishList én gang
                    wishList = new WishList(
                            rs.getInt("wishlist_id"),
                            rs.getInt("wl.user_id"),
                            rs.getString("wl.list_name"),
                            wishes  // Initialiserer listen
                    );
                }

                // Henter wish-data og tilføjer det til wishes-listen, hvis de findes
                int wishId = rs.getInt("wish_id");
                if (wishId > 0) { // Sikrer, at der er en tilknyttet wish
                    Wish wish = new Wish(

                            // int wishID, String wishName, String url, float price, boolean reserved

                            wishId,
                            rs.getString("w.wish_name"),
                            rs.getString("w.wish_url"),
                            rs.getFloat("w.wish_price"),
                            rs.getBoolean("w.wish_reserved")
                    );
                    wishes.add(wish);
                }
            }

            return wishList;
        });
    }

    // Til post-delen: opdater i databasen med nyt navn hvor ID passer ...
    public void updateWishList(int wishListId, String wishListName) {
        String sql = "UPDATE wish_list SET list_name = ? WHERE id = ?";
        jdbcTemplate.update(sql, wishListName, wishListId);
    }

    // metode til at hente navnet på en anden bruger ud fra et ønskelisteid ...
    public String getWishListOwnersName(int wishListID) {
        int userID = getWishListById(wishListID).getUserId();
        String sql = "SELECT user_name FROM `user` WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userID}, String.class);
    }

    // Metode til at hente alle andres ønskelister end ens egne ...
    public List<WishList> getAllOtherWishLists(int userID) {
        String sql="SELECT * FROM wish_list WHERE user_id != ?";

        // Definerer en RowMapper for at mappe resultaterne til WishList objekter
        RowMapper<WishList> rowMapper = (resultSet, rowNum) -> new WishList(
                resultSet.getInt("id"),
                resultSet.getInt("user_id"),
                resultSet.getString("list_name")
        );

        // Brug JdbcTemplate til at udføre forespørgslen og returnere listen over wishlists
        return jdbcTemplate.query(sql, rowMapper, userID);


    }

    // Metode til at hente en ønskelistes id, hvis man kun har et ønskes id ...
    public int getWishListIdFromWishId(int wishId) {
        String sql="SELECT wish_list_id FROM wish WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{wishId}, Integer.class);
    }
}
