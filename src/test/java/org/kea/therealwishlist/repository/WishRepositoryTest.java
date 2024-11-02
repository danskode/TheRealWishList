package org.kea.therealwishlist.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kea.therealwishlist.model.Wish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
// Her vælger vi test-profilen (application-test.properties, så H2 bruges med de tilhørende data
@ActiveProfiles("test")
public class WishRepositoryTest {

    @Autowired
    private WishRepository wishRepository;

    @Test
    public void testFindAllHasWishes() {
        // Henter alle wishes
        List<Wish> wishes = wishRepository.findAll();

        // Simpel test for at tjekke op imod data.sql (H2-databasens wish-tabel
        Assertions.assertEquals(5, wishes.size(), "Der burde være 5 ønsker i databasen");
    }
}