package com.example.shoppingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Send List class will take a list from the database and convert into a HTML formatted text to be sent
 * by an app of the user's choice, such as email or text message.
 * @author Jason Steffan, Martin Cornelli, James Clarke
 * @link https://developer.android.com/training/sharing/send.html
 */
public class SendList extends AppCompatActivity {

    Button btnShare;
    Intent shareIntent;
    String shareBody = "This was sent with ACTION_SEND";
    AppDatabaseHelper myDB;
    /**
     * onCreate will allow the user to select a list to be sent vie HTML formatted text.
     * @param savedInstanceState to help the device save when app is paused.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar8);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Send List");
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

        /* change shareBody to take the list we selected like in choose list/inventory!*/
        // shareBody = selectedList;

        btnShare = findViewById(R.id.button6);

        btnShare.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/html");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Action Send");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareIntent, "share via"));
            }
        });

        displayAll();
    }

    public void displayAll() {

        ListView listView = (ListView) findViewById(R.id.listView4);
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
}