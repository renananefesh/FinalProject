package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class activity_manager extends AppCompatActivity {
    EditText managerName, date, time, address, nameOfevent;
    Button button;
    validation valid;
    DatabaseReference reff;
    EventTable eventTable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        managerName = (EditText) findViewById(R.id.managerName);
        date = (EditText) findViewById(R.id.date);
        time = (EditText) findViewById(R.id.time);
        address = (EditText) findViewById(R.id.address);
        nameOfevent = (EditText) findViewById(R.id.nameOfevent);
        eventTable = new EventTable();
        valid = new validation();
        reff = FirebaseDatabase.getInstance().getReference().child("EventTable");

        button = findViewById(R.id.btnsubmit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validAll()) {
                    eventTable.setManagerName(managerName.getText().toString().trim());
                    eventTable.setDate(date.getText().toString().trim());
                    eventTable.setTime(time.getText().toString().trim());
                    eventTable.setAddress(address.getText().toString().trim());
                    eventTable.setNameOfevent(nameOfevent.getText().toString().trim());
                    reff.push().setValue(eventTable);
                    getNumEvent();
                }
            }

        });
    }

    //here the manager will send a link to the people he wants to join or any other way
    public void getNumEvent() {
        Intent intent = new Intent(this, getNumEvent.class);
        startActivity(intent);
    }

    //main function that responsible of all validation
    public boolean validAll() {

        if (isEmpty())
            return (validTimeAndDate());
        return false;

    }
    //function that check if the user did not insert values to the fields
    public boolean isEmpty() {
        String Time = time.getText().toString();
        String Date = date.getText().toString();
        String Managername = managerName.getText().toString();
        String Address = address.getText().toString();
        String Nameofevent = nameOfevent.getText().toString();
        //if there empty fields print to screen a popup
        if (Time.matches("") || Date.matches("") || Managername.matches("") || Address.matches("") || Nameofevent.matches("")) {
            Toast.makeText(this, "one or more of the fields are empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //function that check if date and time that user input is valid
    public boolean validTimeAndDate() {

        if (valid.validTime(time.getText().toString()) && valid.validDate(date.getText().toString())) {
            Toast.makeText(activity_manager.this, "The date and time you entered is invalid", Toast.LENGTH_LONG).show();
            return false;
        } else if (valid.validTime(time.getText().toString())) {
            Toast.makeText(activity_manager.this, "Time you entered is invalid", Toast.LENGTH_LONG).show();
            return false;
        } else if (valid.validDate(date.getText().toString())) {
            Toast.makeText(activity_manager.this, "Date you entered is invalid", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}