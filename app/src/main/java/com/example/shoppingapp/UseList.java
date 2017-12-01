package com.example.shoppingapp;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.ListActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 20171104.
 */

public class UseList extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.shoppingapp.MESSAGE";
    public static String FORWARD;

    AppDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_list);

        //Button btnAdd;

        //btnAdd = (Button) findViewById(R.id.button12);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar10);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Shopping List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String message = intent.getStringExtra(create_list.EXTRA_MESSAGE);
        FORWARD = message;

        TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText(message);

        SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit();
        editor.putString("name", message);
        editor.apply();

        // Setup the list view
        final ListView productListView = (ListView) findViewById(R.id.listView2);
        final ProductListAdapter productListAdapter = new ProductListAdapter(this, R.layout.adapter_view_layout);
        productListView.setAdapter(productListAdapter);

        // Populate the list, through the adapter
        for(final ProductList entry : getProducts()) {
            productListAdapter.add(entry);
        }

                /*btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(UseList.this, PickExisting.class);
                intent.putExtra(EXTRA_MESSAGE, FORWARD);
                startActivity(intent);
            }
        });*/
    }

        private List<ProductList> getProducts() {

            /*final List<ProductList> entries = new ArrayList<ProductList>();

            for(int i = 1; i < 50; i++) {
                entries.add(
                        new ProductList(
                                "7 ",
                                "Pizza entry " + i,
                                "$10"
                        )
                );
            }

            return entries;*/
            myDB = new AppDatabaseHelper(this);
            List<ProductList> theList = new ArrayList<ProductList>();
            Cursor data = myDB.feedNewList();
            ProductList product;
            if (data.getCount() == 0) {
                Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
            } else {
                while (data.moveToNext()) {
                    product = new ProductList(data.getString(5), data.getString(1), data.getString(2));
                    theList.add(product);
                }
            }
            return theList;
        }



    public void onAdd(View view){
        Intent intent = new Intent(UseList.this, PickExisting.class);
        intent.putExtra(EXTRA_MESSAGE, FORWARD);
        startActivity(intent);
    }

    public void onBoxCheck(View view){

    }

    public void onListRestore(View view) {

    }
}
