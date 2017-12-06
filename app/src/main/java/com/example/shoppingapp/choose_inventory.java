package com.example.shoppingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Choose Inventory classwill select and existing inventory list.
 * @author Jason Steffan, Martin Cornelli, James Clarke
 */

public class choose_inventory extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.shoppingapp.MESSAGE";
    AppDatabaseHelper myDB;
    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_inventory);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar5);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Choose Inventory");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Intent intent = getIntent();

        SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME2", MODE_PRIVATE);
        String message = prefs.getString("inventory", "");

        Context context = getApplicationContext();
        CharSequence text = "The list that was last used was " + message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);

        if(message != "")
            toast.show();

        displayAll();
        registerClick();
    }

    public void onResume(){
        super.onResume();
        displayAll();
        registerClick();
    }

    public void displayAll() {

        ListView listView = (ListView) findViewById(R.id.InventoryView100);
        myDB = new AppDatabaseHelper(this);
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.allInventories();
        if (data.getCount() == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                theList.add(data.getString(1));

            }
            ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
            listView.setAdapter(listAdapter);
        }
    }
    /**
     * onChooseInventory
     * @param view
     */
    public void onChooseInventory(View view){
        Intent intent = new Intent(this, UseInventory.class);
        startActivity(intent);
    }

    /**
     * registerClick will make the list items clickable so we can send the items in
     * each list to a text app of the users choice.
     * @link https://www.youtube.com/watch?v=eAPFgC9URqc
     * was used to get this part working.
     */
    private void registerClick() {
        listView = (ListView) findViewById(R.id.InventoryView100);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private AdapterView parent;

            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                this.parent = parent;
                TextView textView = (TextView) viewClicked;

                //these 2 lines show the toast
                //String message = "You clicked # " + position + ", which is list: " + textView.getText().toString();
                //Toast.makeText(choose_inventory.this, message, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(choose_inventory.this, UseInventory.class);

                //EditText editText = (EditText) findViewById(R.id.editText104);
                String listChosen = textView.getText().toString();

                myDB = new AppDatabaseHelper(choose_inventory.this);

                intent.putExtra(EXTRA_MESSAGE, listChosen);
                startActivity(intent);
            }
        });
    }
}
