package com.example.shoppingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //This is Jason's comment! I hope this works!
        //This is Martin's comment!
        //This is James' comment!
    }

    public void onLoad(View view){
        AppDatabaseHelper newDb = new AppDatabaseHelper(this);
        Product milk = new Product();
        Product eggs = new Product();
        newDb.addProduct(milk);
        newDb.addProduct(eggs);
    }
     public void onShare(View view){

     }
}
