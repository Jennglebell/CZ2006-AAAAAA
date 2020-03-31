package UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import Model.Item;
import Control.ItemController;
import Model.ItemList;
import Control.ItemListController;
import Model.Observer;
import com.example.sharingapp.R;
import Model.UserList;
import Control.UserListController;

import java.util.ArrayList;
import java.util.List;

public class ViewItemActivity extends AppCompatActivity implements Observer {

    private ItemList item_list = new ItemList();
    private ItemListController item_list_controller = new ItemListController(item_list);

    private Item item;
    private ItemController item_controller;


    private Context context;

    private UserList user_list = new UserList();

    private UserListController user_list_controller = new UserListController(user_list);

    private List<Bitmap> images;
    int numOfphotoViews = 2;
    private List<ImageView> photos;
    private ImageView photo1;
    private ImageView photo2;

    private TextView title_tv;
    private TextView maker_tv;
    private TextView description_tv;
    private TextView length_tv;
    private TextView width_tv;
    private TextView height_tv;
    private Button return_item_button;

    private boolean on_create_update;

    private String title_str;
    private String maker_str;
    private String description_str;
    private String length_str;
    private String width_str;
    private String height_str;
    private String user_id;
    private String item_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_item);

        title_tv = (TextView) findViewById(R.id.title_right_tv);
        maker_tv = (TextView) findViewById(R.id.maker_right_tv);
        description_tv = (TextView) findViewById(R.id.description_right_tv);
        length_tv = (TextView) findViewById(R.id.length_tv);
        height_tv = (TextView) findViewById(R.id.height_tv);
        photo1 = (ImageView) findViewById(R.id.image_view);
        //photo2 = (ImageView) findViewById(R.id.image_view2);
        photos = new ArrayList<>();
        photos.add(photo1);
        photos.add(photo2);


        Intent intent = getIntent(); // Get intent from BorrowedItemsActivity/SearchActivity
        item_id = intent.getStringExtra("item_id");
        user_id = intent.getStringExtra("user_id");

        context = getApplicationContext();

        on_create_update = false; // Suppress first call to update()
        user_list_controller.loadUsers(context);

        on_create_update = true; // First call to update occurs now
        item_list_controller.addObserver(this);
        item_list_controller.loadItems(context);

        on_create_update = false; // Suppress any further calls to update()
    }


    public void showPhotos(int numOfPhotoViews){
        images = item_controller.getImages();
        int i =0;
        for(Bitmap image : images) {
            if (image != null && numOfPhotoViews>0) {
                photos.get(i).setImageBitmap(image);
            } else {
                photos.get(i).setImageResource(android.R.drawable.ic_menu_gallery);
            }
            i++;
            numOfPhotoViews--;
        }
    }

    public void update() {
        if (on_create_update) {
            // For all status options we do the following
            item = item_list_controller.getItemById(item_id);
            item_controller = new ItemController(item);

            title_tv.setText(item_controller.getName());
            maker_tv.setText(item_controller.getHashtag());
            description_tv.setText(item_controller.getDescription());

            length_tv.setText(item_controller.getLati());
            width_tv.setText(item_controller.getLonti());
            showPhotos(numOfphotoViews);
        }
    }

    public boolean validateInput(){
        return true;
    }
}
