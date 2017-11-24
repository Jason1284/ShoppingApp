package com.example.shoppingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
/**
 * This Shopping app will help the user create lists to use while shopping and create lists
 * of items on hand at their home.
 * @author Jason Steffan, Martin Cornelli, James Clarke
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //This is Jason's comment! I hope this works!
        //This is Martin's comment!
        //This is James' comment!
    }

    /**
     * onCreateList Creates a list for products or items to be added to.
     * @param view
     */
    public void onCreateList(View view){
        AppDatabaseHelper newDb = new AppDatabaseHelper(this);
        Product milk = new Product("milk", 1, "dairy", 3 );
        Product eggs = new Product("eggs", 5, "dairy", 6);
        newDb.addProduct(milk);
        newDb.addProduct(eggs);
        Intent intent = new Intent(this, create_list.class);
        startActivity(intent);
    }

    /**
     * onChooseList Shows the lists that are in the database for products
     * or items to be added to.
     * @param view
     */
    public void onChooseList(View view){
        AppDatabaseHelper newDb = new AppDatabaseHelper(this);
        //Product milk = new Product("milk", 1, "dairy" );
        //Product eggs = new Product("eggs", 5, "dairy");
        //newDb.addProduct(milk);
        //newDb.addProduct(eggs);
        Intent intent = new Intent(this, ChooseList.class);
        startActivity(intent);
    }

    /**
     * onCreateInventory Creates a Inventory list for products or items to be added
     * to, that the user already has on hand at home.
     * @param view
     */
    public void onCreateInventory(View view){
        Intent intent = new Intent(this, CreateInventory.class);
        startActivity(intent);
    }

    /**
     * onChooseInventory Shows the Inventory lists that are in the database for products
     * or items to be added to.
     * @param view
     */
    public void onChooseInventory(View view){
        Intent intent = new Intent(this, choose_inventory.class);
        startActivity(intent);
    }

    /**
     * onShare Allows the user to share a list of items with someone else via text
     * in an email or text message.
     * @param view
     */
    public void onShare(View view){
        Intent intent = new Intent(this, SendList.class);
        startActivity(intent);
    }

    /**
     * onPause saves the state of the app when the user switches to something else.
     * @param view
     */
    public void onPause(View view){

    }

}
