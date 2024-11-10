package org.kea.therealwishlist.repository;

import org.kea.therealwishlist.model.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    // Ved at bruge JdbcTemplate slipper du for at angive url, user, og password direkte i koden, da Spring Boot henter disse fra de korrekte application-filer baseret på den aktive profil.
    private final JdbcTemplate jdbcTemplate; // Bruges til at udføre SQL-spørgsmål til databasen

    // En konstruktør til at initialisere jdbcTemplate, når UserRepository oprettes
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Metode til at oprette en ny bruger i db
    public void createUser(User user) {
        //String sql = "INSERT INTO \"user\" (user_name, user_password) VALUES (?, ?)";
        String sql = "INSERT INTO `user` (user_name, user_password) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getUserName(), user.getUserPassword());
    }


    // Metode til at logge en bruger ind
    // User - er returtypen for metoden! Så metoden vil returnere et objekt af typen User
    public User loginUser(String username, String password) { //Brugeren indtaster username og password
        //String sql = "SELECT * FROM \"user\" WHERE `user_name` = ? AND `user_password` = ?"; <-- \"user\" virker ikke med MySQL, der skal det være --> `user`
        String sql = "SELECT * FROM `user` WHERE `user_name` = ? AND `user_password` = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username, password}, // Forsøger at finde en bruger med de matchende oplysninger
                    (rs, rowNum) -> new User(
                            rs.getInt("id"),
                            rs.getString("user_name"), // henter brugernavn fra db og sætter det på User-objektet
                            rs.getString("user_password") // henter kodeord fra db og sætter det på User-objektet
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return null; // hvis ingen bruger matcher oplysningerne returneres null
        }

    }

    public User findUserByUsername(String username) {
        // String sql = "SELECT * FROM \"user\" WHERE `user_name` = ?";
        String sql = "SELECT * FROM `user` WHERE `user_name` = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{username},
                    (rs, rowNum) -> {
                        User user = new User(
                                rs.getString("user_name"),  // Hent user_name
                                rs.getString("user_password")  // Hent user_password
                        );
                        user.setUserID(rs.getInt("id"));  // Hent user_id og sæt det på objektet
                        return user;
                    }
            );
        } catch (EmptyResultDataAccessException e) {
            return null;  // Hvis ingen bruger findes med dette brugernavn, returneres null
        }
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