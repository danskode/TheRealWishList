package org.kea.therealwishlist.repository;

import org.kea.therealwishlist.model.Wish;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class WishRepository {

    final private String url = System.getenv("DB_URL");
    final private String user = System.getenv("DB_USER");
    final private String password = System.getenv("DB_PASSWORD");

    public List<Wish> findAll() {
        List<Wish> wishes = new ArrayList<>();
        String sql = "SELECT * FROM wish";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
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

}
