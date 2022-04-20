package com.example.ikujatwende2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {

    private RecyclerView recyclerView;
    private  RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList activity,location,date,time,reporter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Intent intent2 = getIntent();
        Bundle bd = intent2.getExtras();

        activity = new ArrayList<>(bd.getStringArrayList("myActivity"));
        location = new ArrayList<>(bd.getStringArrayList("myLocation"));
        date = new ArrayList<>(bd.getStringArrayList("myDate"));
        time = new ArrayList<>(bd.getStringArrayList("myTime"));
        reporter = new ArrayList<>(bd.getStringArrayList("myReporter"));



        recyclerViewAdapter = new RecyclerViewAdapter(getApplicationContext(),activity,location,date,time,reporter);
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerViewAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}