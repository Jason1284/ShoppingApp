package com.example.shoppingapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Pick Existing class will select an existing list in the database
 * @author Jason Steffan, Martin Cornelli, James Clarke
 */

public class PickExisting extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.shoppingapp.MESSAGE";
    public static String FORWARD;
    Product product = new Product();
    AppDatabaseHelper myDB;
    ListView listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_existing);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar7);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Pick Item");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String message = intent.getStringExtra(UseList.EXTRA_MESSAGE);
        FORWARD = message;

        // Setup the list view
        //final ListView productListViewReduced = (ListView) findViewById(R.id.listViewPick);
        //final ProductListAdapterReduced productListAdapterReduced = new ProductListAdapterReduced(this, R.layout.adapter_view_layout_reduced);
        //productListViewReduced.setAdapter(productListAdapterReduced);

        // Populate the list, through the adapter
        //for(final ProductListReduced entry : getProducts()) {
            //productListAdapterReduced.add(entry);
        //}

        displayAll();
        registerClick();
    }

    protected void onResume(){
        super.onResume();
        displayAll();
        registerClick();
    }

    public void displayAll() {

        ListView listView = (ListView) findViewById(R.id.listViewPick);
        myDB = new AppDatabaseHelper(this);
        ArrayList<String> theList = new ArrayList<>();
        Cursor data = myDB.feedNewList();
        String itemRow[] = new String[data.getCount()];
        Product product = new Product();
        int i = 0;
        if (data.getCount() == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                product.setName(data.getString(1));
                product.setPrice(data.getString(2));
                itemRow[i] = product.getName() + "                                                          $" + product.getPrice();
                theList.add(itemRow[i]);
                i++;
            }
            final ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
            listView.setAdapter(listAdapter);
        }
    }
    private List<ProductListReduced> getProducts() {

        myDB = new AppDatabaseHelper(this);
        float tempPrice = 0;
        List<ProductListReduced> theList = new ArrayList<ProductListReduced>();
        Cursor data = myDB.feedNewList();
        ProductListReduced product;
        if (data.getCount() == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
        } else {
            while (data.moveToNext()) {
                tempPrice = Float.valueOf(data.getString(2));
                product = new ProductListReduced(data.getString(1),  "$" + String.format("%.2f", tempPrice), data.getString(3));
                theList.add(product);
            }
        }
        return theList;
    }
    /**
     * onAddNew
     * @param view
     */
    public void onAddNew(View view){
        Intent intent = new Intent(this, AddProduct.class);
        intent.putExtra(EXTRA_MESSAGE, FORWARD);
        startActivity(intent);
    }

    /**
     * registerClick will make the list items clickable so we can send the items in
     * each list to a text app of the users choice.
     * @link https://www.youtube.com/watch?v=eAPFgC9URqc
     * was used to get this part working.
     */
    private void registerClick() {
        listView = (ListView) findViewById(R.id.listViewPick);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            private AdapterView parent;

            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                this.parent = parent;
                TextView textView = (TextView) viewClicked;
                String message = "You clicked # " + position + ", which is list: " + textView.getText().toString();
                Toast.makeText(PickExisting.this, message, Toast.LENGTH_SHORT).show();

                //add database commands here to add this item selected to the List in use
                //Also needs the option to add to inventory if we came from UseInventory

                //Cursor data = myDB.feedNewList();

                //Intent intent = new Intent(PickExisting.this, UseList.class);
                //String listChosen = textView.getText().toString();
                //String itemChosen = data.getString(1);
                //product.setName(data.getString(1));
                //myDB = new AppDatabaseHelper(PickExisting.this);
                //myDB.addListProduct(listChosen, product);

                //intent.putExtra(EXTRA_MESSAGE, listChosen);
                //startActivity(intent);



            }
        });
    }
}
