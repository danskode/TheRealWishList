package org.kea.therealwishlist.model;

public class Profile {

    private int profileID;
    private String profileName;
    private String profilePassword;

    public Profile() {

    }

    public Profile(String profileName, String profilePassword) {
        this.profileName = profileName;
        this.profilePassword = profilePassword;
    }

    // Getters and setters
    public int getProfileID() {
        return profileID;
    }
    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }
    public String getProfileName() {
        return profileName;
    }
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }
    public String getProfilePassword() {
        return profilePassword;
    }
    public void setProfilePassword(String profilePassword) {
        this.profilePassword = profilePassword;
    }
}