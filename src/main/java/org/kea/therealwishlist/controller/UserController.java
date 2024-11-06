package org.kea.therealwishlist.controller;

import org.kea.therealwishlist.model.User;
import org.kea.therealwishlist.repository.UserRepository;
import org.kea.therealwishlist.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    // Viser create profile formularen - her opretter man sig
    @GetMapping("/create")
    public String showCreateUserForm(Model model) {
        model.addAttribute("user", new User());
        return "createUser";
    }


    // En ny instans af user/profile bliver oprettet
    @PostMapping("/save")
    public String createUser(@ModelAttribute ("user") User user, Model model) { //"user" binder dataene fra en HTML-formular til et Profile-objekt
        userService.createUser(user); // kalder create metoden i Service

        model.addAttribute("message", "User succesfully created! Happy wishing!");

        return "test";// omdirigeres til en liste over sine ønskelister - opret ønskeliste??
    }

    // Viser login-siden / vores forside
    @GetMapping("")
    public String showLoginForm(Model model) {
        // Et tomt User-objekt tilføjet til modellen, som kan bruges i formularen
        model.addAttribute("user", new User());
        return "login";
    }

    // Når brugeren indsender loginformularen, tjekker denne metode brugernavn og adgangskode
    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username, // brugernavn indtastet af brugeren
                            @RequestParam("password") String password, // kodeord indtastet af brugeren
                            Model model) { // Model-objektet bruges til at sende data til HTML-siden

        // Kalder loginUser fra userService og forsøger at logge ind
        User user = userService.loginUser(username, password);

        // Tjekker om brugeren blev fundet
        if (user != null) {
            model.addAttribute("user", user);
            return "test"; // her kan brugeren se sine ønskelister (NICOLAI)
        } else {
            model.addAttribute("error", "Forkert brugernavn eller adgangskode!");
            return "login"; // returnerer login-siden med fejlbesked
        }
    }
}
