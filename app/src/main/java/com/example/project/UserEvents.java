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


//we need to add here to read the particapint file and yes to add a button
public class UserEvents extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String text, nameevent, user;
        Button button_manage, button_participant;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_events);
        button_manage = (Button) findViewById(R.id.manage_a_new_activity);
        button_participant = (Button) findViewById(R.id.sing_into_activity);
        Intent in = getIntent();
        user = in.getStringExtra("username");

        FileInputStream fileInputStream = null;
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("/data/data/com.example.project/files/weddingevent.txt");

            Reader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            try {
                text = bufferedReader.readLine();
                if (text.equals(user)) {
                    //reads the name of event
                    nameevent = bufferedReader.readLine();
                    //add a button to the layout
                    final RelativeLayout rl = (RelativeLayout) findViewById(R.id.layout);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(100, 1100, 100, 40);
                    Button add_btn = new Button(this);
                    add_btn.setText(nameevent);

                    rl.addView(add_btn, layoutParams);
                    final Context context = this;

                    add_btn.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            //  goToActivity.needs to move to function once we build the page
                            Intent intent = new Intent(context, getNumEvent.class);
                            startActivity(intent);
                        }
                    });
                    this.setContentView(rl);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
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

    private void goToActivityMangerPage() {
        Intent intent = new Intent(this, activity_manager.class);
        startActivity(intent);
    }

    private void goToActivityParticipantPage() {
        Intent intent = new Intent(this, ActivityParticipant.class);
        startActivity(intent);
    }

}

