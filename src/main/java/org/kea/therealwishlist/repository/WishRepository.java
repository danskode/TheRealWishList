package org.kea.therealwishlist.repository;

import org.kea.therealwishlist.model.Wish;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class WishRepository {

    private final JdbcTemplate jdbcTemplate;

    // Injecter JdbcTemplate i konstruktøren
    public WishRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Wish> findAllByUserID(int userID) {
        String sql = "SELECT * FROM wish WHERE user_id = ?";

        // Definerer en RowMapper for at mappe resultaterne til Wish objekter
        RowMapper<Wish> rowMapper = (resultSet, rowNum) -> new Wish(
                resultSet.getString("wish_name"),
                resultSet.getString("wish_url"),
                resultSet.getFloat("wish_price"),
                resultSet.getBoolean("wish_reserved")
        );

        // Brug JdbcTemplate til at udføre forespørgslen og returnere listen over wishes
        return jdbcTemplate.query(sql, rowMapper);
    }

    public void createWish(Wish wish){
        String sql = "INSERT INTO wish (wish_name, wish_url, wish_price, wish_reserved, wish_list_id) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                wish.getWishName(),
                wish.getUrl(),
                wish.getPrice(),
                wish.isReserved(),
                wish.getWishListID()
        );
    }

    public Wish findWishByID(int wishID){
        String sql = "SELECT * FROM wish WHERE id = ?";
        RowMapper<Wish> rowMapper = (resultSet, rowNum) -> new Wish(
                resultSet.getInt("id"),
                resultSet.getString("wish_name"),
                resultSet.getString("wish_url"),
                resultSet.getFloat("wish_price"),
                resultSet.getBoolean("wish_reserved")
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, wishID);
    }

    public Wish updateWish(Wish wish) {
        System.out.println("Attempting to update wish with ID: " + wish.getWishID());
        System.out.println("Nye navn: " + wish.getWishName());
        System.out.println("ny pris: " + wish.getPrice());
        System.out.println("den nye URL: " + wish.getUrl());

        String sql = "UPDATE wish SET wish_name = ?, wish_url = ?, wish_price = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, wish.getWishName(), wish.getUrl(), wish.getPrice(), wish.getWishID());
        System.out.println("Rows affected: " + rowsAffected);

        return wish;
    }

    public void deleteWish(int wishID) {
        String sql = "DELETE FROM wish WHERE id = ?";
        jdbcTemplate.update(sql, wishID);
    }

    public void createReservation(int wishId) {
        String sql="UPDATE wish SET wish_reserved = 1 WHERE id = ?";
        jdbcTemplate.update(sql, wishId);
    }
}



/* Patrick har sagt at vi godt må gøre brug af jdbcTemplate, derfor har vi udkommenteret vores oprindelige kode som gjorde burg af DataSource'
//
    //DataSource gør det muligt for os at teste med en test-profil (application-test.properties), så hentes miljøvariablene fra denne eller prod automatisk
    private final DataSource dataSource;

    @Autowired
    public WishRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Wish> findAll() {
        List<Wish> wishes = new ArrayList<>();
        String sql = "SELECT * FROM wish";

        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Wish wish = new Wish(
                        resultSet.getString("wish_name"),
                        resultSet.getString("wish_url"),
                        resultSet.getFloat("wish_price"),
                        resultSet.getBoolean("wish_reserved"));
                wishes.add(wish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wishes;
    }
}*/