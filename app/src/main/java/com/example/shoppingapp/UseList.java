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
import android.widget.ListView;
import android.widget.TextView;
import android.app.ListActivity;

import java.util.ArrayList;

/**
 * Created by Jason on 20171104.
 */

public class UseList extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.shoppingapp.MESSAGE";
    public static String FORWARD; 
    Cursor cursor;
    CustomCursorAdapter myCursorAdapter;
    ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_list);

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

        /*
        private void createListView() {
            setContentView(R.layout.main);

            cursor=dh.query(DatabaseHelper.USER_PASSWORD, new String[]{“_id”,”username”,”selected”}, null, null, null, null, “firstname asc”);

            startManagingCursor(cursor);
            myCursorAdapter= new CustomCursorAdapter(this,cursor);
            this.getListView().setAdapter(myCursorAdapter);
        }

        private void insertData(String firstName ,String password){
            if(values!= null){
                values.clear();
            }
            values.put(“username”,firstName);
            values.put(“password”, password);
            dh.insert(DatabaseHelper.USER_PASSWORD, null, values);
        }

        private void clickHandler(View view){

            if(view.getId() == R.id.checkbox){
                cursor.requery();
            }
        }*/


    }

    public void onAddProduct(View view){
        Intent intent = new Intent(this, PickExisting.class);
        intent.putExtra(EXTRA_MESSAGE, FORWARD);
        startActivity(intent);
    }


    public void onBoxCheck(View view){

    }

    public void onListRestore(View view) {

    }
}
