package org.kea.therealwishlist.controller;

import jakarta.servlet.http.HttpSession;
import org.kea.therealwishlist.model.User;
import org.kea.therealwishlist.model.Wish;
import org.kea.therealwishlist.service.WishService;
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
    private final WishService wishService;

    public WishListController(WishListService wishListService, WishService wishService) {
        this.wishListService = wishListService;
        this.wishService = wishService;
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
        // Tilføjer userID til modellen
        model.addAttribute("userId", user.getUserID());
        model.addAttribute("wishList_userID", wishes.getUserId());

        // Debugging - udskriv værdier
        System.out.println("User ID: " + user.getUserID());
        System.out.println("WishList User ID: " + wishes.getUserId());

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

    // Update wishlist-metode ...
    // Først GET-mapping for at vise opdateringsformularen
    @GetMapping("/updateWishList/{wishListID}")
    public String showUpdateWishListForm(@PathVariable int wishListID, Model model) {
        // Hent ønskelisten baseret på wishListID
        WishList wishList = wishListService.getWishListById(wishListID);

        // Tilføj ønskelisten til modellen
        model.addAttribute("wishList", wishList);

        // Returner Thymeleaf-skabelonen for opdateringsformularen
        return "updateWishList";
    }

    // Så POST-mapping for at gemme ændringerne til specifik wishlist (ovenfor) ...
    @PostMapping("/updateWishList")
    public String updateWishListByIDWithNewName(@RequestParam("wishListId") int wishListId, @RequestParam("wishListName") String wishListName) {
        wishListService.updateWishList(wishListId, wishListName);
        return "redirect:/welcome";
    }

    // nico
    @GetMapping("otherswishlist/{wishListID}")
    public String showOthersWishListByWishLIstIDAndUserID(Model model, @PathVariable int wishListID, HttpSession session) {

        //Integer userID = (Integer) session.getAttribute("userID");
        User user = (User) session.getAttribute("user");

        // Tjek, om userID er sat i session ...
        if (user == null) {
            return "redirect:/";
        }

        // Brug wishListID til at hente ønskelister
        WishList wishes = wishListService.getWishListById(wishListID);

        // Tilføj ønskerne til modellen
        model.addAttribute("wishes", wishes);
        // Tilføjer userID til modellen
        model.addAttribute("userId", user.getUserID());
        model.addAttribute("wishList_userID", wishes.getUserId());

        // Debugging - udskriv værdier
        System.out.println("User ID: " + user.getUserID());
        System.out.println("WishList User ID: " + wishes.getUserId());

        return "otherswishlist";
    }

    // nico
    @PostMapping("/reserveWish")
    public String reserveWish(@RequestParam("wish_id") int wishId, @RequestParam("user_id") int userId) {
        // Hent ønsket og brugeren
        Wish wish = wishService.getWishByID(wishId);
        int wlID = wishListService.getWishLisIdFromWishId(wishId);
        if (wish != null && !wish.isReserved()) {
            // Opret en reservation i databasen
            wishService.createReservation(wishId);
        }

        // Omdirigér tilbage til ønskelisten
        // int wishListId = wish.getWishListID();
        return "redirect:/otherswishlist/" + wlID;
    }

}
