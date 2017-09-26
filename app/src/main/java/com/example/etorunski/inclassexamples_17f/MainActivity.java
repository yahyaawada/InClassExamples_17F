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

       final SharedPreferences sPrefs = getSharedPreferences("AnyFileName", Context.MODE_PRIVATE);

        SharedPreferences.Editor writer = sPrefs.edit();

        writer.putString("Name", "Eric");
        writer.putString("OtherInfo", "T316");
        writer.commit(); //this writes the file



        if(savedInstanceState != null)
         saved = savedInstanceState.getString("ReservedName");

        Button b1 = (Button)findViewById(R.id.button_one);
        b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String fromFile = sPrefs.getString("Name", "Nothing found");
               fromFile = sPrefs.getString("Not there", "Nothing found");

                   tv.setText("Done in java");

                Bundle someInformation = new Bundle();
                   Intent nextPage = new Intent(MainActivity.this, PageTwo.class);


                   /*Intent newPage = new Intent(Intent.ACTION_VIEW);
                    nextPage.putExtra("FirstName", "Eric");
                    nextPage.putExtra("LastName", "T");
               Uri imgUri = Uri.parse("http://www.cbc.ca");
               newPage.setData(imgUri);*/
               startActivityForResult(nextPage, 5, someInformation);

            }
       });

        b1 = (Button)findViewById(R.id.button_two);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextPage = new Intent(MainActivity.this, PageThree.class);

                startActivityForResult(nextPage, 6);
            }
            });
        Log.e(NAME, "In onCreate");
     }

    protected void onSaveInstanceState (Bundle outState)
    {
        outState.putInt("ReservedName", 56);


    }

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
