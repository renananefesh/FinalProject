package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class UserEvents extends AppCompatActivity {
    int x = 0, y = 0;
    String text1;
    String user, nameofevent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String text;

        final String path;
        final String path2;
        Button button_manage, button_participant;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_events);
        button_manage = (Button) findViewById(R.id.manage_a_new_activity);
        button_participant = (Button) findViewById(R.id.sing_into_activity);
        Intent in = getIntent();
        user = in.getStringExtra("username");
        InputStream inputStream = null, inputStream1 = null, inputStream2 = null;
        path = "/data/data/com.example.project/files/";
        path2 = "/data/data/com.example.project/files/eventnames.txt";


        try {

            try {
                //open eventnames.txt file
                inputStream = new FileInputStream(path2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Reader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            try {
                //for each event try to see if the user is managing it
                while ((text1 = bufferedReader.readLine()) != null)
                    try {
                        inputStream1 = new FileInputStream(path + text1 + ".txt");
                        Reader inputStreamReader1 = new InputStreamReader(inputStream1);
                        BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader1);
                        StringBuilder stringBuilder1 = new StringBuilder();
                        try {

                            while ((text = bufferedReader1.readLine()) != null)
                                if (text.equals(user)) {

                                    createManegeEventButton();
                                }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //try to see if user participating in text1 = eventname
                        try {
                            inputStream2 = new FileInputStream(path + text1 + "_partic_names" + ".txt");
                            Reader inputStreamReader2 = new InputStreamReader(inputStream2);
                            BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);
                            StringBuilder stringBuilder2 = new StringBuilder();
                            try {

                                while ((text = bufferedReader2.readLine()) != null)
                                    if (text.equals(user)) {
                                        createParticipantEventButton();
                                    }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (FileNotFoundException e) {
                        }

                    } catch (Exception e) {
                    }
            } catch (Exception e) {
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
        } finally {

        }
    }

    private void goToActivityMangerPage() {
        Intent intent = new Intent(this, activity_manager.class);
        startActivity(intent);
    }

    private void goToActivityParticipantPage() {
        Intent intent = new Intent(this, ActivityParticipant.class);
        intent.putExtra("username", user);
        startActivity(intent);
    }
    //this function create ner button for the events that user maneging
    public void createManegeEventButton() {
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.layout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(100, 350+y, 100, 640);
        y = y + 150;
        final Button add_btn = new Button(this);
        add_btn.setText(text1);
        rl.addView(add_btn, layoutParams);
        final Context context = this;
        add_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //  goToActivity.needs to move to function once we build the page
                nameofevent= add_btn.getText().toString();
                Intent intent = new Intent(context, EventPage.class);
                intent.putExtra("username", user);
                intent.putExtra("eventname", nameofevent);
                startActivity(intent);
            }
        });
        this.setContentView(rl);
    }
    //this function create ner button for the events that user participating in
    public void createParticipantEventButton(){
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.layout);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(100, 950 + x, 100, 640);
        x = x + 150;
        final Button add_btn = new Button(this);
        add_btn.setText(text1);

        rl.addView(add_btn, layoutParams);
        final Context context = this;

        add_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //  goToActivity.needs to move to function once we build the page
                nameofevent= add_btn.getText().toString();
                Intent intent = new Intent(context, EventPage.class);
                intent.putExtra("username", user);
                intent.putExtra("eventname", nameofevent);
                startActivity(intent);
            }
        });
        this.setContentView(rl);

    }

}
