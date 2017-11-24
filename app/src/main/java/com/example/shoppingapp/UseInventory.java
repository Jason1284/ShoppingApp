package com.example.shoppingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Use Inventory will allow the user to see the items on each list requested from Choose list
 * or create list and be able to add or remove items from them.
 * @author Jason Steffan, Martin Cornelli, James Clarke
 */

public class UseInventory extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.shoppingapp.MESSAGE";
    public static String FORWARD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_inventory);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar9);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Use Inventory");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String message = intent.getStringExtra(create_list.EXTRA_MESSAGE);
        FORWARD = message;

        TextView textView = (TextView) findViewById(R.id.textView12);
        textView.setText(message);

        AppDatabaseHelper newDb = new AppDatabaseHelper(this);
        ArrayList<String> allItems = new ArrayList<String>();
        allItems = newDb.feedNewList();

        ListView lv = (ListView) findViewById(R.id.listView3);
        //lv.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, allItems));

        SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_NAME2", MODE_PRIVATE).edit();
        editor.putString("inventory", message);
        editor.apply();
    }

    public void onAddProduct(View view){
        Intent intent = new Intent(this, AddProduct.class);
        intent.putExtra(EXTRA_MESSAGE, FORWARD);
        startActivity(intent);
    }
}
