package com.example.shoppingapp;

/**
 * Created by SG0216351 on 11/21/2017.
 */

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.TextView;

public class ListViewActivity extends AppCompatActivity {

    AppDatabaseHelper AppDatabaseHelper;
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    SQLiteListAdapter ListAdapter ;

    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> NAME_ArrayList = new ArrayList<String>();
    ArrayList<String> QUANTITY_ArrayList = new ArrayList<String>();
    ArrayList<String> PRICE_ArrayList = new ArrayList<String>();
    ArrayList<String> VALUE_ArrayList = new ArrayList<String>();
    ListView LISTVIEW;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_list);

        LISTVIEW = (ListView) findViewById(R.id.listView2);

        AppDatabaseHelper = new AppDatabaseHelper(this);

    }

    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        SQLITEDATABASE = AppDatabaseHelper.getWritableDatabase();

        cursor = SQLITEDATABASE.rawQuery("SELECT * FROM product_table", null);

        ID_ArrayList.clear();
        NAME_ArrayList.clear();
        QUANTITY_ArrayList.clear();
        PRICE_ArrayList.clear();
        VALUE_ArrayList.clear();

        if (cursor.moveToFirst()) {
            do {
                ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(AppDatabaseHelper.PRODUCT_ID)));

                NAME_ArrayList.add(cursor.getString(cursor.getColumnIndex(AppDatabaseHelper.PRODUCT_NAME)));

                QUANTITY_ArrayList.add(cursor.getString(cursor.getColumnIndex(AppDatabaseHelper.PRODUCT_QUANTITY)));

                PRICE_ArrayList.add(cursor.getString(cursor.getColumnIndex(AppDatabaseHelper.PRODUCT_PRICE)));

               VALUE_ArrayList.add(cursor.getString(cursor.getColumnIndex(AppDatabaseHelper.PRODUCT_VALUE)));

            } while (cursor.moveToNext());
        }

        ListAdapter = new SQLiteListAdapter(ListViewActivity.this,

                ID_ArrayList,
                NAME_ArrayList,
                QUANTITY_ArrayList,
                PRICE_ArrayList,
                VALUE_ArrayList

        );

        LISTVIEW.setAdapter(ListAdapter);

        cursor.close();
    }
}
