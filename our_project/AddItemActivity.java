package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Add a new item
 */
public class AddItemActivity extends AppCompatActivity {


    private EditText hashtag;
    private EditText name;
    private EditText dateCreate;
    private EditText description;
    private EditText longtitude;
    private EditText latitude;

    private ImageView photo;
    protected Bitmap image;
   // protected transient List<Bitmap> images;
    protected String image_base64;



   // private EditText name;
   // private EditText dateCreate;
   // private EditText description;
    // private EditText longtitude;
    // private EditText latitude;
    private EditText height;
    private EditText min_bid;

    private String user_id;
  //  private ImageView photo;
   // private Bitmap image;
    private int REQUEST_CODE = 1;

    private ItemList item_list = new ItemList();
    private ItemListController item_list_controller = new ItemListController(item_list);

    private Context context;

    private String name_str;
    private String dateCreate_str;
    private String description_str;
    private String longtitude_str;
    private String latitude_str;
    private String height_str;
    private String min_bid_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_item);

        Intent intent = getIntent(); // Get intent from MainActivity
        user_id = intent.getStringExtra("user_id");

        name = (EditText) findViewById(R.id.name);
        dateCreate = (EditText) findViewById(R.id.dateCreate);
        description = (EditText) findViewById(R.id.description);
        longtitude = (EditText) findViewById(R.id.longtitude);
        latitude = (EditText) findViewById(R.id.latitude);
        height = (EditText) findViewById(R.id.height);
        photo = (ImageView) findViewById(R.id.image_view);
        min_bid = (EditText) findViewById(R.id.minimum_bid);

        photo.setImageResource(android.R.drawable.ic_menu_gallery);

        context = getApplicationContext();
        item_list_controller.loadItems(context);
    }

    public void saveItem (View view) {

        name_str = name.getText().toString();
        dateCreate_str = dateCreate.getText().toString();
        description_str = description.getText().toString();
        longtitude_str = longtitude.getText().toString();
        latitude_str = latitude.getText().toString();
        height_str = height.getText().toString();
        min_bid_str = min_bid.getText().toString();

        if(!validateInput()){
            return;
        }

        Item item = new Item(name_str, dateCreate_str, description_str, user_id, min_bid_str, image, null);
        ItemController item_controller = new ItemController(item);
        item_controller.setDimensions(longtitude_str, latitude_str, height_str);

        boolean success = item_list_controller.addItem(item, context);
        if (!success){
            return;
        }

        // End AddItemActivity
        final Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user_id", user_id);
        Toast.makeText(context, "Item created.", Toast.longtitude_SHORT).show();
        startActivity(intent);
    }

    public void addPhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    public void deletePhoto(View view) {
        image = null;
        photo.setImageResource(android.R.drawable.ic_menu_gallery);
        Toast.makeText(context, "Photo removed.", Toast.longtitude_SHORT).show();
    }

    @Override
    protected void onActivityResult(int request_code, int result_code, Intent intent){
        if (request_code == REQUEST_CODE && result_code == RESULT_OK){
            Bundle extras = intent.getExtras();
            image = (Bitmap) extras.get("data");
            photo.setImageBitmap(image);
        }
        Toast.makeText(context, "Photo added.", Toast.longtitude_SHORT).show();
    }

    public boolean validateInput(){
        if (name_str.equals("")) {
            name.setError("Empty field!");
            return false;
        }

        if (dateCreate_str.equals("")) {
            dateCreate.setError("Empty field!");
            return false;
        }

        if (description_str.equals("")) {
            description.setError("Empty field!");
            return false;
        }

        if (longtitude_str.equals("")) {
            longtitude.setError("Empty field!");
            return false;
        }

        if (latitude_str.equals("")) {
            latitude.setError("Empty field!");
            return false;
        }

        if (height_str.equals("")) {
            height.setError("Empty field!");
            return false;
        }

        if (min_bid_str.equals("")) {
            min_bid.setError("Empty field!");
            return false;
        }

        if (Float.valueOf(min_bid_str) <= 0) {
            min_bid.setError("Starting bid must be above 0!");
            return false;
        }

        return true;
    }
}
