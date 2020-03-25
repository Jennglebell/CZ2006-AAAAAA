package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import static maes.tech.intentanim.CustomIntent.customType;

public class GetstartedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getstarted);
    }
    public void fade(View view){
        startActivity(new Intent(GetstartedActivity.this, LoginActivity.class
        ));
        customType(GetstartedActivity.this,"fadein-to-fadeout");

    }
}
