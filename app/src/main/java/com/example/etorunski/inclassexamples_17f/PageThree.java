package com.example.etorunski.inclassexamples_17f;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PageThree extends Activity {


    protected final String NAME = "PageThreeActivity";

    Button acceptButton, cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagethree_layout);

        //Get information passed from MainActivity passed through the Extras information
        String fName = getIntent().getStringExtra("FirstName");
        String lName = getIntent().getStringExtra("LastName");


        acceptButton = (Button)findViewById(R.id.acceptButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //About to finish the activity and pass information back:
                Intent passDataBack = new Intent();
                passDataBack.putExtra("Hello", "World");

                //Now set the data to be returned:
                setResult(2, passDataBack);
                // and finish the activity:
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //No data to pass back, just setting resultCode to 3 so onActivityResult knows how the Activity
                // returned there.
                setResult(3);
                finish();
            }
        });
    }
}
