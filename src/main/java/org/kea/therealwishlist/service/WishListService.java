package org.kea.therealwishlist.service;

import org.kea.therealwishlist.model.Wish;
import org.kea.therealwishlist.model.WishList;
import org.kea.therealwishlist.repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;

    public WishListService(WishListRepository wishListRepository) {
        this.wishListRepository = wishListRepository;
    }

//    public List<Wish> getWishesFromUserNameAndWishListName(String userName, String wishListName) {
//        // her skal være en metode til at hente en specifik brugers ønskeliste ...
//        wishListRepository.getWishesFromUserNameAndWishListName(userName, wishListName);
//    }

    public void createWishList(String wishListName, int userID) {
        wishListRepository.createWishList(wishListName, userID);
    }

    public List<WishList> getAllWishListsFromUserID(int userID) {
        return wishListRepository.getAllWishListsFromUserID(userID);
    }
}
