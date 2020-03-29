package com.example.sharingapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Item class
 */
public class Item extends Observable {
     //use in system
    private String id;
    private String hashtag;
    private String name;
    private String description;
    private Location location;
    protected List<oiphoto> oiphotos;
<<<<<<< HEAD:ParkVibe/app/src/main/java/com/example/sharingapp/Item.java
    protected List<oiphoto> oiphotos_temp;
=======
>>>>>>> e1396f3bd08f7218e2d6c967d7f04c4b7f3add8f:SharingApp_/app/src/main/java/com/example/sharingapp/Item.java
    protected transient Bitmap image;
    protected List<Bitmap> images;
    protected String image_base64;
    protected List<String> image_base64s;
    protected Map<String, String> getowners;



//    public Item(String hashtag, String name, String description, List<Bitmap> images, String id) {
//        this.hashtag = hashtag;
//        this.name = name;
//        this.description= description;
//        this.location = null;
//        image_base64s = new ArrayList<>();
//        for(Bitmap image : images) {
//            if(image!=null)
//                addImage(image);
//        }
//        if (id == null){
//            setId();
//        } else {
//            updateId(id);
//        }
//    }

    public Item(String hashtag, String name, String description, List<oiphoto> oiphotos, String id) {
        this.hashtag = hashtag;
        this.name = name;
        this.description= description;
        this.location = null;
<<<<<<< HEAD:ParkVibe/app/src/main/java/com/example/sharingapp/Item.java
        oiphotos_temp = new ArrayList<>();
        //getowners = new HashMap<>();
        for(oiphoto oiphoto : oiphotos) {
            if(oiphoto!=null) {
                addoiphoto(oiphoto);
            }
=======
        image_base64s = new ArrayList<>();
        addImage(image);
        if (id == null){
            setId();
        } else {
            updateId(id);
        }
    }

//    public Item(String hashtag, String name, String description, List<Bitmap> images, String id) {
//        this.hashtag = hashtag;
//        this.name = name;
//        this.description= description;
//        this.location = null;
//        image_base64s = new ArrayList<>();
//        for(Bitmap image : images) {
//            if(image!=null)
//                addImage(image);
//        }
//        if (id == null){
//            setId();
//        } else {
//            updateId(id);
//        }
//    }

    public Item(String hashtag, String name, String description, List<oiphoto> oiphotos, String id) {
        this.hashtag = hashtag;
        this.name = name;
        this.description= description;
        this.location = null;
        image_base64s = new ArrayList<>();
        for(oiphoto oiphoto : oiphotos) {
            if(oiphoto!=null && oiphoto.getImage()!=null)
                addImage(oiphoto.getImage());
>>>>>>> e1396f3bd08f7218e2d6c967d7f04c4b7f3add8f:SharingApp_/app/src/main/java/com/example/sharingapp/Item.java
        }
        if (id == null){
            setId();
        } else {
            updateId(id);
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashtag() {
        return hashtag;
    }

    public void setHashtag(String hashtag) {
        this.hashtag = hashtag;
    }

    public void setLocation(String lonti, String lati) {
        location = new Location(lonti, lati);
        notifyObservers();
    }

    public void setDescription(String description) {
        this.description = description;
        notifyObservers();
    }

    public String getDescription() {
        return description;
    }

    public String getLonti() {
        return location.getLonti();
    }

    public String getLati() {
        return location.getLati();
    }




    public Bitmap getImage(){
        if (image == null && image_base64 != null) {
            byte[] decodeString = Base64.decode(image_base64, Base64.DEFAULT);
            image = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
            notifyObservers();
        }
        return image;
    }

    public void addImage(Bitmap new_image){

        if (new_image != null) {
            ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
            new_image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayBitmapStream);

            byte[] b = byteArrayBitmapStream.toByteArray();
            image_base64 = Base64.encodeToString(b, Base64.DEFAULT);
            // if(image_base64!=null)
            image_base64s.add(image_base64);
        }
        notifyObservers();
    }

    public List<Bitmap> getImages(){
        images = new ArrayList<>();
        if (image_base64s != null) {
            for(String image_base64 : image_base64s) {
                byte[] decodeString = Base64.decode(image_base64, Base64.DEFAULT);
                image = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                if(image!=null)
                    images.add(image);
                notifyObservers();
            }
        }
        return images;
    }

<<<<<<< HEAD:ParkVibe/app/src/main/java/com/example/sharingapp/Item.java
//    public List<String> getOwners(){
//        return this.owners;
//    }

    public void addoiphoto(oiphoto oiphoto_){
        if (oiphoto_ != null) {
            ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
            oiphoto_.getImage().compress(Bitmap.CompressFormat.PNG, 100, byteArrayBitmapStream);

            byte[] b = byteArrayBitmapStream.toByteArray();
            image_base64 = Base64.encodeToString(b, Base64.DEFAULT);
            oiphoto_.setImage_base64(image_base64);
            // if(image_base64!=null)
            System.out.println("calling from Item class: add photo");
            oiphotos_temp.add(oiphoto_);
        }
        notifyObservers();
    }
//
    public List<oiphoto> getOiphotos(){
        oiphotos = new ArrayList<>();
        if (oiphotos_temp != null) {
            for(oiphoto oiphoto_ : oiphotos_temp) {
                System.out.println("calling from Item class: get photo");
                byte[] decodeString = Base64.decode(oiphoto_.getImage_base64(), Base64.DEFAULT);
                image = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
                if(image!=null)
                    oiphotos.add(new oiphoto(image, oiphoto_.getOwner()));
                notifyObservers();
            }
        }
        return oiphotos;
    }
=======


//    public void addoiphoto(oiphoto oiphoto_){
//
//        if (oiphoto_.getImage() != null) {
//            image = oiphoto_.getImage();
//            ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream();
//            oiphoto_.getImage().compress(Bitmap.CompressFormat.PNG, 100, byteArrayBitmapStream);
//
//            byte[] b = byteArrayBitmapStream.toByteArray();
//            image_base64 = Base64.encodeToString(b, Base64.DEFAULT);
//            // if(image_base64!=null)
//            image_base64s.add(image_base64);
//        }
//        notifyObservers();
//    }
//
//    public List<Bitmap> getImages(){
//        images = new ArrayList<>();
//        if (image_base64s != null) {
//            for(String image_base64 : image_base64s) {
//                byte[] decodeString = Base64.decode(image_base64, Base64.DEFAULT);
//                image = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
//                if(image!=null)
//                    images.add(image);
//                notifyObservers();
//            }
//        }
//        return images;
//    }
>>>>>>> e1396f3bd08f7218e2d6c967d7f04c4b7f3add8f:SharingApp_/app/src/main/java/com/example/sharingapp/Item.java

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

    public Item(String hashtag, String name, String description, Bitmap image, String id) {
        this.hashtag = hashtag;
        this.name = name;
        this.description= description;
        this.location = null;
        image_base64s = new ArrayList<>();
        addImage(image);
        if (id == null){
            setId();
        } else {
            updateId(id);
        }
    }
}

