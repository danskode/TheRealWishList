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