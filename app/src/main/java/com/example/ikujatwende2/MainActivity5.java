package com.example.ikujatwende2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity5 extends AppCompatActivity {
    private TextView myActivty, myLocation, myDate, myTime, myReporter, myReport;
    private Button addReport;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        myActivty = findViewById(R.id.add_rep_act);
        myLocation = findViewById(R.id.add_rep_location);
        myDate = findViewById(R.id.add_rep_date);
        myTime = findViewById(R.id.add_rep_time);
        myReporter = findViewById(R.id.add_rep_reporter);

        myReport = findViewById(R.id.editTextReport);

//        addReport = findViewById(R.id.btnAddRep);
        DB = new DatabaseHelper(this);


        Intent intent2 = getIntent();
        Bundle bd = intent2.getExtras();

        String activity = bd.getString("myActivity");
        String location = bd.getString("myLocation");
        String date = bd.getString("myDate");
        String time = bd.getString("myTime");
        String reporter = bd.getString("myReporter");

        myActivty .setText(activity);
        myLocation.setText(location );
        myDate .setText(date);
        myTime.setText(time);
        myReporter.setText(reporter);

        Cursor res = DB.getReport(activity,date,reporter);

        if(res.getCount()==0){
            myReport.setText("no report found yet");
        }
        else{
            while(res.moveToNext()) {
                myReport.setText(res.getString(0));
            }
        }


    }
}