package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class EventPage extends AppCompatActivity {
    String user, eventname, address;

    int x = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
        Intent in = getIntent();
        user = in.getStringExtra("username");
        eventname = in.getStringExtra("eventname");
        InputStream inputStream = null;
        String path = "/data/data/com.example.project/files/", text;
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.layout);
        BufferedReader bufferedReader = null;
        Reader inputStreamReader;

        try {
            inputStream = new FileInputStream(path + eventname
                    + "_partic_details.txt");
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
        } catch (Exception e) {

        }
        try {

            while ((text = bufferedReader.readLine()) != null) {
                if (text.equals(user)) {
                    address = bufferedReader.readLine();
                    break;
                }
            }
        } catch (Exception e) {
        }

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(100, 950 + x, 100, 640);
        x = x + 150;
        TextView add_text = new TextView(this);

        add_text.setText(address);

        rl.addView(add_text, layoutParams);
        final Context context = this;
        goToMapActivity();
    }
        public void goToMapActivity() {
            Intent intent = new Intent(this, AppMap.class);
            intent.putExtra("address", address);
            intent.putExtra("eventname", eventname);
            startActivity(intent);
        }

    }

