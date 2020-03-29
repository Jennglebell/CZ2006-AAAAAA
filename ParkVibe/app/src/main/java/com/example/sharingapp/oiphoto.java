package com.example.sharingapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.View;

import java.io.ByteArrayOutputStream;

public class oiphoto extends Observable {
    protected transient Bitmap image;
    protected String image_base64;
    protected String owner;
    String imgurl;


    protected int likes;

    public oiphoto(String imgurl) {
        this.imgurl = imgurl;
    }
    public oiphoto(Bitmap image) {
        this.image = image;
    }
    public oiphoto(Bitmap image, String owner) {
        this.image = image;
        this.owner = owner;
    }
    public oiphoto(Bitmap image, String owner, int likes) {
        this.image = image;
        this.owner = owner;
        this.likes = likes;
    }


    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
