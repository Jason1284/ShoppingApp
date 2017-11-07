package com.example.shoppingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Jason on 20170311.
 */

public class create_list extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        Intent intent = getIntent();
    }

    public void onNewList(View view){
        Intent intent = new Intent(this, UseList.class);
        startActivity(intent);
    }
}
