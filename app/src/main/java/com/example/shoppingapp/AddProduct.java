package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddProduct extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.shoppingapp.MESSAGE";
    public static String FORWARD;
    Product product = new Product();
    Button addBtn;
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
        FORWARD = message;

        addBtn = (Button) findViewById(R.id.button7);
        newDb = new AppDatabaseHelper(this);

        addBtn.setOnClickListener((new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                EditText nameText = (EditText) findViewById(R.id.editText);
                String name = nameText.getText().toString();

                product.setName(name);

                EditText priceText = (EditText) findViewById(R.id.editText3);
                String price = priceText.getText().toString();

                product.setPrice(Integer.parseInt(price));

                EditText quantityText = (EditText) findViewById(R.id.editText4);
                String quantity = quantityText.getText().toString();

                product.setQuantity(Integer.parseInt(quantity));

                EditText aisleText = (EditText) findViewById(R.id.editText8);
                String aisle = aisleText.getText().toString();

                product.setAisle(aisle);

                newDb.addProduct(product);
                newDb.addListProduct(FORWARD, product);
            }
        }));


    }
    public void onBack(View v) {
        Intent intent = new Intent(this, UseList.class);
        intent.putExtra(EXTRA_MESSAGE, FORWARD);
        startActivity(intent);
    }
}
