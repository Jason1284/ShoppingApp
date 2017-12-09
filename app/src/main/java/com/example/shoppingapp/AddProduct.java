package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddProduct extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.shoppingapp.MESSAGE";
    public static String FORWARD;
    public static String FROM;
    Product product = new Product();
    //Button addBtn;
    AppDatabaseHelper newDb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Item");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String message = intent.getStringExtra(PickExisting.EXTRA_MESSAGE);
        FROM = intent.getStringExtra("caller");
        FORWARD = message;

        //addBtn = (Button) findViewById(R.id.button7);
        newDb = new AppDatabaseHelper(this);

    }


    public void onAddNew(View view){
        //Getting text
        EditText nameText = (EditText) findViewById(R.id.editText);
        String name = nameText.getText().toString();

        EditText priceText = (EditText) findViewById(R.id.editText3);
        String price = priceText.getText().toString();

        EditText quantityText = (EditText) findViewById(R.id.editText4);
        String quantity = quantityText.getText().toString();

        EditText aisleText = (EditText) findViewById(R.id.editText8);
        String aisle = aisleText.getText().toString();

        //Setting product with input information
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setAisle(aisle);

        //Clearing fields
        nameText.setText("");
        priceText.setText("");
        quantityText.setText("");
        aisleText.setText("");

        //Adding to database
        newDb.addProduct(product);
        Toast.makeText(AddProduct.this, "Item Added!", Toast.LENGTH_LONG).show();
        if (FROM.equals("UseList"))  {
            newDb.addListProduct(FORWARD, product);
        }else{
            newDb.addInventoryProduct(FORWARD, product);
        }
    }

    public void onBack(View v) {
        Intent intent = new Intent(this, PickExisting.class);
        intent.putExtra("caller", FROM);
        intent.putExtra(EXTRA_MESSAGE, FORWARD);
        startActivity(intent);
    }

}


