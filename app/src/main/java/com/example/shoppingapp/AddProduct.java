package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

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

    public void addName(View view){
        EditText nameText = (EditText) findViewById(R.id.editText);
        String name = nameText.getText().toString();

        product.setName(name);
    }

    public void addPrice(View view){
        EditText priceText = (EditText) findViewById(R.id.editText3);
        String price = priceText.getText().toString();

        product.setPrice(Integer.parseInt(price));
    }

    public void addAisle(View view){
        EditText aisleText = (EditText) findViewById(R.id.editText8);
        String aisle = aisleText.getText().toString();

        product.setAisle(aisle);
    }

    public void addQuantity(View view){
        EditText aisleText = (EditText) findViewById(R.id.editText4);
        String aisle = aisleText.getText().toString();

        product.setAisle(aisle);
    }

    public void onAddProduct(View view){
        AppDatabaseHelper newDb = new AppDatabaseHelper(this);
        newDb.addProduct(product);

        newDb.addListProduct(FORWARD, product);
    }

}
