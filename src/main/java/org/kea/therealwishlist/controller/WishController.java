package org.kea.therealwishlist.controller;

import org.kea.therealwishlist.model.Wish;
import org.kea.therealwishlist.repository.WishRepository;
import org.kea.therealwishlist.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WishController {

    private final WishService wishService;

    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @GetMapping("/createwish/{wishListID}")
    public String showCreateWishForm(@PathVariable int wishListID, Model model) {  // localhost:8080//createwish/1
        model.addAttribute("wish", new Wish());
        model.addAttribute("wishListID", wishListID);  // Send wishListID til formularen
        return "createWish"; // Navn på HTML-siden til formularen
    }

    // opret ønske
    @PostMapping("/savewish")
    public String createWish(@ModelAttribute Wish wish, @RequestParam int wishListID) {
        try {
            wish.setWishListID(wishListID);  // Sæt wishListID i Wish-objektet
            wishService.getCreateWish(wish);
            return "redirect:/welcome"; // Hvor skal man hen ????
        } catch (Exception e) {
            return "error";
        }
    }

    // opdater ønske
    @GetMapping("/editwish/{id}")
    public String showEditWishForm(@PathVariable("id") int wishID, Model model) {
        Wish existingWish = wishService.getWishByID(wishID); // Henter det eksisterende ønske baseret på ID
        model.addAttribute("wish", existingWish); // vi tilføjer det eksisterende ønske til modellen
        System.out.println("Wish ID: " + existingWish.getWishID());
        System.out.println("Navn: " + existingWish.getWishName());
        System.out.println("Pris: " + existingWish.getPrice());
        return "editWish";
    }

    @PostMapping("/updatewish")
    public String updateWish(@ModelAttribute Wish wish) {
        try {
            System.out.println("Wish ID: " + wish.getWishID());
            System.out.println("Navn: " + wish.getWishName());
            System.out.println("Pris: " + wish.getPrice());
            wishService.getUpdateWish(wish); // opdaterer ønsket i db
            return "redirect:/welcome";
        } catch (Exception e) {
            return "error";
        }

    }

    // slet ønske
    @PostMapping("/deletewish/{wishID}")
    public String deleteWish(@PathVariable int wishID) {
        try {
            wishService.deleteWish(wishID);
            return "redirect:/welcome";
        } catch (Exception e) {
            return "error";
        }
    }


}
