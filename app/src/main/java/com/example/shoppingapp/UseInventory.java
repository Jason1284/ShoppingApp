package com.example.shoppingapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Use Inventory class will allow the user to see the items on each list requested from Choose list
 * or create list and be able to add or remove items from them.
 * @author Jason Steffan, Martin Cornelli, James Clarke
 */

public class UseInventory extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.shoppingapp.MESSAGE";
    public static String FORWARD;

    AppDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_inventory);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar9);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Use Inventory");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String message = intent.getStringExtra(create_list.EXTRA_MESSAGE);
        FORWARD = message;

        TextView textView = (TextView) findViewById(R.id.textView12);
        textView.setText(message);

        SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_NAME2", MODE_PRIVATE).edit();
        editor.putString("inventory", message);
        editor.apply();

        setListView();
    }

    public void onResume(){
        super.onResume();
        setListView();
    }

    public void setListView(){
        // Setup the list view
        final ListView productListView = (ListView) findViewById(R.id.listView3);
        final ProductListAdapterReduced productListAdapterReduced = new ProductListAdapterReduced(this, R.layout.adapter_view_layout_reduced);
        productListView.setAdapter(productListAdapterReduced);

        // Populate the list, through the adapter
        for(final ProductListReduced entry : getProducts()) {
            productListAdapterReduced.add(entry);
        }
    }
    

    /*public void displayAll() {

        ListView listView = (ListView) findViewById(R.id.listView3);
        myDB = new AppDatabaseHelper(this);
        ArrayList<String> theInventory = new ArrayList<>();
        Cursor data = myDB.feedNewList();
        if (data.getCount() == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                theInventory.add(data.getString(1));
            }
            ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theInventory);
            listView.setAdapter(listAdapter);
        }
    }*/
    /**
     * onAddProduct allows user to add products or items to each inventory list in use
     * @param view
     */
    public void onAddProduct(View view){
        Intent intent = new Intent(this, PickExisting.class);
        intent.putExtra(EXTRA_MESSAGE, FORWARD);
        intent.putExtra("caller", "UseInventory");
        startActivity(intent);
    }

    private List<ProductListReduced> getProducts() {

        myDB = new AppDatabaseHelper(this);

        List<ProductListReduced> theInventory = new ArrayList<ProductListReduced>();
        List<Product> receivedInventory = new ArrayList<Product>();
        receivedInventory = myDB.displayInventoryProducts(FORWARD);
        Product tempProduct;
        ProductListReduced productListReduced;
        String tempQuantity = "";
        if(receivedInventory.size() == 0){
            Toast.makeText(this, "There are no contents in this inventory!", Toast.LENGTH_LONG).show();
        } else{
            for (int i = 0; i < receivedInventory.size(); i++){

                tempProduct = receivedInventory.get(i);
                tempQuantity = tempProduct.getQuantity();
                //if (tempProduct.getValue() == 1) {
                //    checked = true;
                //}

                productListReduced = new ProductListReduced(tempProduct.getName(), tempQuantity, tempProduct.getAisle());
                theInventory.add(productListReduced);
            }
        }

        return theInventory;
    }
}
