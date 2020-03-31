package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class activity_participant extends AppCompatActivity {
ParticipantsTable participantsTable;
EditText eventNumber;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant);
        eventNumber = (EditText)findViewById(R.id.eventNumber);
        button = (Button) findViewById(R.id.btnsubmit);
        button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 fillInParticipantDetails();
             }
         });
    }
public void  fillInParticipantDetails(){
    Intent intent = new Intent(this, activity_participantDetails.class);
    startActivity(intent);
}
}