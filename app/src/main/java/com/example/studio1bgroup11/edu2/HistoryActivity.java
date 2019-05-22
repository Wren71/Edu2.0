package com.example.studio1bgroup11.edu2;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class HistoryActivity extends AppCompatActivity {


    private ListView history_list;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        ActionBar actionbar = getSupportActionBar();


        history_list = (ListView) findViewById(R.id.history);

        ArrayList<String> arrayHistory = new ArrayList<>();
        arrayHistory.addAll(Arrays.asList(getResources().getStringArray(R.array.history)));

        adapter = new ArrayAdapter<String>(
                HistoryActivity.this,
                android.R.layout.simple_list_item_1,
                arrayHistory
        );


        history_list.setAdapter(adapter);
    }
}
