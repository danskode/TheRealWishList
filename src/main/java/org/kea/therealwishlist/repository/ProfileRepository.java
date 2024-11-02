package org.kea.therealwishlist.repository;

import org.kea.therealwishlist.model.Profile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class ProfileRepository {

    // Her hentes variable fra den aktive profil angivet i application.properties (prod eller test) ...
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    //Denne kode gør, at man ikke kan bruge de forskellige profiler prod og test til forbindelsen ...
    //final private String url = System.getenv("DB_URL");
    //final private String user = System.getenv("DB_USER");
    //final private String password = System.getenv("DB_PASSWORD");


    // Metode til at oprette en ny profil
    public void createProfile(Profile profile) {
        String sql = "INSERT INTO `user` (user_name, user_password) VALUES (?, ?)";

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