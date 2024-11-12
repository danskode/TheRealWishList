package org.kea.therealwishlist.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kea.therealwishlist.model.Wish;
import org.kea.therealwishlist.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@TestConfiguration
@ExtendWith(SpringExtension.class)
@SpringBootTest
// Her vælger vi test-profilen (application-test.properties, så H2 bruges med de tilhørende data
@ActiveProfiles("test")
public class WishRepositoryTest {

    @Autowired
    private WishRepository wishRepository;
    @Autowired
    private WishService wishService;
    @Autowired
    private JdbcTemplate jdbcTemplate; // Så vi kan sende SQL forespørgsler direkte til db


//    Der er fejl i testen her – H2 mangler en user_id i tabellen user, siger fejlkoden ...
//    @Test
//    void findAllByUserID() {
//        List<Wish> wishes = wishRepository.findAllByUserID(1);
//
//    }

    @Test
    void createWish() {
        Wish wish = new Wish();
        wish.setWishName("Test");
        wish.setUrl("https://www.google.com");
        wish.setPrice(200);
        wish.setWishListID(1);

        wishService.getCreateWish(wish);

        Wish storedWish = jdbcTemplate.queryForObject(
                "SELECT * FROM wish WHERE wish_name = ?",
                (rs, rowNum) -> {
                    Wish w = new Wish();
                    w.setWishName(rs.getString("wish_name"));
                    w.setUrl(rs.getString("wish_url"));
                    w.setPrice(rs.getInt("wish_price"));
                    w.setWishListID(rs.getInt("wish_list_id"));
                    return w;
                },
                "Test");
        System.out.println("Stored wish: Name=" + storedWish.getWishName() +
                ", URL=" + storedWish.getUrl() +
                ", Price=" + storedWish.getPrice() +
                ", WishListID=" + storedWish.getWishListID());
    }


    @Test
    void findWishByID() {
    }

    @Test
    void updateWish() {
    }
}