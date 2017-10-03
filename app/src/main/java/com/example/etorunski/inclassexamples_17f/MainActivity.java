package com.example.etorunski.inclassexamples_17f;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity implements SensorEventListener {
    int i = 0;

    protected final String NAME = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*FullScreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

*/
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
                Vibrator vib = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 500 milliseconds
                vib.vibrate(500);

                Intent nextPage = new Intent(MainActivity.this, PageThree.class);

                startActivityForResult(nextPage, 6);
            }
            });

        Button arrayButton = (Button)findViewById(R.id.arrayListButton);
        arrayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vibrator vib = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                // Vibrate for 200 milliseconds
                vib.vibrate(200);

                startActivity(new Intent(MainActivity.this, ArrayListActivity.class));
            }
        });



        //Sensors:
         SensorManager mSensorManager;
         Sensor mSensor;
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

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

    @Override
    public void onSensorChanged(SensorEvent event) {
            double x = event.values[0];
            double y = event.values[1];
            double z = event.values[2];
            Log.i("Sensor position", String.format("%f %f %f", x, y, z));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
