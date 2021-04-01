package com.hfad.astroapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.util.Log;
import android.widget.EditText;
import androidx.appcompat.widget.Toolbar;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Button yesButton = findViewById(R.id.yes_button);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onYes();
            }
        });

//        mRecycleView = findViewById(R.id.recycleView);
//        mAdapter = new WordListAdapter(this, mWordList);
//        mRecycleView.setAdapter(mAdapter);
//        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    public void onYes () {
        Intent intent = new Intent(this, BirthChartDisplayActivity.class);
        startActivity(intent);

    }


}