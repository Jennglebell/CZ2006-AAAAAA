package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import static maes.tech.intentanim.CustomIntent.customType;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void slideLeft(View view){
        startActivity(new Intent(StartActivity.this, WelcomeActivity.class
        ));
        customType(StartActivity.this,"left-to-right");
    }
}
