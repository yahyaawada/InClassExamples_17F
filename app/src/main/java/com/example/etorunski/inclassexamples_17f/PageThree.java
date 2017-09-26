package com.example.etorunski.inclassexamples_17f;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PageThree extends Activity {

    Button acceptButton, cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagethree_layout);
        String fName =        getIntent().getStringExtra("FirstName");
        String lName = getIntent().getStringExtra("LastName");

        acceptButton = (Button)findViewById(R.id.acceptButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent passDataBack = new Intent();
                passDataBack.putExtra("Hello", "World");
                setResult(2, passDataBack);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(3);
                finish();
            }
        });
    }
}
