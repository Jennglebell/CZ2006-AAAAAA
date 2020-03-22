package com.example.sharingapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class Photo extends Observable {
    protected transient Bitmap image;
    protected String image_base64;
    protected User owner;

    public Photo(Bitmap image, String image_base64, User owner) {
        this.image = image;
        this.image_base64 = image_base64;
        this.owner = owner;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage(){
        return image;
    }

    public String getImage_base64() {
        return image_base64;
    }

    public void setImage_base64(String image_base64) {
        this.image_base64 = image_base64;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
