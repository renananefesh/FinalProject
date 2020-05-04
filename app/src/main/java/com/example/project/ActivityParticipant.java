package com.example.project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;



public class ActivityParticipant extends AppCompatActivity {
    ParticipantsTable participantsTable;
    EditText eventNumber;
    Button button;
    String sCurrentLine, lastLine, number, user;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant);
        eventNumber = (EditText) findViewById(R.id.eventNumber);
        number = eventNumber.getText().toString();

        button = (Button) findViewById(R.id.btnsubmit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validAll()) {

                    fillInParticipantDetails();
                }
            }
        });
    }

    public void fillInParticipantDetails() {
        Intent intent = new Intent(this, activity_participantDetails.class);
        startActivity(intent);
    }

    public boolean validAll() {
        return (isEmpty());
    }

    public boolean isEmpty() {
        String Number = eventNumber.getText().toString();
        if (Number.matches("")) {
            Toast.makeText(this, "you need to enter event number first", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}