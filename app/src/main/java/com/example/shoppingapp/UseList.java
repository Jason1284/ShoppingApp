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
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.ListActivity;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jason on 20171104.
 */

public class UseList extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.shoppingapp.MESSAGE";
    public static String FORWARD;

    AppDatabaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_list);

        //Tool Bar
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

        setListView();
        //displayAll();

    }

    protected void onResume(){
        super.onResume();
        setListView();
        //displayAll();
    }

    public void setListView(){
        // Setup the list view
        final ListView productListView = (ListView) findViewById(R.id.listView2);
        final ProductListAdapter productListAdapter = new ProductListAdapter(this, R.layout.adapter_view_layout);
        productListView.setAdapter(productListAdapter);

        // Populate the list, through the adapter
        for(final ProductList entry : getProducts()) {
            productListAdapter.add(entry);
        }
    }

        //I'M COMMENTING THIS OUT BECAUSE IT'S WORKING CODE, BUT I NEED TO TEST GETTING EVERYTHING FROM
        //LISTPRODUCT TABLE INSTEAD OF PRODUCT -- MARTIN.
        /*private List<ProductList> getProducts() {

            float rowPrice = 0;
            float rowQuantity = 0;
            float finalTotal = 0;
            float tempPrice = 0;
            Boolean checked = false;


            myDB = new AppDatabaseHelper(this);
            List<ProductList> theList = new ArrayList<ProductList>();
            Cursor data = myDB.feedNewList();
            ProductList product;
            if (data.getCount() == 0) {
                Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
            } else {
                while (data.moveToNext()) {
                    if (data.getString(6) == "1") {
                        checked = true;
                    }
                    tempPrice = Float.valueOf(data.getString(2));
                    product = new ProductList(data.getString(5), data.getString(1), "$" + String.format("%.2f", tempPrice), data.getString(3), checked);
                    theList.add(product);
                    rowPrice = Float.valueOf(data.getString(2));
                    rowQuantity = Float.valueOf(data.getString(5));
                    finalTotal += (rowPrice * rowQuantity);
                }
            }


            TextView updateTotal = (TextView) findViewById(R.id.textView7);
            updateTotal.setText("$" + String.format("%.2f", finalTotal));

            return theList;
        }*/

    //This should display only the products related to the current list
    private List<ProductList> getProducts() {

        float rowPrice = 0;
        float rowQuantity = 0;
        float finalTotal = 0;
        float tempPrice = 0;
        String tempAisle;
        Boolean checked = false;
        myDB = new AppDatabaseHelper(this);

        List<ProductList> theList = new ArrayList<ProductList>();
        List<Product> receivedList = new ArrayList<Product>();
        receivedList = myDB.displayListProducts(FORWARD);
        Product tempProduct;
        ProductList productList;
        String tempQuantity = "";
        if(receivedList.size() == 0){
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show();
        } else{
            for (int i = 0; i < receivedList.size(); i++){

                tempProduct = receivedList.get(i);
                tempQuantity = tempProduct.getQuantity();
                tempPrice = Float.valueOf(tempProduct.getPrice());
                //if (tempProduct.getValue() == 1) {
                //    checked = true;
                //}
                productList = new ProductList(/*tempProduct.getQuantity()*/tempQuantity, tempProduct.getName(), "$" + String.format("%.2f", tempPrice), tempProduct.getAisle()/*, checked*/);
                theList.add(productList);
                rowPrice = Float.valueOf(tempProduct.getPrice());
                rowQuantity = Float.valueOf(tempQuantity);

                finalTotal += (rowPrice * rowQuantity);
            } 
        }

        TextView updateTotal = (TextView) findViewById(R.id.textView7);
        updateTotal.setText("$" + String.format("%.2f", finalTotal));

        return theList;
    }

    public void displayAll() {

        ListView listView = (ListView) findViewById(R.id.listView2);
        myDB = new AppDatabaseHelper(this);
        List<Product> theList = new ArrayList<Product>();
        List<String> toDisplay = new ArrayList<String>();
        theList = myDB.displayListProducts(FORWARD);
        String itemRow[] = new String[theList.size()];
        for (int i = 0; i < theList.size(); i++){
            itemRow[i] = theList.get(i).getName() + "                                                          $" + theList.get(i).getPrice();
            toDisplay.add(itemRow[i]);
        }

        final ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, toDisplay);
        listView.setAdapter(listAdapter);

    }

    public void onAdd(View view){
        Intent intent = new Intent(UseList.this, PickExisting.class);
        intent.putExtra(EXTRA_MESSAGE, FORWARD);
        startActivity(intent);
    }

    public void onBoxCheck(View view){

    }

    public void onListRestore(View view) {

    }
}

