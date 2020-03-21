package com.example.sharingapp;

import java.util.List;
import java.util.UUID;

/**
 * User class
 */
public class User extends Observable {
    private String username;
    private String password;
    private boolean isAdmin;
    private boolean isLogin;
    private double longtitude;
    private double latitude;


    public List<Double> getUserLocation(){
        List<Double> location;
        location.add(longtitude);
        location.add(latitude);
        return location;
    }


    User(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = email;
        this.isAdmin = isAdmin;
    }



    public boolean isAdmin(){
        return this.isAdmin;
    }

    public void setAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyObservers();
    }

    public String getPassword() {
        return email;
    }

    public void setPassword(String password) {
        this.password= password;
        notifyObservers();
    }
}

