package org.kea.therealwishlist.model;

import java.util.List;

public class WishList {

    private int id;
    private int userId;
    private String wishListName;
    private List<Wish> wishList;

    public WishList(int userId, String wishListName) {
        this.userId = userId;
        this.wishListName = wishListName;
    }

    public WishList(int id, int userId, String wishListName) {
        this.id = id;
        this.userId = userId;
        this.wishListName = wishListName;
    }

    public WishList(int id, int userId, String wishListName, List<Wish> wishList) {
        this.id = id;
        this.userId = userId;
        this.wishListName = wishListName;
        this.wishList = wishList;
    }

    // Gettere
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getWishListName() {
        return wishListName;
    }

    public List<Wish> getWishList() {
        return wishList;
    }

    // Settere
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setWishListName(String wishListName) {
        this.wishListName = wishListName;
    }

    public void setWishList(List<Wish> wishList) {
        this.wishList = wishList;
    }
}
