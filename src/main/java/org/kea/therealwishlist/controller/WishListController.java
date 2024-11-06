package org.kea.therealwishlist.controller;

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
    public String createWishList() {
        return "createwishlist";
    }

    @PostMapping("/savewishlist")
    public String createWishList(@RequestParam String wishListName, @RequestParam int userID, RedirectAttributes redirectAttributes) {
        wishListService.createWishList(wishListName, userID);

        redirectAttributes.addFlashAttribute("message", "Wish list successfully created! Happy wishing!");

        return "welcome"; // SKAL LAVES: Fx omdirigere til brugerens liste over egne ønskelister eller til selve listen så man kan tilføje nye ønsker
    }

    // Hent alle ønskelister fra en specifik bruger (userID) ...
    @GetMapping("wishlists/{userID}")
    public String showWishListFromUserID(@PathVariable int userID, Model model) {
        // Der skal kunne hentes userID automatisk fra cookies ... skal injectes i  metoden!!!
        List<WishList> wishLists = wishListService.getAllWishListsFromUserID(userID);
        model.addAttribute("wishLists", wishLists);
        return "wishlists";
    }

    /* Samme bare med cookie ...
    @GetMapping("/wishList")
    public String showWishListFromUserID(@CookieValue("userID") int userID, Model model) {
    // Henter ønskelisterne for brugeren med det `userID`, der findes i cookien
     */

    /*
    @GetMapping("/{userName}/{wishListName}")
    public String userWishList(Model model, @PathVariable String userName, @PathVariable String wishListName) {
        List<Wish> wishes = wishListService.getWishesFromUserNameAndWishListName(userName, wishListName);
        return "wishList";
    }
     */
}
