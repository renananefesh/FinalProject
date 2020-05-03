package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class UserEvents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String text,nameevent;
        Button button_manage, button_participant;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_events);
        button_manage = (Button) findViewById(R.id.manage_a_new_activity);
        button_participant = (Button) findViewById(R.id.sing_into_activity);
        Intent in=getIntent();
        String user=in.getStringExtra("username");



        FileInputStream fileInputStream = null;
        InputStream inputStream       = null;
        try {
            inputStream = new FileInputStream("/data/data/com.example.project/files/weddingevent.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Reader inputStreamReader = new InputStreamReader(inputStream);

        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
            try {
                text = bufferedReader.readLine();
                if(text.equals(user)) {
                    nameevent = bufferedReader.readLine();

                    Toast.makeText(UserEvents.this, "you have mre event ", Toast.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }





        button_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                goToActivityMangerPage();

            }
        });

        button_participant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToActivityParticipantPage();

            }
        });
    }
    private void goToActivityMangerPage(){
        Intent intent = new Intent(this, activity_manager.class);
        startActivity(intent);
    }

    private void goToActivityParticipantPage(){
        Intent intent = new Intent(this, ActivityParticipant.class);
        startActivity(intent);
    }
}

