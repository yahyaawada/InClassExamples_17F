package com.example.etorunski.inclassexamples_17f;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        CheckBox cb =  (CheckBox)findViewById(R.id.check_box);
        final TextView tv = (TextView) findViewById(R.id.textField);

        Button b1 = (Button)findViewById(R.id.button_one);
        b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

                   tv.setText("Done in java");

            }
       });
     }
}
