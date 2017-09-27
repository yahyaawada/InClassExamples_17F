package com.example.etorunski.inclassexamples_17f;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {
    int i = 0;

    protected final String NAME = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        CheckBox cb =  (CheckBox)findViewById(R.id.check_box);
        final TextView tv = (TextView) findViewById(R.id.textField);
        String saved;


        //Open the sharedPreferences file:
        final SharedPreferences sPrefs = getSharedPreferences("AnyFileName", Context.MODE_PRIVATE);


        //If the incoming bundle is not null, then you are passing data to the activity:
        if(savedInstanceState != null)
         saved = savedInstanceState.getString("ReservedName");

        String fromFile = sPrefs.getString("Name", "Nothing found");
        fromFile = sPrefs.getString("Not there", "Nothing found");

        Button b1 = (Button)findViewById(R.id.button_one);
        b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               //Edit the file:
               SharedPreferences.Editor writer = sPrefs.edit();
               //Add entries to the file:
               writer.putString("Name", "Eric");
               writer.putString("OtherInfo", "T316");

               //this writes the file:
               writer.commit();


               tv.setText("Done in java");

               //For passing information to the next Activity, create a bundle:
               Bundle someInformation = new Bundle();
               Intent nextPage = new Intent(MainActivity.this, PageTwo.class);
               nextPage.putExtra("FirstName", "Eric");
               nextPage.putExtra("LastName", "T");


               //To open a webpage using default Intents:
               /*Intent newPage = new Intent(Intent.ACTION_VIEW);
               Uri imgUri = Uri.parse("http://www.cbc.ca");
               newPage.setData(imgUri);*/

               //To transition to the new Activity  with requestCode 5 and pass someInformation:
               startActivityForResult(nextPage, 5, someInformation);

            }
       });

        //For button 2, start a different Activity, with 6 as the request code
        b1 = (Button)findViewById(R.id.button_two);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(MainActivity.this, PageThree.class);

                startActivityForResult(nextPage, 6);
            }
            });
        //Set a logging message at the Error level
        Log.e(NAME, "In onCreate");
     }

     //This gets called before the Activity is stopped. You can save information in memory to be passed
    //back into onCreate(Bundle )
    protected void onSaveInstanceState (Bundle outState)
    {
        outState.putInt("ReservedName", 56);
    }


    //This gets called after the Activity started by startActivity() has ended. You can pass data back here:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        int numReceived = resultCode;
        if(requestCode == 6 && resultCode == 2)
        {
            String dataBack =   data.getStringExtra("Hello");
            Log.i("Info", dataBack);
        }
    }
}
