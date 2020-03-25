package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;
import static maes.tech.intentanim.CustomIntent.customType;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void slideLeft(View view){
        startActivity(new Intent(MainActivity.this, StartActivity.class
        ));
        customType(MainActivity.this,"left-to-right");

    }
}
