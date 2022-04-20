package com.example.ikujatwende2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    private Button myedit, mySave,myNew, myView;
    private TextView text6,text7,text8,text9,text10;
    private ArrayList<String> activity,location,date,time,reporter;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        myedit = findViewById(R.id.myedit);
        mySave = findViewById(R.id.mySave);
        myNew= findViewById(R.id.myNew);
        myView = findViewById(R.id.myView);

        text6  = findViewById(R.id.text6);
        text7  = findViewById(R.id.text7);
        text8  = findViewById(R.id.text8);
        text9  = findViewById(R.id.text9);
        text10  = findViewById(R.id.text10);



        Intent intent2 = getIntent();
        Bundle bd = intent2.getExtras();
        List myL  = (List<myIkiujaTwende>) bd.getSerializable("MyValues");

        ArrayList<myIkiujaTwende> myList= new ArrayList(myL);

        text6.setText(myList.get(0).getActivty());
        text7.setText(myList.get(0).getLocation());
        text8 .setText(myList.get(0).getDate());
        text9.setText(myList.get(0).getTime());
        text10.setText(myList.get(0).getReporter());

        DB = new DatabaseHelper(this);

        myedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        myNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });
        mySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean checkinsertdata = DB.insertuserdata(text6.getText().toString(),text7.getText().toString()
                        ,text8.getText().toString(),text9.getText().toString(),text10.getText().toString());
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity3.this, "Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity3.this, "Entry Not Inserted", Toast.LENGTH_SHORT).show();


            }
        });
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity = new ArrayList<>();
                location = new ArrayList<>();
                date = new ArrayList<>();
                time = new ArrayList<>();
                reporter = new ArrayList<>();

                Cursor res = DB.getdata();
                if(res.getCount()==0){ // check if there are records to display
                    Toast.makeText(MainActivity3.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;

                }

                while(res.moveToNext()){

                    activity.add(res.getString(0));
                    location.add(res.getString(1));
                    date.add(res.getString(2));
                    time.add(res.getString(3));
                    reporter.add(res.getString(4));

                }

                Intent intent = new Intent(getApplicationContext(), MainActivity4.class);
                Bundle bd = new Bundle();


                bd.putStringArrayList("myActivity",activity);
                bd.putStringArrayList("myLocation",location);
                bd.putStringArrayList("myDate",date);
                bd.putStringArrayList("myTime",time);
                bd.putStringArrayList("myReporter",reporter);

                intent.putExtras(bd);
                startActivity(intent);


            }
        });


    }
}