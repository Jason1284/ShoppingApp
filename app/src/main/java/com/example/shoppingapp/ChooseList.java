package com.example.shoppingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Choose List class will select a list on items that was already created
 * @author Jason Steffan, Martin Cornelli, James Clarke
 */
public class ChooseList extends AppCompatActivity {

    //ListView listView;
    AppDatabaseHelper myDB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Choose List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        String message = prefs.getString("name", "");

        Context context = getApplicationContext();
        CharSequence text = "The list that was last used was " + message;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);

        if(message != "")
            toast.show();

        displayAll();

        //This is just code awaiting for how the List of list names is going to be passed from the database
        //by J. Clarke
        /*listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(this, UseList.class);
                intent.putExtra("ListName", listView.getItemIdAtPosition(position).toString());
                AppDatabaseHelper newList = new AppDatabaseHelper(this);
                newList.addList(ListName);
                startActivity(intent);
            }
        });*/

    }

    public void onResume(){
        super.onResume();
        displayAll();
    }

    public void displayAll() {

        ListView listView = (ListView) findViewById(R.id.listView);
        myDB = new AppDatabaseHelper(this);
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.allLists();
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
     * onChooseList
     * @param view
     */
    public void onChooseList(View view){
        Intent intent = new Intent(this, UseList.class);
        startActivity(intent);
    }
}


