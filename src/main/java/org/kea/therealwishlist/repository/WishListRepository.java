package org.kea.therealwishlist.repository;

import org.kea.therealwishlist.model.Wish;
import org.kea.therealwishlist.model.WishList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishListRepository {

    private JdbcTemplate jdbcTemplate;

    // Injecter JdbcTemplate i konstruktøren
    public WishListRepository(org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Metode til at oprette en ny ønskeliste i databasen ...
    public String createWishList(String wishListName, int userID){
        String sql = "INSERT INTO wish_list (list_name, `user_id`) VALUES (?, ?)";
        jdbcTemplate.update(sql, wishListName, userID);
        return wishListName + " er blevet oprettet med succes.";
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


    // Metode til at hente en ønskeliste med udgangspunkt i et konkret ønskelistenavn ... fx fra Pathvariable ...
    public WishList getWishList(String wishListName){
        String sql = "SELECT * FROM wish_list WHERE list_name = ?";

        // Definerer en RowMapper for at mappe resultaterne til WishList objekter
        RowMapper<WishList> rowMapper = (resultSet, rowNum) -> new WishList(
                resultSet.getInt("id"),
                resultSet.getInt("user_id"),
                resultSet.getString("list_name")
        );

        // Brug JdbcTemplate til at udføre forespørgslen og returnere ønskelisten ...
        return jdbcTemplate.queryForObject(sql, rowMapper, wishListName);
    }

    // Metode til at hente en ønskeliste med udgangspunkt i et konkret ønskelistenavn og et brugernavn ... fx fra Pathvariable ...
    public WishList getWishList(String wishListName, int userID){
        String sql = "SELECT * FROM wish_list WHERE list_name = ? AND user_id = ?";

        // Definerer en RowMapper for at mappe resultaterne til WishList objekter
        RowMapper<WishList> rowMapper = (resultSet, rowNum) -> new WishList(
                resultSet.getInt("id"),
                resultSet.getInt("user_id"),
                resultSet.getString("list_name")
        );

        // Brug JdbcTemplate til at udføre forespørgslen og returnere listen over wishes
        return jdbcTemplate.queryForObject(sql, rowMapper, wishListName);
    }
}
