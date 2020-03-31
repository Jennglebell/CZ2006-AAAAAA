package Control;

import android.graphics.Bitmap;

import Model.Item;
import Model.Observer;
import Model.oiphoto;

import java.util.List;

/**
 * ItemController is responsible for all communication between views and Item model
 */

public class ItemController {

    private Item item;

    public ItemController(Item item){
        this.item = item;
    }

    public String getName(){
        return item.getName();
    }

    public void setName(String name) {
        item.setName(name);
    }

    public String getId(){
        return item.getId();
    }

    public void setId() {
        item.setId();
    }

    public String getHashtag(){return item.getHashtag();}
    public void setHashtag(String hashtag){item.setHashtag(hashtag);}

    public void setLocation(String lonti, String lati) {
        item.setLocation(lonti, lati);
    }

    public String getLonti() {
        return item.getLonti();
    }

    public String getLati() {
        return item.getLati();
    }

    public void setDescription(String description) {
        item.setDescription(description);
    }
    public String getDescription() {
        return item.getDescription();
    }


    public void addImage(Bitmap new_image){
        item.addImage(new_image);
    }

    public Bitmap getImage(){
        return item.getImage();
    }

    public List<Bitmap> getImages(){
        return item.getImages();
    }

    public List<oiphoto> getOiphotos(){
        return item.getOiphotos();
    }

    public Item getItem() { return this.item; }

    public void addObserver(Observer observer) {
        item.addObserver(observer);
    }

    public void removeObserver(Observer observer) {
        item.removeObserver(observer);
    }
}
