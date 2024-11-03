package org.kea.therealwishlist.repository;

import org.kea.therealwishlist.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

// Ved at bruge JdbcTemplate slipper du for at angive url, user, og password direkte i koden, da Spring Boot henter disse fra de korrekte application-filer baseret på den aktive profil.
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createUser(User user) {
        String sql = "INSERT INTO `user` (user_name, user_password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getUserName(), user.getUserPassword());
    }
}



 /* Patrick har sagt at vi godt må gøre brug af jdbcTemplate, derfor har vi udkommenteret vores oprindelige kode'

    //final private String url = System.getenv("DB_URL");
    //final private String user = System.getenv("DB_USER");
    //final private String password = System.getenv("DB_PASSWORD");

    // Metode til at oprette en ny profil
    public void createProfile(Profile profile) {
        String sql = "INSERT INTO `user` (user_name, user_password) VALUES (?, ?)";

        // Vi må godt bare gøre brug af "jdbc.Template"
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, profile.getProfileName());
            statement.setString(2, profile.getProfilePassword());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
*/