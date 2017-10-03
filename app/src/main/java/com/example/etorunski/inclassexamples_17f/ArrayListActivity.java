package com.example.etorunski.inclassexamples_17f;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ArrayListActivity extends Activity {

    ArrayList<String> sourceData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sourceData = new ArrayList<>();
        sourceData.add("One");
        sourceData.add("Two");
        sourceData.add("Three");


        setContentView(R.layout.activity_array_list);

        ListView theList = (ListView)findViewById(R.id.listView);

        /* for simple ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.cell_layout,sourceData);

        */
        theList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("ListItemClick", "You clicked an item");
            }
        });
        theList.setAdapter(new MyCustomAdapter());

    }

    private class MyCustomAdapter extends BaseAdapter
    {
        public int getCount() //how many items to display
        {
            return 100;
        }

        public View getView(int position, View recycled, ViewGroup parent)
        {

                LayoutInflater inflater = getLayoutInflater();
                View layout;


                if (recycled == null)
                    layout = inflater.inflate(R.layout.cell_layout, null);
                else
                    layout = recycled;


                TextView testPosition = (TextView) layout.findViewById(R.id.textPos);


                if (position == 1)
                    testPosition.setGravity(Gravity.LEFT);


                testPosition.setText(getItem(position));

                //If it's position 2, return a button
             /*   Button b = new Button(ArrayListActivity.this);
                b.setText("I'm new here");
                if (position == 2)
                    return b;
*/
                return layout;

        }

        public long getItemId(int position)
        {
            return  position;
        }

        public String getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return "First";

                case 1:
                    return "Second";
                case 2:
                    return "Last";
            }
            return "Out of range:" + position;
        }
    }
}
