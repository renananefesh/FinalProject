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
    EditText eventnumber;
    Button button;
    String sCurrentLine, lastLine, number, user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant);


        button = (Button) findViewById(R.id.btnsubmit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventnumber = (EditText) findViewById(R.id.eventNumber);
                number = eventnumber.getText().toString();
                if (validAll()) {

                    fillInParticipantDetails();
                }
            }
        });
    }

    public void fillInParticipantDetails() {
        Intent intent = new Intent(this, activity_participantDetails.class);
       intent.putExtra("number",number);
        startActivity(intent);
    }

    public boolean validAll() {
        return (isEmpty());
    }

    public boolean isEmpty() {
        String Number = eventnumber.getText().toString();
        if (Number.matches("")) {
            Toast.makeText(this, "you need to enter event number first", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}