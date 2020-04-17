package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UserEvents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Button button_manage, button_participant;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_events);
        button_manage = (Button) findViewById(R.id.manage_a_new_activity);
        button_participant = (Button) findViewById(R.id.sing_into_activity);
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

