package com.example.project;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_participantDetails extends AppCompatActivity {
    EditText name, departLocation, leavingTime, address, emptySpots;
    TextView textView;
    Button btnsubmit;
    RadioGroup radioGroup;
    RadioButton radioButton;
    DatabaseReference reff;
    ParticipantsTable participantsTable;

    //    Databasehelper eventDB;
//    private Button maneger_button;
//    private Button participant_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parcitipant_details);
        name = (EditText) findViewById(R.id.name);
        departLocation = (EditText) findViewById(R.id.depart_location);
        leavingTime = (EditText) findViewById(R.id.leaving_time);
        address = (EditText) findViewById(R.id.address);
        radioGroup = findViewById(R.id.radioGroup);
        textView= findViewById(R.id.type_of_traveler);
        btnsubmit= findViewById(R.id.submit);
        emptySpots = (EditText) findViewById(R.id.number_of_empty_spots);
        participantsTable = new ParticipantsTable();

        reff = FirebaseDatabase.getInstance().getReference().child("ParticipantTable");

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                participantsTable.setName(name.getText().toString().trim());
                participantsTable.setDepartLocation(departLocation.getText().toString().trim());
                participantsTable.setLeavingTime(leavingTime.getText().toString().trim());
                participantsTable.setAddress(address.getText().toString().trim());
                //participantsTable.setDriver(driver);
                //participantsTable.getPassenger(passenger.

                participantsTable.setEmptySpots(emptySpots.getText().toString().trim());
                reff.push().setValue(participantsTable);
                Toast.makeText(activity_participantDetails.this, "data inserted successfully", Toast.LENGTH_LONG).show();
            }
       });
   }

//not working
public void checkButton(View v){
    int radioID = radioGroup.getCheckedRadioButtonId();
    radioButton = findViewById(radioID);
    if (radioButton.getText()=="driver"){
        emptySpots.setVisibility(View.VISIBLE);
        leavingTime.setVisibility(View.VISIBLE);
    }
}

}
