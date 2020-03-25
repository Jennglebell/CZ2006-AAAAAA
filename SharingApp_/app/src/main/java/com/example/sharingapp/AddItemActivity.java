package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Add a new item
 */
public class AddItemActivity extends AppCompatActivity {

    private EditText name;
    private EditText hashtag;
    private EditText description;
    private EditText longtitude;
    private EditText latitude;

    private String user_id;
    private String id;
    private ImageView photo;
    private Bitmap image;
    private int REQUEST_CODE = 1;

    private ItemList item_list = new ItemList();
    private ItemListController item_list_controller = new ItemListController(item_list);

    private Context context;

    private String name_str;
    private String hashtag_str;
    private String description_str;
    private String longtitude_str;
    private String latitude_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_item);

        Intent intent = getIntent(); // Get intent from MainActivity
        user_id = intent.getStringExtra("user_id");
        id = intent.getStringExtra("id");
        name = (EditText) findViewById(R.id.addname);
        hashtag = (EditText) findViewById(R.id.addhastag);
        description = (EditText) findViewById(R.id.adddescription);
        longtitude = (EditText) findViewById(R.id.addlongtitude);
        latitude = (EditText) findViewById(R.id.addlatitude);
        photo = (ImageView) findViewById(R.id.image_view);

        photo.setImageResource(android.R.drawable.ic_menu_gallery);

        context = getApplicationContext();
        item_list_controller.loadItems(context);
    }

    public void saveItem (View view) {

        name_str = name.getText().toString();
        hashtag_str = hashtag.getText().toString();
        description_str = description.getText().toString();
        longtitude_str = longtitude.getText().toString();
        latitude_str = latitude.getText().toString();

        if(!validateInput()){
            return;
        }

        Item item = new Item(hashtag_str,name_str, description_str, image, id);
        ItemController item_controller = new ItemController(item);
        item_controller.setLocation(longtitude_str, latitude_str);
        boolean success = item_list_controller.addItem(item, context);
        if (!success){
            return;
        }

        // End AddItemActivity
        final Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user_id", user_id);
        Toast.makeText(context, "Item created.", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(context, "Photo removed.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int request_code, int result_code, Intent intent){
        if (request_code == REQUEST_CODE && result_code == RESULT_OK){
            Bundle extras = intent.getExtras();
            image = (Bitmap) extras.get("data");
            photo.setImageBitmap(image);
        }
        Toast.makeText(context, "Photo added.", Toast.LENGTH_SHORT).show();
    }

    public boolean validateInput(){
        if (name_str.equals("")) {
            name.setError("Empty field!");
            return false;
        }

        if (hashtag_str.equals("")) {
            hashtag.setError("Empty field!");
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

        return true;
    }
}
