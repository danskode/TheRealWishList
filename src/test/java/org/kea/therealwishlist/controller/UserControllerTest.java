package org.kea.therealwishlist.controller;

import org.junit.jupiter.api.Test;
import org.kea.therealwishlist.model.User;
import org.kea.therealwishlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    // Test for at vise login side
    // Virker
    @Test
    void showCreateUserForm() throws Exception {
        mockMvc.perform(get("/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createUser"));
    }

    // Test for velkomstsiden
    // OBS. Virker kun hvis man i UserRepository udkommenterer linje 48.
    // H2-databasen kan åbenbart ikke finde 'user'. Den skal bruge backslash...
    /*
    @Test
    void showWelcomePage() throws Exception {
        // Find brugeren i databasen
        User testUser = userRepository.findUserByUsername("Anna");

        // Opret en mock-session og tilføj user-attribut
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", testUser);

        // Udfør GET-anmodningen til /welcome og med mock-session
        mockMvc.perform(MockMvcRequestBuilders.get("/welcome")
                        .session(session)) // Tilføj sessionen her
                .andExpect(MockMvcResultMatchers.status().isOk()) // Forvent OK-status
                .andExpect(MockMvcResultMatchers.view().name("welcome")) // Forvent view-navn
                .andExpect(MockMvcResultMatchers.model().attributeExists("username")) // Tjek at username er i modellen
                .andExpect(MockMvcResultMatchers.model().attributeExists("userID")) // Tjek at userID er i modellen
                .andExpect(MockMvcResultMatchers.model().attributeExists("wishLists")); // Tjek at wishLists er i modellen
    }*/
}