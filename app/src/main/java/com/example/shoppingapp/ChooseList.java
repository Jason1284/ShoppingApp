package com.example.shoppingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Choose List class will select a list on items that was already created
 * @author Jason Steffan, Martin Cornelli, James Clarke
 */
public class ChooseList extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.shoppingapp.MESSAGE";
    ListView listView;
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
        registerClick();

    }

    public void onResume(){
        super.onResume();
        displayAll();
        registerClick();
    }

    public void displayAll() {

        ListView listView = (ListView) findViewById(R.id.listView21);
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

    /**
     * registerClick will make the list items clickable so we can send the items in
     * each list to a text app of the users choice.
     * @link https://www.youtube.com/watch?v=eAPFgC9URqc
     * was used to get this part working.
     */
    private void registerClick() {
        listView = (ListView) findViewById(R.id.listView21);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private AdapterView parent;

            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                this.parent = parent;
                TextView textView = (TextView) viewClicked;

                //String message = "You clicked # " + position + ", which is list: " + textView.getText().toString();
                //Toast.makeText(ChooseList.this, message, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ChooseList.this, UseList.class);
                String listChosen = textView.getText().toString();

                myDB = new AppDatabaseHelper(ChooseList.this);

                intent.putExtra(EXTRA_MESSAGE, listChosen);
                startActivity(intent);
            }
        });
    }
}


