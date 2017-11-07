package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class ChooseList extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_list);

        Intent intent = getIntent();
    }

    public void onChooseList(View view){
        Intent intent = new Intent(this, UseList.class);
        startActivity(intent);
    }
}


