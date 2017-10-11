package com.example.etorunski.inclassexamples_17f;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class SQLExample extends Activity {

    private final static String name = "MyTable";
    private final static String DATABASE_NAME = "Filename.db";
    private final static int VERSION_NUM = 1;
    private final static String ASSIGNMENT = "ASSIGNMENT";
    private final static String GRADE = "GRADE";
    private final static String COMMENT = "COMMENT";
    private final static String ID = "_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlexample);

        //Create an object for opening a database:
        MyOpenHelper aHelperObject = new MyOpenHelper(this);
        final SQLiteDatabase db = aHelperObject.getWritableDatabase();
        Button insButton = (Button) findViewById(R.id.insert_button);

        //This does a query and stores the results:
        Cursor results = db.query(false, name, new String[] {ID, ASSIGNMENT, COMMENT},
                null, null , null, null, null, null);

        //How many rows in the results:
        int numResults = results.getCount();

        //How many columns in the results:
        int numColumns = results.getColumnCount();

        int assignmentIndex = results.getColumnIndex(ASSIGNMENT);
        int commentIndex = results.getColumnIndex(COMMENT);

        results.moveToFirst();//resets the iteration of results
        //This is an example of a SimpleCursorAdapter to fill a list view with database results:
        int [] arr = new int[]{R.id.assignment_name, R.id.comment};
        ListView lv = (ListView)findViewById(R.id.list_results);
        SimpleCursorAdapter adptr = new SimpleCursorAdapter(this, R.layout.cursor_layout, results,
                new String[] {ASSIGNMENT, COMMENT, ID},
                arr , 0);

        String returnedComment, returnedName;
        lv.setAdapter(adptr);

        for(int i = 0; i < numResults; i++) {
            returnedComment = results.getString(commentIndex);
            returnedName = results.getString(assignmentIndex);
            Log.i("Results:", returnedComment + " : " + returnedName);
            results.moveToNext();
        }

        results.moveToFirst();//resets the iteration of results
        while(!results.isAfterLast())
        {
            returnedComment = results.getString(commentIndex);
            returnedName = results.getString(assignmentIndex);
            Log.i("Results:", returnedComment + " : " + returnedName);
            results.moveToNext();
        }


        // When you click on the button:
        insButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String assignmentName, comment, grade;
                //get the strings typed:
                assignmentName=((EditText)findViewById(R.id.assignment_field)).getText().toString();
                comment = ((EditText)findViewById(R.id.comment_field)).getText().toString();
                grade = ((EditText)findViewById(R.id.grade_field)).getText().toString();

                //Use a content values object to insert them in the database
                ContentValues newData = new ContentValues();
                newData.put(ASSIGNMENT, assignmentName);
                newData.put(GRADE, grade);
                newData.put(COMMENT, comment);

                //Then insert
                db.insert(name, "" , newData);
            }
        });
    }

    // this is responsible for creating, opening and reading database
    protected class MyOpenHelper extends SQLiteOpenHelper
    {


        public MyOpenHelper(Context ctx)
        {
            super(ctx, DATABASE_NAME, null, VERSION_NUM);

        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL("CREATE TABLE " + name + " ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +ASSIGNMENT+" text, "+ GRADE +" INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS "+ name); //delete what was there previously
            db.execSQL("CREATE TABLE " + name + " ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " + ASSIGNMENT+ " text, "+ GRADE +" INTEGER, "+COMMENT+" text);");
        }

        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS "+ name); //delete what was there previously
                db.execSQL("CREATE TABLE " + name + " ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " + ASSIGNMENT+ " text, "+ GRADE + " INTEGER, "+COMMENT+" text);");
        }

        @Override
        public void onOpen(SQLiteDatabase db)
        {
            Log.i("Database ", "onOpen was called");
        }
    }
}
