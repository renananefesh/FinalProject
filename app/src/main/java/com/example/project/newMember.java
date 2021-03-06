package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class newMember extends AppCompatActivity {


    private Button manager_button;
    private Button participant_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newmember);
        participant_button = (Button) findViewById(R.id.participant_button);
        participant_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openParticipantPage();
            }
        });
        manager_button = (Button) findViewById(R.id.manager_button);
        manager_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openManagerPage();

            }

        });
    }
    public void openManagerPage() {
        Intent intent = new Intent(this, activity_manager.class);
        startActivity(intent);
    }

    public void openParticipantPage() {
        Intent intent = new Intent(this, ActivityParticipant.class);
        startActivity(intent);
    }

}