package com.example.shoppingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

/**
 * Create List class will make a new list in the database so items can be added to it.
 * @author Jason Steffan, Martin Cornelli, James Clarke
 */

public class create_list extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.shoppingapp.MESSAGE";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
    }

    /**
     * onNewList
     * @param view
     */
    public void onNewList(View view){
        Intent intent = new Intent(this, UseList.class);

        EditText editText = (EditText) findViewById(R.id.editText104);
        String message = editText.getText().toString();

        AppDatabaseHelper newList = new AppDatabaseHelper(this);
        newList.addList(message);

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
