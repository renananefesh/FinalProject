package com.example.project;

import android.content.Intent;
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

public class


activity_participantDetails extends AppCompatActivity {
    EditText name, departLocation, leavingTime, address, emptySpots;
    TextView textView;
    Button btnsubmit;
    RadioGroup radioGroup;
    RadioButton radioButton;
    DatabaseReference reff;
    ParticipantsTable participantsTable;
    validation valid;

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
        textView = findViewById(R.id.type_of_traveler);
        btnsubmit = findViewById(R.id.submit);
        emptySpots = (EditText) findViewById(R.id.number_of_empty_spots);
        participantsTable = new ParticipantsTable();
        radioButton = findViewById(R.id.driver);
        valid = new validation();

        reff = FirebaseDatabase.getInstance().getReference().child("ParticipantTable");

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validAll()) {

                    participantsTable.setName(name.getText().toString().trim());
                    participantsTable.setDepartLocation(departLocation.getText().toString().trim());
                    participantsTable.setLeavingTime(leavingTime.getText().toString().trim());
                    //  participantsTable.setAddress(address.getText().toString().trim());
                    //participantsTable.setDriver(driver);
                    //participantsTable.getPassenger(passenger.

                    participantsTable.setEmptySpots(emptySpots.getText().toString().trim());
                    reff.push().setValue(participantsTable);
                    Toast.makeText(activity_participantDetails.this, "data inserted successfully", Toast.LENGTH_LONG).show();
                    goToUserEventPage();
                }
            }
        });
    }

    private void goToUserEventPage() {
        Intent intent = new Intent(this, UserEvents.class);
        startActivity(intent);
    }

    //function that check if one of the radio button has clicked
    public void checkButton(View v) {
        boolean checked = ((RadioButton) v).isChecked();

        // Check which radio button was clicked
        switch (v.getId()) {
            case R.id.driver:
                if (checked)
                    emptySpots.setVisibility(View.VISIBLE);
                leavingTime.setVisibility(View.VISIBLE);
                break;
            case R.id.passenger:
                if (checked)
                    emptySpots.setVisibility(View.INVISIBLE);
                leavingTime.setVisibility(View.INVISIBLE);
                ;//update database
                break;
        }
    }

    //main function that responsible of all validation
    public boolean validAll() {
        if (isEmpty())
            return (validTime());
        return false;
    }

    //function that check if the user did not insert values to the fields
    public boolean isEmpty() {
        String Name = name.getText().toString();
        String DepartLocation = departLocation.getText().toString();
        String LeavingTime = leavingTime.getText().toString();
        String EmptySpots = emptySpots.getText().toString();


        if (Name.matches("") || DepartLocation.matches("")) {
            Toast.makeText(this, "one or more of the fields are empty", Toast.LENGTH_SHORT).show();
            return false; }

        //check if driver radio button has clicked and than check his fields
        RadioButton db;
        db = (RadioButton) findViewById(R.id.driver);
        if (db.isChecked()) {
            if (LeavingTime.matches("") || EmptySpots.matches("")) {
                Toast.makeText(this, "one or more of the fields are emptyyyyy", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }


    //function that check if time that user input is valid
    public boolean validTime() {
        if (valid.validTime(leavingTime.getText().toString())) {
            Toast.makeText(this, "Time you entered is invalid", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}