package org.kea.therealwishlist.controller;

import jakarta.servlet.http.HttpSession;
import org.kea.therealwishlist.model.User;
import org.springframework.ui.Model;
import org.kea.therealwishlist.model.WishList;
import org.kea.therealwishlist.service.WishListService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WishListController {

    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @GetMapping("/createwishlist")
    public String createWishList(HttpSession session, Model model) {
        // Henter brugeren fra sessionen
        User user = (User) session.getAttribute("user");

        // Tjekker om brugeren er logget ind
        if (user == null) {
            return "redirect:login"; // omdirigerer til login, hvis brugeren ikke er logget ind
        }
        model.addAttribute("user", user); // Sender brugerens data videre til HTML-siden
        return "createWishList"; // Siden hvor brugeren kan oprette en ønskeliste
    }


    @PostMapping("/savewishlist")
    public String createWishList(@RequestParam String wishListName, @RequestParam int userID, RedirectAttributes redirectAttributes) {
        wishListService.createWishList(wishListName, userID);

        redirectAttributes.addFlashAttribute("message", "Wish list successfully created! Happy wishing!");

        return "redirect:welcome";
    }


    /*
    @GetMapping("/{userName}/{wishListName}")
    public String userWishList(Model model, @PathVariable String userName, @PathVariable String wishListName) {
        List<Wish> wishes = wishListService.getWishesFromUserNameAndWishListName(userName, wishListName);
        return "wishList";
    }
     */
}
