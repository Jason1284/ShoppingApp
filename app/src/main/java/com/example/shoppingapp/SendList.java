package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

/**
 * This Class will take a list from the database and convert into a HTML formatted text to be sent
 * by an app of the user's choice, such as email or text message.
 * @author James Clarke
 * @link https://developer.android.com/training/sharing/send.html
 */
public class SendList extends AppCompatActivity {

    Button btnShare;
    Intent shareIntent;
    String shareBody = "This was sent with ACTION_SEND";
    /**
     * onCreate will allow the user to select a list to be sent vie HTML formatted text.
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar8);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Send List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Intent intent = getIntent();

        /** change shareBody to take the list we selected like in choose list/inventory!*/
        // shareBody = selectedList;

        btnShare = findViewById(R.id.button6);

        btnShare.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/html");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Action Send");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareIntent, "share via"));
            }
        });
    }
}