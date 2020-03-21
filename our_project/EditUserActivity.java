package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Editing a pre-existing user consists of deleting the old user and adding a new user with the old
 * users's id.
 */
public class EditUserActivity extends AppCompatActivity implements Observer {

    private UserList user_list = new UserList();
    private UserListController user_list_controller = new UserListController(user_list);

    private User user;
    private EditText password;
    private TextView username;
    private Context context;
    private boolean on_create_update = true;

    private String password_str;
    private String username_str;
    private boolean isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        // Intent intent = getIntent(); // Get intent from MainActivity
        // user_id = intent.getStringExtra("user_id");

        username = (TextView) findViewById(R.id.username);

        context = getApplicationContext();
        user_list_controller.addObserver(this);
        user_list_controller.loadUsers(context); // First call to update()
        on_create_update = false; // Suppress any further calls to update()
    }

    public void saveUser(View view) {
        password_str = password.getText().toString();
        username_str = username.getText().toString();

        if(!validateInput()){
            return;
        }

        // Reuse the user id
        User updated_user = new User(username_str, password_str, isAdmin);

        boolean success = user_list_controller.editUser(user, updated_user, context);
        if (!success){
            return;
        }

        // End EditUserActivity
        // final Intent intent = new Intent(this, MainActivity.class);
        // intent.putExtra("user_id", user_id);
        Toast.makeText(context, "Profile edited.", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    /**
     * Called when the activity is destroyed, thus we remove this activity as a listener
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        user_list_controller.removeObserver(this);
    }

    /**
     * Only need to update the view from the onCreate method
     */
    public void update(){
        if (on_create_update) {

            user = user_list_controller.getUserByUsername(username);
            UserController user_controller = new UserController(user);

            username = (TextView) findViewById(R.id.username);
            password = (EditText) findViewById(R.id.password);

            username.setText(user_controller.getUsername());
            password.setText(user_controller.getPassword());
        }
    }

    public boolean validateInput(){
        if (password_str.equals("")) {
            password.setError("Empty field!");
            return false;
        }

        // if (!password_str.contains("@")) {
        //     password.setError("Must be an password address!");
        //     return false;
        // }

        // Check that username is unique AND username is changed (Note: if username was not changed
        // then this should be fine, because it was already unique.)
        if (!user_list_controller.isUsernameAvailable(username_str) &&
                !(user.getUsername().equals(username_str))){
            username.setError("Username already taken!");
            return false;
        }


        // check admin exits or not?

//         if (!user_list_controller.isUsernameAvailable(username_str) &&
//         !(user.getUsername().equals(username_str))){
//     username.setError("Username already taken!");
//     return false;
// }
        return true;
    }
}
