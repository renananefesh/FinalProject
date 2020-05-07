package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.FileInputStream;

public class EventPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
        try {
            FileInputStream inputStream = new FileInputStream("/data/data/com.example.project/files/wedding_partic_names.txt");
        } catch (Exception e) {

        }
    }
}
