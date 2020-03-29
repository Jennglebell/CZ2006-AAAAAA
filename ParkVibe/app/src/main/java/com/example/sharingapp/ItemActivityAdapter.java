package com.example.sharingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * For our listview ..................
 * ItemActivityAdapter is responsible for what information is displayed in ListView entries.
 */
public class ItemActivityAdapter extends ArrayAdapter<Item> {

    private LayoutInflater inflater;
    private Context context;

    public ItemActivityAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);
        ItemController item_controller = new ItemController(item);

        String title = "Title: " + item_controller.getName();
        String description = "Description: " + item_controller.getDescription();
        List<Bitmap> thumbnails = item_controller.getImages();
        String status = "Hashtag: " + item_controller.getHashtag();

        // Check if an existing view is being reused, otherwise inflate the view.
        if (convertView == null) {
            convertView = inflater.from(context).inflate(R.layout.itemlist_item, parent, false);
        }

        TextView title_tv = (TextView) convertView.findViewById(R.id.title_tv);
        TextView status_tv = (TextView) convertView.findViewById(R.id.status_tv);
        TextView description_tv = (TextView) convertView.findViewById(R.id.description_tv);
        ImageView photo1 = (ImageView) convertView.findViewById(R.id.image_view);
//        ImageView photo2 = (ImageView) convertView.findViewById(R.id.image_view2);
        List<ImageView> photos = new ArrayList<>();
        photos.add(photo1);
        //photos.add(photo2);
        int i = 0;
        for(Bitmap thumbnail : thumbnails) {
            if (thumbnail != null) {
                photos.get(i).setImageBitmap(thumbnail);
            } else {
                photos.get(i).setImageResource(android.R.drawable.ic_menu_gallery);
            }
            i++;
        }

        title_tv.setText(title);
        description_tv.setText(description);
        status_tv.setText(status);

        return convertView;
    }
}

