package org.kea.therealwishlist.controller;

import jakarta.servlet.http.HttpSession;
import org.kea.therealwishlist.model.User;
import org.kea.therealwishlist.model.WishList;
import org.kea.therealwishlist.repository.UserRepository;
import org.kea.therealwishlist.service.UserService;
import org.kea.therealwishlist.service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final WishListService wishListService;

    public UserController(UserService userService, WishListService wishListService) {
        this.userService = userService;
        this.wishListService = wishListService;
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
        try {
            userService.createUser(user); // kalder create metoden i Service

            model.addAttribute("message", "User succesfully created! Happy wishing!");

            return "login";
        } catch (Exception e) {
            return "error";
        }
    }

    // Viser login-siden / vores forside
    @GetMapping({""})
    public String showLoginForm(Model model) {
        // Et tomt User-objekt tilføjet til modellen, som kan bruges i formularen
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session,
                            Model model) {

        // Forsøger at logge brugeren ind
        User user = userService.loginUser(username, password);

        if (user != null) {
            // Gemmer hele User-objektet i sessionen
            session.setAttribute("user", user);
            model.addAttribute("user", user);
            model.addAttribute("userID", user.getUserID());

            return "redirect:welcome";
        } else {
            model.addAttribute("error", "Forkert brugernavn eller adgangskode!");
            return "login";
        }
    }
    @GetMapping("/welcome")
    public String showWelcomePage(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user"); // Hent User-objektet fra session

        if (user != null) {
            model.addAttribute("username", user.getUserName());
            model.addAttribute("userID", user.getUserID());
            List<WishList> wishLists = wishListService.getAllWishListsFromUserID(user.getUserID());
            model.addAttribute("wishLists", wishLists);

            return "welcome";
        } else {
            return "redirect:/login"; // omdiriger til login, hvis brugeren ikke er logget ind
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
