package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

/**
 * AddProduct class will add and item to the list or create it and add it to the database and the list.
 * @author Jason Steffan, Martin Cornelli, James Clarke
 */
public class AddProduct extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.shoppingapp.MESSAGE";
    public static String FORWARD;
    Product product = new Product();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Item");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String message = intent.getStringExtra(PickExisting.EXTRA_MESSAGE);
        FORWARD = message;
    }

    /**
     * addName adds the product name to the database
     * @param view
     */
    public void addName(View view){
        EditText nameText = (EditText) findViewById(R.id.editText);
        String name = nameText.getText().toString();

        product.setName(name);
    }

    /**
     * addPrice adds the price of a product or item to the database
     * @param view
     */
    public void addPrice(View view){
        EditText priceText = (EditText) findViewById(R.id.editText3);
        String price = priceText.getText().toString();

        product.setPrice(Integer.parseInt(price));
    }

    /**
     * addAisle adds the ailse location to the database
     * @param view
     */
    public void addAisle(View view){
        EditText aisleText = (EditText) findViewById(R.id.editText8);
        String aisle = aisleText.getText().toString();

        product.setAisle(aisle);
    }

    /**
     * addQuantity adds the quantity of the product or item of a product
     * a user wants to the database.
     * @param view
     */
    public void addQuantity(View view){
        EditText aisleText = (EditText) findViewById(R.id.editText4);
        String aisle = aisleText.getText().toString();

        product.setAisle(aisle);
    }

    /**
     * onAddProduct allows user to select an item to add to the list in use.
     * @param view
     */
    public void onAddProduct(View view){
        AppDatabaseHelper newDb = new AppDatabaseHelper(this);
        newDb.addProduct(product);
        newDb.addListProduct(FORWARD, product);

        Intent intent = new Intent(this, UseList.class);
        intent.putExtra(EXTRA_MESSAGE, FORWARD);
        startActivity(intent);
    }


}
