package org.kea.therealwishlist.model;

public class WishList {

    private int id;
    private int userId;
    private String listName;

    public WishList(int userId, String listName) {
        this.userId = userId;
        this.listName = listName;
    }

    // Gettere
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getListName() {
        return listName;
    }

    // Settere
    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }
}
