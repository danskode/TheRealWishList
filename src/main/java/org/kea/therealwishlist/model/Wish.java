package org.kea.therealwishlist.model;

public class Wish {

    private int wishID;
    private String wishName;
    private String url;
    private float price;
    private boolean reserved;


    public Wish() {

    }

    public Wish(int wishID, String wishName, String url, float price, boolean reserved) {
        this.wishID = wishID;
        this.wishName = wishName;
        this.url = url;
        this.price = price;
        this.reserved = reserved;
    }

    public boolean isReserved() {

    }


}
