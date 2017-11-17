package com.example.shoppingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by SG0216351 on 11/16/2017.
 */

public class PickExisting extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_existing);

        Intent intent = getIntent();

        AppDatabaseHelper newDb = new AppDatabaseHelper(this);
        ArrayList<String> allItems = new ArrayList<String>();
        allItems = newDb.feedNewList();

        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, allItems));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar7);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pick Item");
    }

    public void onAddNew(View view){
        Intent intent = new Intent(this, AddProduct.class);
        startActivity(intent);
    }
}
