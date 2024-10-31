package org.kea.therealwishlist.controller;

import org.kea.therealwishlist.model.Profile;
import org.kea.therealwishlist.repository.ProfileRepository;
import org.kea.therealwishlist.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    private final ProfileService profileService;
    private final ProfileRepository profileRepository;

    public ProfileController(ProfileService profileService, ProfileRepository profileRepository) {
        this.profileService = profileService;
        this.profileRepository = profileRepository;
    }


    // Viser create profile formularen - her opretter man sig
    @GetMapping("/create")
    public String showCreateProfileForm(Model model) {
        model.addAttribute("profile", new Profile());
        return "createProfile";
    }


    // En ny instans af user/profile bliver oprettet
    @PostMapping("/save")
    public String createProfile(@ModelAttribute ("profile") Profile profile, Model model) { //"Profile" binder dataene fra en HTML-formular til et Profile-objekt
        profileService.createProfile(profile); // kalder create metoden i Service

        model.addAttribute("message", "Profile succesfully created! Happy wishing!");

        return "";// omdirigeres til en liste over sine ønskelister - opret ønskeliste??
    }


}
