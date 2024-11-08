package org.kea.therealwishlist.model;

public class Wish {

    private int wishID;
    private String wishName;
    private String url;
    private float price;
    private boolean reserved;
    private int wishListID;


    public Wish(String wishName, String url, float price, boolean reserved) {
        this.wishName = wishName;
        this.url = url;
        this.price = price;
        this.reserved = reserved;
    }

    public Wish() {

    }

    public Wish(int wishID, String wishName, String url, float price, boolean reserved) {
        this.wishID = wishID;
        this.wishName = wishName;
        this.url = url;
        this.price = price;
        this.reserved = reserved;
    }

    // Getters
    public int getWishID() {
        return wishID; }

    public String getWishName() {
        return wishName; }

    public String getUrl() {
        return url; }

    public float getPrice() {
        return price; }

    public boolean isReserved() {
        return reserved; }

    public int getWishListID() {
        return wishListID;
    }

    // Setters
    public void setWishID(int wishID) {
        this.wishID = wishID; }

    public void setWishName(String wishName) {
        this.wishName = wishName; }

    public void setUrl(String url) {
        this.url = url; }

    public void setPrice(float price) {
        this.price = price; }

    public void setReserved(boolean reserved) {
        this.reserved = reserved; }

    public void setWishListID(int wishListID) {
        this.wishListID = wishListID;
    }


}