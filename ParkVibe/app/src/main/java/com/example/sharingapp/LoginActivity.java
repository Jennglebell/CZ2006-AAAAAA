package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Users must log into the app
 */
public class LoginActivity extends AppCompatActivity {
    protected static boolean admin= false;
    private static String username_str;
    private UserList user_list = new UserList();
    private UserListController user_list_controller = new UserListController(user_list);
    private static User user;
    private EditText username;
    private EditText email;
    private Context context;
    //private String username_str;
    private String email_str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        admin = false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        email.setVisibility(View.GONE);
        context = getApplicationContext();
        user_list_controller.loadUsers(context);
    }

    // Admin login
    public void admin(View view){
        admin = true;
        setContentView(R.layout.activity_login_admin);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        email.setVisibility(View.GONE);
        context = getApplicationContext();
        user_list_controller.loadUsers(context);
    }
    // User login
    public void userLogin(View view){
        admin = false;
        setContentView(R.layout.activity_login_user);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        email.setVisibility(View.GONE);
        context = getApplicationContext();
        user_list_controller.loadUsers(context);
    }



    public void login(View view) {
        username_str = username.getText().toString();
        email_str = email.getText().toString();
        String user_id = "";

        if (user_list_controller.getUserByUsername(username_str) == null && email.getVisibility() == View.GONE) {
            email.setVisibility(View.VISIBLE);
            email.setError("New user! Must enter email!");
            return;
        }
        // User does not already have an account
        if (user_list_controller.getUserByUsername(username_str) == null && email.getVisibility() == View.VISIBLE){
            if(!validateInput()){
                return;
            }
            user = new User(username_str, email_str, null, admin);
            UserController user_controller = new UserController(user);
            user_id = user_controller.getId();
            boolean success = user_list_controller.addUser(user, context);
            if (!success){
                return;
            }
            Toast.makeText(context, "Profile created.", Toast.LENGTH_SHORT).show();
        } else { // User already has an account
            user = user_list_controller.getUserByUsername(username_str);
            user_id = user_list_controller.getUserIdByUsername(username_str);

        }

        final Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("admin", admin);
        intent.putExtra("user_id", user_id);
        Toast.makeText(context, "Welcome!", Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }

    public static String getName(){
        return username_str;
    }
    public static User getUser(){
        return user;
    }


    public boolean validateInput(){
        if (email_str.equals("")) {
            email.setError("Empty field!");
            return false;
        }
        if (!email_str.contains("@")) {
            email.setError("Must be an email address!");
            return false;
        }
        if (user_list_controller.getUserByUsername(username_str) != null) {
            username.setError("Username already taken!");
            return false;
        }
        return true;
    }
}
