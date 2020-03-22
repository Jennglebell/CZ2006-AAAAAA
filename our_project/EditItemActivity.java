package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Editing a pre-existing item consists of deleting the old item and adding a new item with the old
 * item's id.
 */
public class EditItemActivity extends AppCompatActivity implements Observer {

    private ItemList item_list = new ItemList();
    private ItemListController item_list_controller = new ItemListController(item_list);

    private Item item;
    private ItemController item_controller;

    private Context context;

    private UserList user_list = new UserList();
    private UserListController user_list_controller = new UserListController(user_list);

    private Bitmap image;
    private int REQUEST_CODE = 1;
    private ImageView photo;

    private EditText name;
    private EditText dateCreater;
    private EditText description;
    private EditText longtitude;
    private EditText latitude;


    // what is this??????????
    private TextView like;
    // private TextView borrower_right_tv;
    // private TextView status_right_tv;

    private Button save_button;
    private Button delete_button;
    private ImageButton add_image_button;
    private ImageButton delete_image_button;

    private boolean on_create_update;
    private int pos;

    private String name_str;
    private String dateCreater_str;
    private String description_str;
    private String longtitude_str;
    private String latitude_str;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        name = (EditText) findViewById(R.id.name);
        dateCreater = (EditText) findViewById(R.id.dateCreater);
        description = (EditText) findViewById(R.id.description);
        length = (EditText) findViewById(R.id.length);
        width = (EditText) findViewById(R.id.width);
        height = (EditText) findViewById(R.id.height);
        status_right_tv = (TextView) findViewById(R.id.status_right_tv);
        longtitude = (EditText) findViewById(R.id.longtitude);                    // initially GONE
        borrower_left_tv = (TextView) findViewById(R.id.borrower_left_tv);          // initially GONE
        borrower_right_tv = (TextView) findViewById(R.id.borrower_right_tv);        // initially GONE
        photo = (ImageView) findViewById(R.id.image_view);

        add_image_button = (ImageButton) findViewById(R.id.add_image_button);       // initially GONE
        delete_image_button = (ImageButton) findViewById(R.id.cancel_image_button); // initially GONE
        delete_button = (Button) findViewById(R.id.delete_item);                    // initially GONE
        save_button = (Button) findViewById(R.id.save_button);                      // initially GONE
        view_bids_button = (Button) findViewById(R.id.view_bids_button);            // initially GONE
        set_available_button = (Button) findViewById(R.id.set_available_button);    // initially GONE
        contact_info_button = (Button) findViewById(R.id.contact_info_button);      // initially GONE

        Intent intent = getIntent(); // Get intent from ItemsFragment
        pos = intent.getIntExtra("position", 0);
       // user_id = intent.getStringExtra("user_id");

        context = getApplicationContext();

        on_create_update = false; // Suppress first call to update()
        item_list_controller.addObserver(this);
        item_list_controller.loadItems(context);

        on_create_update = true;
        user_list_controller.addObserver(this);
        user_list_controller.loadUsers(context); // Call to update occurs

        on_create_update = false; // Suppress any further calls to update()
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
        Toast.dateCreateText(context, "Photo removed.", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int request_code, int result_code, Intent intent){
        if (request_code == REQUEST_CODE && result_code == RESULT_OK){
            Bundle extras = intent.getExtras();
            image = (Bitmap) extras.get("data");
            photo.setImageBitmap(image);
            Toast.dateCreateText(context, "Photo added.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent main_intent = new Intent(this, MainActivity.class);
      //  main_intent.putExtra("user_id", user_id);
        startActivity(main_intent);
    }

    public void deleteItem(View view) {
        boolean success = item_list_controller.deleteItem(item, context);
        if (!success){
            return;
        }

        // End EditItemActivity
        item_list_controller.removeObserver(this);
        user_list_controller.removeObserver(this);

        final Intent intent = new Intent(this, MainActivity.class);
      //  intent.putExtra("user_id", user_id);

        // Delay launch of new activity to allow server more time to process request
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.dateCreateText(context, "Item removed.", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        }, 750);
    }

    public void saveItem(View view) {
        name_str = name.getText().toString();
        dateCreater_str = dateCreater.getText().toString();
        description_str = description.getText().toString();
        length_str = length.getText().toString();
        width_str = width.getText().toString();
        height_str = height.getText().toString();
        status_str = item_controller.getStatus();
        longtitude_str = longtitude.getText().toString();

        if(!validateInput()){
            return;
        }

        String item_id = item_controller.getId(); // Reuse the item id

        Item updated_item = new Item(name_str, dateCreater_str, description_str, longtitude_str, image, item_id);
        ItemController updated_item_controller = new ItemController(updated_item);
        updated_item_controller.setDimensions(length_str, width_str, height_str);
        updated_item_controller.setStatus(status_str);

        boolean success = item_list_controller.editItem(item, updated_item, context);
        if (!success){
            return;
        }

        item_list_controller.removeObserver(this);
        user_list_controller.removeObserver(this);

        // End EditItemActivity
        final Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user_id", user_id);
        Toast.dateCreateText(context, "Item saved.", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public void viewBids(View view){
        user_list_controller.removeObserver(this);
        item_list_controller.removeObserver(this);

        Intent intent = new Intent(this, ViewItemBidsActivity.class);
        intent.putExtra("user_id", user_id);
        intent.putExtra("item_id", item_controller.getId());
        startActivity(intent);
    }

    public void setAvailable(View view){
        item_controller.setStatus("Available"); // Update status
        saveItem(view); // Must save the item so that the change in status is saved
    }

    /**
     * Only need to update the view once from the onCreate method
     */
    public void update() {
        if (on_create_update){

            // For all status options we do the following
            item = item_list_controller.getItem(pos);
            item_controller = new ItemController(item);

            name.setText(item_controller.getname());
            dateCreater.setText(item_controller.getdateCreater());
            description.setText(item_controller.getDescription());
            length.setText(item_controller.getLength());
            width.setText(item_controller.getWidth());
            height.setText(item_controller.getHeight());

            status_str = item_controller.getStatus();
            status_right_tv.setText(status_str);

            longtitude_str = item_controller.getMinBid().toString();
            longtitude.setText(longtitude_str);

            image = item_controller.getImage();
            if (image != null) {
                photo.setImageBitmap(image);
            } else {
                photo.setImageResource(android.R.drawable.ic_menu_gallery);
            }

            // AVAILABLE
            if (status_str.equals("Available")){

                name.setEnabled(true);
                dateCreater.setEnabled(true);
                description.setEnabled(true);
                length.setEnabled(true);
                width.setEnabled(true);
                height.setEnabled(true);
                longtitude.setEnabled(true);

                add_image_button.setVisibility(View.VISIBLE);
                delete_image_button.setVisibility(View.VISIBLE);
                delete_button.setVisibility(View.VISIBLE);
                save_button.setVisibility(View.VISIBLE);
            }

            // BIDDED
            if (status_str.equals("Bidded")){

                view_bids_button.setVisibility(View.VISIBLE);
                name.setEnabled(false);
                dateCreater.setEnabled(false);
                description.setEnabled(false);
                length.setEnabled(false);
                width.setEnabled(false);
                height.setEnabled(false);
                longtitude.setEnabled(false);
            }

            // BORROWED
            if (status_str.equals("Borrowed")){

                name.setEnabled(false);
                dateCreater.setEnabled(false);
                description.setEnabled(false);
                length.setEnabled(false);
                width.setEnabled(false);
                height.setEnabled(false);
                longtitude.setEnabled(false);

                borrower_left_tv.setVisibility(View.VISIBLE);
                borrower_right_tv.setVisibility(View.VISIBLE);
                contact_info_button.setVisibility(View.VISIBLE);
                set_available_button.setVisibility(View.VISIBLE);

                User borrower = item_controller.getBorrower();
                borrower_username_str = borrower.getUsername();
                borrower_right_tv.setText(borrower_username_str);
            }
        }
    }

    public boolean validateInput(){
        if (name_str.equals("")) {
            name.setError("Empty field!");
            return false;
        }

        if (dateCreater_str.equals("")) {
            dateCreater.setError("Empty field!");
            return false;
        }

        if (description_str.equals("")) {
            description.setError("Empty field!");
            return false;
        }

        if (length_str.equals("")) {
            length.setError("Empty field!");
            return false;
        }

        if (width_str.equals("")) {
            width.setError("Empty field!");
            return false;
        }

        if (height_str.equals("")) {
            height.setError("Empty field!");
            return false;
        }

        if (longtitude_str.equals("")) {
            longtitude.setError("Empty field!");
            return false;
        }

        if (Float.valueOf(longtitude_str) <= 0) {
            longtitude.setError("Starting bid must be above 0!");
            return false;
        }

        return true;
    }

    public void viewUserActivity(View view){
        user_list_controller.removeObserver(this);
        item_list_controller.removeObserver(this);

        Intent intent = new Intent(this, ViewUserActivity.class);
        intent.putExtra("borrower_username_str", borrower_username_str);
        startActivity(intent);
    }
}
