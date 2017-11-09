package com.example.shoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SendList extends AppCompatActivity {

    Button btnShare;
    Intent shareIntent;
    String shareBody = "This was sent with ACTION_SEND";
    //*** change shareBody to the list we select!**/

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_list);

        Intent intent = getIntent();

        btnShare = findViewById(R.id.button6);

        btnShare.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "My Action Send");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareIntent, "share via"));
            }
        });
    }
}