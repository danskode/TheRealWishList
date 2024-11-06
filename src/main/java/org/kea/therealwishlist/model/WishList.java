package org.kea.therealwishlist.model;

public class WishList {

    private int id;
    private int userId;
    private String wishListName;

    public WishList(int userId, String wishListName) {
        this.userId = userId;
        this.wishListName = wishListName;
    }

    public WishList(int id, int userId, String wishListName) {
        this.id = id;
        this.userId = userId;
        this.wishListName = wishListName;
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
}
