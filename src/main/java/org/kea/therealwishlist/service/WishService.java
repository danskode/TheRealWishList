package org.kea.therealwishlist.service;

import org.kea.therealwishlist.model.Wish;
import org.kea.therealwishlist.repository.WishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishService {

    private final WishRepository wishRepository;


    public WishService(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
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

}
