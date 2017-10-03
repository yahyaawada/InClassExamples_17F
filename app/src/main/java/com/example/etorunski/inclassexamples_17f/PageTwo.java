package com.example.etorunski.inclassexamples_17f;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PageTwo extends Activity {


    protected final String NAME = "PageTwoActivity";
    Button acceptButton, cancelButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagetwo_layout);

        //Get information passed from MainActivity passed through the Extras information
        String fName = getIntent().getStringExtra("FirstName");
        String lName = getIntent().getStringExtra("LastName");

        acceptButton = (Button)findViewById(R.id.acceptButton);
        cancelButton = (Button)findViewById(R.id.cancelButton);


        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //No data to pass back, just setting resultCode to 55 so onActivityResult knows how the Activity
                // returned there.
                setResult(55);
                finish();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //No data to pass back, just setting resultCode to 12054823 so onActivityResult knows how the Activity
                // returned there.
                setResult(12054823);
                finish();
            }
        });
    }

    protected void onResume()
    {
        super.onResume();
        Log.w(NAME, "In onResume");
    }
    protected void onStart()
    {
        super.onStart();
        Log.e(NAME, "In onStart");
    }
    protected void onStop()
    {
        super.onStop();
        Log.d(NAME, "In onStop");
    }
}
