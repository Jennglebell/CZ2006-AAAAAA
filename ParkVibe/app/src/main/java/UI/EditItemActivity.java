package UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.sharingapp.R;

import java.util.ArrayList;
import java.util.List;

import Control.ItemController;
import Control.ItemListController;
import Control.UserListController;
import Model.Item;
import Model.ItemList;
import Model.Observer;
import Model.User;
import Model.UserList;
import Model.oiphoto;
import UI.LoginActivity;
import UI.MainActivity;
import UI.ViewUserActivity;
import UI.oispecificphotosAdapter;

/**
 * Editing a pre-existing item consists of deleting the old item and adding a new item with the old
 * item's id.
 */
public class EditItemActivity extends AppCompatActivity implements Observer, View.OnClickListener {

    private ItemList item_list = new ItemList();
    private ItemListController item_list_controller = new ItemListController(item_list);
    private Item item;
    private ItemController item_controller;
    private Context context;
    private UserList user_list = new UserList();
    private UserListController user_list_controller = new UserListController(user_list);

    private String name_str;
    private String hashtag_str;
    private String description_str;
    private String longti_str;
    private String lati_str;

    private String id_str;


    private TextView like_num;
    private EditText name;
    private EditText hashtag; //hashtag_str/id
    private EditText longti; //coord longitude
    private EditText lati; //coord latitude
    private EditText description;
    private ImageButton add_image_button;
    private ImageButton delete_image_button;
    private ImageButton deleteImage;
    private ImageButton save_button;
    private Button delete_button;
    boolean on_create_update;
    private int REQUEST_CODE = 1;
    private int pos;
    private String status_str;
    private Bitmap image;
    List<Bitmap> images;
    //staggeredRecyclerView
    List<oiphoto> oi_photos_list = getPhotoData();
    //staggeredRecyclerView
    oispecificphotosAdapter adapter;
    RecyclerView staggeredrecyclerView;
    private static final int NUM_COLUMNS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item1);
        name = (EditText) findViewById(R.id.addname);
        hashtag= (EditText) findViewById(R.id.addhastag);
        description = (EditText) findViewById(R.id.adddescription);
        longti = (EditText) findViewById(R.id.maker2a);
        lati = (EditText) findViewById(R.id.maker2b);

        add_image_button = (ImageButton) findViewById(R.id.add_image_button);       // initially GONE
        delete_image_button = (ImageButton) findViewById(R.id.cancel_image_button); // initially GONE
        delete_button = (Button) findViewById(R.id.delete_item);                    // initially GONE
        save_button = (ImageButton) findViewById(R.id.save_button);                      // initially GONE
        deleteImage = (ImageButton) findViewById(R.id.deleteImage);

        Intent intent = getIntent(); // Get intent from ItemsFragment
        pos = intent.getIntExtra("position", 0);
        //user_id = intent.getStringExtra("user_id");
        id_str = intent.getStringExtra("id");
        context = getApplicationContext();

        on_create_update = false; // Suppress first call to update()
        item_list_controller.addObserver(this);
        item_list_controller.loadItems(context);
        on_create_update = true;
        user_list_controller.addObserver(this);
        user_list_controller.loadUsers(context); // Call to update occurs
        on_create_update = false;

        Log.d("EditItemActivity", "Starting RecyclerView");
        staggeredrecyclerView = (RecyclerView) findViewById(R.id.staggeredrecyclerView);
        adapter = new oispecificphotosAdapter(oi_photos_list, this);
        staggeredrecyclerView.setAdapter(adapter);
        staggeredrecyclerView.setLayoutManager(new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL));
        //end staggeredRecyclerView
        Log.d("EditItemActivity", "Ending RecyclerView");
    }



    public void addPhoto(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

//    public void deletePhoto(View view) {
//    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onActivityResult(int request_code, int result_code, Intent intent){
         images = item_controller.getImages();
//        owners = item_controller.getOwners();
//        oi_photos_list = item_controller.getOiphotos();
        if (request_code == REQUEST_CODE && result_code == RESULT_OK){
            Bundle extras = intent.getExtras();
            image = (Bitmap) extras.get("data");
//            likeNums = intent.getIntExtra("like_num", 0);
//            System.out.println("likeNums:"+ likeNums);
            oiphoto oiphoto_ = new oiphoto(image, LoginActivity.getName(), 0);
//            oiphoto_.setLikes(likeNums);
            oi_photos_list.add(oiphoto_);
            images.add(image);
            //owners.add(LoginActivity.getName());
        }
        Toast.makeText(context, "Photo added.", Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void onBackPressed() {
//        Intent main_intent = new Intent(this, MainActivity.class);
//      //  main_intent.putExtra("user_id", user_id);
//        main_intent.putExtra("id", id_str);
//        startActivity(main_intent);
//    }

    public void deleteItem(View view) {
        boolean success = item_list_controller.deleteItem(item, context);
        if (!success){
            return;
        }

        // End EditItemActivity
        item_list_controller.removeObserver(this);
        user_list_controller.removeObserver(this);

        final Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("user_id", user_id);
        intent.putExtra("id", id_str);
        // Delay launch of new activity to allow server more time to process request
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(context, "Item removed.", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        }, 750);
    }

    public void saveItem(View view) {
        name_str = name.getText().toString();
        hashtag_str = hashtag.getText().toString();
        description_str = description.getText().toString();
        longti_str = longti.getText().toString();
        lati_str = lati.getText().toString();
    //    status_str = item_controller.getStatus();

        if(!validateInput()){
            return;
        }
//        System.out.println("save how many photos? " + oi_photos_list.size());
//        System.out.println("save how many likes? " + oi_photos_list.get(0).getLikes());
        String item_id = item_controller.getId(); // Reuse the item id
       // Item updated_item = new Item(hashtag_str, name_str, description_str, image, id_str);
        //Item updated_item = new Item(hashtag_str, name_str, description_str, images, id_str);
        Item updated_item = new Item(hashtag_str, name_str, description_str, oi_photos_list, id_str);
        ItemController updated_item_controller = new ItemController(updated_item);
        updated_item_controller.setLocation(longti_str, lati_str);
     //   updated_item_controller.setStatus(status_str);

        boolean success = item_list_controller.editItem(item, updated_item, context);
        if (!success){
            return;
        }
        item_list_controller.removeObserver(this);
        user_list_controller.removeObserver(this);
        // End EditItemActivity
        final Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra("user_id", user_id);
        Toast.makeText(context, "Item saved.", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }


    /**
     * Only need to update the view once from the onCreate method
     */
    public void update() {
        if (on_create_update) {
            // For all status options we do the following
//            System.out.println("selected item id:" + hashtag_str);
            item = item_list_controller.getItemById(id_str);
            item_controller = new ItemController(item);

            name.setText(item_controller.getName());
            hashtag.setText(item_controller.getHashtag());
            description.setText(item_controller.getDescription());
            longti.setText(item_controller.getLonti());
            longti.setText(item_controller.getLonti());
            lati.setText(item_controller.getLati());

         //   status_str = item_controller.getStatus();
            images = item_controller.getImages();
            List<oiphoto> oi_photos_list_ = item_controller.getOiphotos();
//            if (images != null) {
//                for (Bitmap image : images) {
//                    System.out.println("get images size" + images.size());
//                    oiphoto oiphoto_ = new oiphoto(image);
//                    oi_photos_list.add(oiphoto_);
//                }
//            }
            System.out.println("get oiphoto size" + oi_photos_list_.size());
            System.out.println("get images size" + images.size());
                if (oi_photos_list_ != null) {
                    for (oiphoto oiphoto_ : oi_photos_list_) {
//                        System.out.println("Owner has:" + oiphoto_.getOwner());
//                        System.out.println("Like has:" + oiphoto_.getLikes());
                        oi_photos_list.add(oiphoto_);
                    }
                }


//                System.out.println("who is the user?: " + LoginActivity.getName());
                String username = LoginActivity.getName();

                User user = user_list_controller.getUserByUsername(username);
                boolean admin = user.getAdmin();
                if (!admin) {
//                    for(oiphoto oiphoto_ : oi_photos_list){
//                        if(oiphoto_.getOwner().equals(LoginActivity.getUser())){
//                          //  oiphoto.isOwner = true;
//                            //oiphoto_.getdelete_image_button.setVisibility(View.VISIBLE);
//                        }
//                    }

                    name.setEnabled(false);
                    hashtag.setEnabled(false);
                    description.setEnabled(false);
                    lati.setEnabled(false);
                    longti.setEnabled(false);
                    delete_button.setVisibility(View.INVISIBLE);
//                delete_button.setVisibility(View.GONE);
                } else {

                    //if (status_str.equals("Available")) {
                    name.setEnabled(true);
                    hashtag.setVisibility(View.VISIBLE);
                    hashtag.setEnabled(true);
                    description.setEnabled(true);
                    lati.setVisibility(View.VISIBLE);
                    lati.setEnabled(true);
                    longti.setVisibility(View.VISIBLE);
                    longti.setEnabled(true);
                    delete_button.setVisibility(View.VISIBLE);
                    //delete_button.setVisibility(View.VISIBLE);
                }

                add_image_button.setVisibility(View.VISIBLE);
                delete_image_button.setVisibility(View.VISIBLE);
                save_button.setVisibility(View.VISIBLE);

            }
        }

    public boolean validateInput(){
        return true;
    }

    public void viewUserActivity(View view){
        user_list_controller.removeObserver(this);
        item_list_controller.removeObserver(this);

        Intent intent = new Intent(this, ViewUserActivity.class);
//        intent.putExtra("borrower_username_str", borrower_username_str);
        startActivity(intent);
    }


    private List<oiphoto> getPhotoData() {
        List<oiphoto> oiphotolist = new ArrayList<>();
//        oiphotolist.add(new oiphoto( "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTL_ePmnP8RJ0nxNKy4cMbe0wdroWNNZyiFO9fK9dPTjjEUkauz"));
//        oiphotolist.add(new oiphoto("https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
//        //oiphotolist.add(new oiphoto("https://www.nparks.gov.sg/-/media/nparks-real-content/gardens-parks-and-nature/parks-and-nature-reserve/labrador-nature-reserve/rsz_bbq_pits.jpg?h=149&la=en&mw=237&w=237&hash=49CCF327B01E02A10114038FE6FBD977B8BF4052"));
//        oiphotolist.add(new oiphoto("https://upload.wikimedia.org/wikipedia/commons/e/eb/Ash_Tree_-_geograph.org.uk_-_590710.jpg"));
//        oiphotolist.add(new oiphoto("https://s3-ap-southeast-1.amazonaws.com/c3a-java-files/73c8d717302fbb8097a6e96c328f264e.jpg"));
//        oiphotolist.add(new oiphoto("https://upload.wikimedia.org/wikipedia/commons/e/eb/Ash_Tree_-_geograph.org.uk_-_590710.jpg"));

        //GET ALL OI DATA HERE. above is dummy data. add to the oilist.
        return oiphotolist;
    }
}
