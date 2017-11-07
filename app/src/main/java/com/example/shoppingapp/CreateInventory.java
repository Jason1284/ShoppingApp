package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class CreateInventory extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_inventory);

        Intent intent = getIntent();
    }

    public void onNewInventory(View view){
        Intent intent = new Intent(this, UseInventory.class);
        startActivity(intent);
    }
}
