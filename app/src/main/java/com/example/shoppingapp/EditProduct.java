package com.example.shoppingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Edit Product class will help to make updates and changed to items in the database.
 * @author Jason Steffan, Martin Cornelli, James Clarke
 */
public class EditProduct extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_modify_product);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar6);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Product");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * onEditProduct
     * @param name
     */
    public void onEditProduct(String name){}

}
