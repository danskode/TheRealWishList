package org.kea.therealwishlist.controller;

import org.kea.therealwishlist.model.Wish;
import org.kea.therealwishlist.repository.WishRepository;
import org.kea.therealwishlist.service.WishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WishController {

    private final WishService wishService;
    private final WishRepository wishRepository;

    public WishController(WishService wishService, WishRepository wishRepository) {
        this.wishService = wishService;
        this.wishRepository = wishRepository;
    }

    @GetMapping("/wishes")
    public String getWishes(Model model) {
        List<Wish> wishes = wishService.getWishes();
        model.addAttribute("wishes", wishes);
        return "wishes";
    }
}
