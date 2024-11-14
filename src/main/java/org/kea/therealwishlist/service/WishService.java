package org.kea.therealwishlist.service;

import org.kea.therealwishlist.model.Wish;
import org.kea.therealwishlist.repository.UserRepository;
import org.kea.therealwishlist.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {

    private final WishRepository wishRepository;
    private final UserRepository userRepository;


    public WishService(WishRepository wishRepository, UserRepository userRepository) {
        this.wishRepository = wishRepository;
        this.userRepository = userRepository;
    }

    public List<Wish> getWishesByUserID(int userID) {
        return wishRepository.findAllByUserID(userID);
    }

    public void getCreateWish(Wish wish) {
        wishRepository.createWish(wish);
    }

    public Wish getWishByID(int id) {
        return wishRepository.findWishByID(id);
    }

    public void getUpdateWish(Wish wish) {
        wishRepository.updateWish(wish);
    }

    public void deleteWish(int wishID) {
        wishRepository.deleteWish(wishID);
    }

    public void createReservation(int wishId) {
        wishRepository.createReservation(wishId);
    }
}
