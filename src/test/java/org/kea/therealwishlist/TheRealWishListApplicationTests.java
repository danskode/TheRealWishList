package org.kea.therealwishlist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class TheRealWishListApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void verifyTableExists() {
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM wish", Integer.class);
        System.out.println("Number of records in WISH table: " + count);
    }

}
