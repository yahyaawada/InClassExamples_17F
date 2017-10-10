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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity implements SensorEventListener {
    int i = 0;
    String [] sourceData = { "Activity two", "Activity Three",
            "Base adapter example", "Open Web Page",
            "Vibrate phone", "SQL Example"};

    private boolean listenToAccelerometer;

    protected final String NAME = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*FullScreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        */
        setContentView(R.layout.activity_array_list);



        //Open the sharedPreferences file:
        final SharedPreferences sPrefs = getSharedPreferences("AnyFileName", Context.MODE_PRIVATE);


        //Seeing what items are in the sharedPreferences file:
        String fromFile = sPrefs.getString("Name", "Nothing found");
        fromFile = sPrefs.getString("Not there", "Nothing found");


        //Creating an array adapter for the listView
        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(new ArrayAdapter<String>(this, R.layout.cell_layout, sourceData));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nextPage;

                switch(position) {
                    case 0://Activity one
                        //Edit the file:
                        SharedPreferences.Editor writer = sPrefs.edit();
                        //Add entries to the file:
                        writer.putString("Name", "Eric");
                        writer.putString("OtherInfo", "T316");

                        //this writes the file:
                        writer.commit();

                        //For passing information to the next Activity, create a bundle:
                        Bundle someInformation = new Bundle();
                        nextPage = new Intent(MainActivity.this, PageTwo.class);
                        nextPage.putExtra("FirstName", "Eric");
                        nextPage.putExtra("LastName", "T");


                        //To transition to the new Activity  with requestCode 5 and pass someInformation:
                        startActivityForResult(nextPage, 5, someInformation);
                        break;

                    case 1://Activity two:
                        nextPage = new Intent(MainActivity.this, PageThree.class);
                        startActivityForResult(nextPage, 6);
                        break;

                    case 2:
                        startActivity(new Intent(MainActivity.this, ArrayListActivity.class));
                        break;

                    case 3:  //To open a webpage using default Intents:
                        Intent newPage = new Intent(Intent.ACTION_VIEW);
                        Uri imgUri = Uri.parse("http://www.cbc.ca");
                        newPage.setData(imgUri);

                        //To transition to the new Activity  with requestCode 5 and pass someInformation:
                        startActivity(newPage);
                        break;

                    case 4:
                        Vibrator vib = (Vibrator) MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                        // Vibrate for 200 milliseconds
                        vib.vibrate(200);
                        break;
                    case 5://SQL EXAMPLE
                        startActivity(new Intent(MainActivity.this, SQLExample.class));
                        break;
                }
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
    public void onResume()
    {
        super.onResume();
        listenToAccelerometer = true;
    }

    //This gets called when the orientation of the phone changes.
    @Override
    public void onSensorChanged(SensorEvent event) {
            double x = event.values[0];
            double y = event.values[1];
            double z = event.values[2];
            Log.i("Sensor position", String.format("%f %f %f", x, y, z));
            if(listenToAccelerometer && (z > y)) //If the phone is being help upright
            {
                startActivity(new Intent(this, ArrayListActivity.class));
                listenToAccelerometer=false;
            }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
