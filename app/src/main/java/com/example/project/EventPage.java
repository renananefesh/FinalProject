package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class EventPage extends AppCompatActivity {
    String user,eventname;

int x=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
        Intent in = getIntent();
        user = in.getStringExtra("username");
        eventname = in.getStringExtra("eventname");
        String path = "/data/data/com.example.project/files/", text;
        FileInputStream inputStream = null;
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.layout);

        try {
             inputStream = new FileInputStream(path + eventname+".txt");
        } catch (Exception e) {

        }
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            text = bufferedReader.readLine();// its manager name we don't need
            while ((text = bufferedReader.readLine()) != null) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(100, 950 + x, 100, 640);
                x = x + 150;
                TextView add_text = new TextView(this);

                add_text.setText(text);

                rl.addView(add_text, layoutParams);
                final Context context = this;
                ;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
