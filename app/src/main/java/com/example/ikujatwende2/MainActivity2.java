package com.example.ikujatwende2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    private EditText activty,location,date,time,reporter;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        activty = findViewById(R.id.edit);
        location = findViewById(R.id.edit2);
        date = findViewById(R.id.edit3);
        time = findViewById(R.id.edit4);
        reporter = findViewById(R.id.edit5);

        button = findViewById(R.id.myedit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(activty.getText())) {
                    activty.requestFocus();
                    activty.setError(" Activity field is required ");
                }
                if (TextUtils.isEmpty(date.getText())) {
                    date.requestFocus();
                    date.setError(" Date field is required ");
                }
                if (TextUtils.isEmpty(reporter.getText())) {
                    reporter.requestFocus();
                    reporter.setError(" Reporter's name field is required ");
                }

                myIkiujaTwende myObj = new myIkiujaTwende(activty.getText().toString(),location.getText().toString(),
                        date.getText().toString(),time.getText().toString(),reporter.getText().toString());
                ArrayList myList = new ArrayList();
                myList.add(myObj);

                if(activty.getText().toString().trim().length() > 0
                        &&  date.getText().toString().trim().length() > 0
                        &&  reporter.getText().toString().trim().length() > 0) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity3.class);
                    Bundle bd = new Bundle();

                    bd.putSerializable("MyValues", (Serializable) myList);
                    intent.putExtras(bd);
                    startActivity(intent);
                }

            }
        });

 }


}