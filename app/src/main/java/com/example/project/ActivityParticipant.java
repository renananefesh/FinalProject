package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityParticipant extends AppCompatActivity {
    ParticipantsTable participantsTable;
    EditText eventNumber;
    Button button;
    DatabaseReference reff;
    EventTable eventTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant);
        eventNumber = (EditText) findViewById(R.id.eventNumber);
//        ValueEventListener postListener = new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // Get Post object and use the values to update the UI
//                int post = dataSnapshot.getValue(Post.class);
//                // ...
//            }



            button = (Button) findViewById(R.id.btnsubmit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validAll()) {

                    fillInParticipantDetails();
                }
            }
        });

        reff = FirebaseDatabase.getInstance().getReference().child("EventTable");
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

