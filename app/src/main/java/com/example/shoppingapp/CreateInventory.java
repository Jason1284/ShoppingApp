package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

/**
 * Create Inventory will make a new List of inventory items that the user needs
 * to track and organize.
 * @author Jason Steffan, Martin Cornelli, James Clarke
 */
public class CreateInventory extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.shoppingapp.MESSAGE";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_inventory);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create Inventory");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();


    }

    public void onNewInventory(View view){
        Intent intent = new Intent(this, UseInventory.class);
        EditText editText = (EditText) findViewById(R.id.editText4);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
