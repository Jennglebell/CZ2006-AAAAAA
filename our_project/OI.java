package com.example.sharingapp;

import androhashtag.graphics.Bitmap;
import androhashtag.graphics.BitmapFactory;
import androhashtag.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.UUhashtag;

import java.util.*;
import java.lang.*;
import java.io.*;

import java.awt.color.*;

/**
 * Item class
 */
public class OI extends Observable {
    private String hashtag;
    private String name;
    private String dateCreate;
    private String description;
    private double longtitude;
    private double latitude;
    protected transient Bitmap image;
   // protected transient List<Bitmap> images;
    protected String image_base64;


//     //private String name;
//     //private String dateCreate;
//    // private String description;
//     private Dimensions dimensions;
//     private String status;
//     private Float minimum_bhashtag;
//     private User creator;
//     private String owner_hashtag;
//    // protected transient Bitmap image;
//  //   protected String image_base64;
//     //private String hashtag;

    public OI(String hashtag, String name, String dateCreate, String description, Double longtitude, Double latitude,Bitmap image){
        this.name = name;
        this.description = description;
        this.hashtag = hashtag;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.minimum_bhashtag = Float.valueOf(minimum_bhashtag);
        this.creator = null;
        addImage(image);

        if (hashtag == null){
            sethashtag();
        } else {
            updatehashtag(hashtag);
        }
    }

    // public Item(String name, String dateCreate, String description, String owner_hashtag, String minimum_bhashtag, Bitmap image, String hashtag) {
    //     this.name = name;
    //     this.dateCreate = dateCreate;
    //     this.description = description;
    //     this.dimensions = null;
    //     this.owner_hashtag = owner_hashtag;
    //     this.status = "Available";
    //     this.minimum_bhashtag = Float.valueOf(minimum_bhashtag);
    //     this.creator = null;
    //     addImage(image);

    //     if (hashtag == null){
    //         sethashtag();
    //     } else {
    //         updatehashtag(hashtag);
    //     }
    // }
    

    // doing later
    public double calculateDistance(double lon1, double lat1, double lon2, double lat2){
        double dist;
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        }
        else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            dist = dist * 1.609344;
            return (dist);
        }
    }

    public double getDistance(User user){
        double lon1 = user.getUserLocation()[0];
        double lat1 = user.getUserLocation()[1];
        double lon2 = this.longtitude;
        double lat2 = this.latitude;
        return this.calculateDistance(lon1,lat1,lon2,lat2);
    }

    public String gethashtag(){
        return this.hashtag;
    }

    public vohashtag sethashtag() {
        this.hashtag = UUhashtag.randomUUhashtag().toString();
        notifyObservers();
    }

    public vohashtag updatehashtag(String hashtag){
        this.hashtag = hashtag;
        notifyObservers();
    }

    public vohashtag setname(String name) {
        this.name = name;
        notifyObservers();
    }

    public String getname() {
        return name;
    }

    public vohashtag setdateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
        notifyObservers();
    }

    public String getdateCreate() {
        return dateCreate;
    }

    public vohashtag setDescription(String description) {
        this.description = description;
        notifyObservers();
    }

    public String getDescription() {
        return description;
    }

    public Float getMinBhashtag() {
        return this.minimum_bhashtag;
    }

    public vohashtag setMinBhashtag(Float minimum_bhashtag) {
        this.minimum_bhashtag = minimum_bhashtag;
        notifyObservers();
    }

    public vohashtag setOwnerhashtag(String owner_hashtag) {
        this.owner_hashtag = owner_hashtag;
        notifyObservers();
    }

    public String getOwnerhashtag() {
        return owner_hashtag;
    }

    public vohashtag setDimensions(String length, String whashtagth, String height) {
        dimensions = new Dimensions(length, whashtagth, height);
        notifyObservers();
    }

    public String getLength(){
        return dimensions.getLength();
    }

    public String getWhashtagth(){
        return dimensions.getWhashtagth();
    }

    public String getHeight(){
        return dimensions.getHeight();
    }

    public vohashtag setStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    public String getStatus() {
        return status;
    }

    public vohashtag setcreator(User creator) {
        this.creator = creator;
        notifyObservers();
    }

    public User getCreator() {
        return creator;
    }

    public String getCreatorUsername() {
        if (creator != null){
            return creator.getUsername();
        }
        return null;
    }

    public vohashtag addImage(Bitmap new_image){
        if (new_image != null) {
            image = new_image;
            ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
            new_image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayBitmapStream);

            byte[] b = byteArrayBitmapStream.toByteArray();
            image_base64 = Base64.encodeToString(b, Base64.DEFAULT);
        }
        notifyObservers();
    }

    public Bitmap getImage(){
        if (image == null && image_base64 != null) {
            byte[] decodeString = Base64.decode(image_base64, Base64.DEFAULT);
            image = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
            notifyObservers();
        }
        return image;
    }
}

