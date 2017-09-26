package com.example.etorunski.inclassexamples_17f;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PageTwo extends Activity {

    Button acceptButton, cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagetwo_layout);
        String fName =        getIntent().getStringExtra("FirstName");
        String lName = getIntent().getStringExtra("LastName");

        acceptButton = (Button)findViewById(R.id.acceptButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);


        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(55);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(12054823);
                finish();
            }
        });
    }

    protected void onResume()
    {   super.onResume();

    }
    protected void onStart()
    {   super.onStart();

    }
    protected void onStop()
    {   super.onStop();

    }
}
