package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by James on 11/3/2017.
 */

public class ChooseList extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_list);

        Intent intent = getIntent();
    }

    public void onChooseList(View view){
        Intent intent = new Intent(this, UseList.class);
        startActivity(intent);
    }
}


