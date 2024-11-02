package org.kea.therealwishlist.repository;

import org.kea.therealwishlist.model.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class WishRepository {

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
}