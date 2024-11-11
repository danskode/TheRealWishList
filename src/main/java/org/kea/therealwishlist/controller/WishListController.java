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

    @GetMapping("wishlist/{wishListID}")
    public String showWishListByWishLIstIDAndUserID(Model model, @PathVariable int wishListID, HttpSession session) {
        // Hent userID fra sessionen ...
        //Integer userID = (Integer) session.getAttribute("userID");
        User user = (User) session.getAttribute("user");

        // Tjek, om userID er sat i session ...
        if (user == null) {
            return "redirect:/login";
        }

        // Brug userID til at hente ønskelister
        WishList wishes = wishListService.getWishListFromWishListIDAndUserID(wishListID, user.getUserID());

        // Tilføj ønskerne til modellen
        model.addAttribute("wishes", wishes);

        return "wishlist";
        }

    // delete-metode til wishlist ...
    @PostMapping("wishlist/delete/{wishListID}")
    public String deleteWishList(@PathVariable int wishListID) {
        try {
            wishListService.deleteWishListFromWishListID(wishListID);
            return "redirect:/welcome"; // Send brugeren tilbage til oversigten over ønskelister ...
        } catch (Exception e) {
            return "error"; // Send brugeren til en fejlside ved fejl ...
        }
    }
}
