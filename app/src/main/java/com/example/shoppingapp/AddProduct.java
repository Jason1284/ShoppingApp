package com.example.shoppingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Created by James on 11/3/2017.
 */

public class AddProduct extends AppCompatActivity {

    Product product = new Product();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product);
    }

    public void addName(View view){
        EditText nameText = (EditText) findViewById(R.id.editText);
        String name = nameText.getText().toString();

        product.setName(name);
    }

    public void addPrice(View view){
        EditText priceText = (EditText) findViewById(R.id.editText2);
        String price = priceText.getText().toString();

        product.setPrice(Integer.parseInt(price));
    }

    public void addAisle(View view){
        EditText aisleText = (EditText) findViewById(R.id.editText3);
        String aisle = aisleText.getText().toString();

        product.setAisle(aisle);
    }

    public void onAddProduct(View view){
        AppDatabaseHelper newDb = new AppDatabaseHelper(this);
        newDb.addProduct(product);
    }
}
