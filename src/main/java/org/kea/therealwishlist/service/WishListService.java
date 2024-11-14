package org.kea.therealwishlist.service;

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

    // Metode til at oprette en ny ønskeliste ...
    public void createWishList(String wishListName, int userID) {
        wishListRepository.createWishList(wishListName, userID);
    }

    // Metode til at hente en liste over alle en brugers ønskelister ...
    public List<WishList> getAllWishListsFromUserID(int userID) {
        return wishListRepository.getAllWishListsFromUserID(userID);
    }

    // Metode til at hente en specifik ønskeliste og dens tilknyttede ønsker ...
    public WishList getWishListFromWishListIDAndUserID(int wishListID, int userID){
        return wishListRepository.getWishListFromWishListIDAndUserID(wishListID, userID);
    }

    // Slet en øsnkeliste og tilhørende ønsker ...
    public void deleteWishListFromWishListID(int wishListID){
        wishListRepository.deleteWishListFromWishListID(wishListID);
    }

    public void updateWishList(int wishListId, String wishListName) {
        wishListRepository.updateWishList(wishListId, wishListName);
    }

    public WishList getWishListById(int wishListID) {
        return wishListRepository.getWishListById(wishListID);
    }

    //nico
    public String getWishListOwnersName(int wishListID) {
        return wishListRepository.getWishListOwnersName(wishListID);
    }

    public List<WishList> getAllOtherWishLists(int userID) {
        return wishListRepository.getAllOtherWishLists(userID);
    }

    public int getWishLisIdFromWishId(int wishId) {
        return wishListRepository.getWishListIdFromWishId(wishId);
    }
}
