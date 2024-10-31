package org.kea.therealwishlist.service;

import org.kea.therealwishlist.model.Profile;
import org.kea.therealwishlist.repository.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void createProfile(Profile profile) {
        profileRepository.createProfile(profile);
    }
}