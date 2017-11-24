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

    public void onCreateList(View view){
        AppDatabaseHelper newDb = new AppDatabaseHelper(this);
        Product milk = new Product("milk", 1, "dairy", 3 );
        Product eggs = new Product("eggs", 5, "dairy", 6);
        newDb.addProduct(milk);
        newDb.addProduct(eggs);
        Intent intent = new Intent(this, create_list.class);
        startActivity(intent);
    }

    public void onChooseList(View view){
        AppDatabaseHelper newDb = new AppDatabaseHelper(this);
        //Product milk = new Product("milk", 1, "dairy" );
        //Product eggs = new Product("eggs", 5, "dairy");
        //newDb.addProduct(milk);
        //newDb.addProduct(eggs);
        Intent intent = new Intent(this, ChooseList.class);
        startActivity(intent);
    }

    public void onCreateInventory(View view){
        Intent intent = new Intent(this, CreateInventory.class);
        startActivity(intent);
    }

    public void onChooseInventory(View view){
        Intent intent = new Intent(this, choose_inventory.class);
        startActivity(intent);
    }

    public void onShare(View view){
        Intent intent = new Intent(this, SendList.class);
        startActivity(intent);
    }

    public void onPause(View view){

    }

}
