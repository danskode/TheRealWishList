package org.kea.therealwishlist.controller;

import org.kea.therealwishlist.model.User;
import org.kea.therealwishlist.repository.UserRepository;
import org.kea.therealwishlist.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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

        return "";// omdirigeres til en liste over sine ønskelister - opret ønskeliste??
    }


}
