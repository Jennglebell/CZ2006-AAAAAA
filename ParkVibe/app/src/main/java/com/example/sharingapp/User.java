package com.example.sharingapp;

import java.util.UUID;

/**
 * User class
 */
public class User extends Observable {
    private String username;
    private String email;
    private String id;

    private boolean isAdmin;

    User(String username, String email, String id) {
        this.username = username;
        this.email = email;

        if (id == null){
            setId();
        } else {
            updateId(id);
        }
    }

    User(String username, String email, String id, boolean isAdmin) {
        this.username = username;
        this.email = email;

        this.isAdmin = isAdmin;

        if (id == null){
            setId();
        } else {
            updateId(id);
        }
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean getAdmin(){
        return isAdmin;
    }

    public String getId(){
        return this.id;
    }

    public void setId() {
        this.id = UUID.randomUUID().toString();
        notifyObservers();
    }

    public void updateId(String id){
        this.id = id;
        notifyObservers();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyObservers();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email= email;
        notifyObservers();
    }
}

