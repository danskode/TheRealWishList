package org.kea.therealwishlist.controller;

import jakarta.servlet.http.HttpSession;
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

        return "frontpage";// omdirigeres til en liste over sine ønskelister - opret ønskeliste??
    }

    // Viser login-siden / vores forside
    @GetMapping({"/login"})
    public String showLoginForm(Model model) {
        // Et tomt User-objekt tilføjet til modellen, som kan bruges i formularen
        model.addAttribute("user", new User());
        return "frontpage";
    }

    // Når brugeren indsender loginformularen, tjekker denne metode brugernavn og adgangskode
    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username, // brugernavn indtastet af brugeren
                            @RequestParam("password") String password,
                            HttpSession session,// kodeord indtastet af brugeren
                            Model model) { // Model-objektet bruges til at sende data til HTML-siden

        // Kalder loginUser fra userService og forsøger at logge ind
        User user = userService.loginUser(username, password);

        // Tjekker om brugeren blev fundet
        if (user != null) {
            session.setAttribute("username", user.getUserName());
            model.addAttribute("user", user);

            return "redirect:welcome"; // her kan brugeren se sine ønskelister (NICOLAI)
        } else {
            model.addAttribute("error", "Forkert brugernavn eller adgangskode!");
            return "frontpage"; // returnerer login-siden med fejlbesked
        }
    }

    @GetMapping("/welcome")
    public String showWelcomePage(HttpSession session, Model model) {
        //User user = (User) session.getAttribute("user");
        String username = (String) session.getAttribute("username");

        // Hvis brugeren er logget ind, send deres navn til modellen
        if (username != null) {
            model.addAttribute("username", username);  // eller brug user.getFullName(), afhængig af, hvad du vil vise
            return "welcome";  // Navnet på din HTML-template (f.eks. "welcome.html")
        } else {
            return "redirect:/login";  // Hvis brugeren ikke er logget ind, send dem til login-siden
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
