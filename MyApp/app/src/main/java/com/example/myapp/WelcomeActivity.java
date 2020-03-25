package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static maes.tech.intentanim.CustomIntent.customType;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void fade(View view){
        startActivity(new Intent(WelcomeActivity.this, GetstartedActivity.class
        ));
        customType(WelcomeActivity.this,"fadein-to-fadeout");

    }
}
