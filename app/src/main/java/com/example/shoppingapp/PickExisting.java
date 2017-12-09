package com.example.shoppingapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Pick Existing class will select an existing list in the database
 * @author Jason Steffan, Martin Cornelli, James Clarke
 */

public class PickExisting extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.shoppingapp.MESSAGE";
    public static String FORWARD;
    Product product = new Product();
    AppDatabaseHelper myDB;
    ListView listView;
    public static String FROM;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_existing);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar7);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pick Item");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String message = intent.getStringExtra(UseList.EXTRA_MESSAGE);
        FROM = intent.getStringExtra("caller");
        FORWARD = message;

        registerClick();
    }

    protected void onResume(){
        super.onResume();
        displayAll();
        //setListView();
        registerClick();
    }

    public void displayAll() {

        ListView listView = (ListView) findViewById(R.id.listViewPick);
        myDB = new AppDatabaseHelper(this);
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.feedNewList();
        String itemRow[] = new String[data.getCount()];
        Product product = new Product();
        int i = 0;
        if (data.getCount() == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                product.setName(data.getString(1));
                itemRow[i] = product.getName();
                theList.add(itemRow[i]);
                i++;
            }
            final ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
            listView.setAdapter(listAdapter);
        }
    }
    /**
     * onAddNew
     * @param view
     */
    public void onAddNew(View view){
        Intent intent = new Intent(this, AddProduct.class);
        intent.putExtra(EXTRA_MESSAGE, FORWARD);
        intent.putExtra("caller", FROM);
        startActivity(intent);
    }

    public void onUseList(View view){
        if (FROM.equals("UseList")) {
            Intent intent = new Intent(this, UseList.class);
            intent.putExtra(EXTRA_MESSAGE, FORWARD);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, UseInventory.class);
            intent.putExtra(EXTRA_MESSAGE, FORWARD);
            startActivity(intent);
        }
    }

    /**
     * registerClick will make the list items clickable so we can send the items in
     * each list to a text app of the users choice.
     * @link https://www.youtube.com/watch?v=eAPFgC9URqc
     * was used to get this part working.
     */
    private void registerClick() {
        listView = (ListView) findViewById(R.id.listViewPick);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private AdapterView parent;

            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                this.parent = parent;
                TextView textView = (TextView) viewClicked;
                String message = "You clicked # " + position + ", which is list: " + textView.getText().toString();
                Toast.makeText(PickExisting.this, message, Toast.LENGTH_SHORT).show();

                String name = textView.getText().toString();
                Product product = new Product();
                product = myDB.findProdByName(name);

                if (FROM.equals("UseList")) {
                    myDB.addListProduct(FORWARD, product);
                }else{
                    myDB.addInventoryProduct(FORWARD, product);
                }
            }
        });
    }
}
