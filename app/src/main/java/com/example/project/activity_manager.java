package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_manager extends AppCompatActivity {
    EditText managerName, date, time, address, nameOfevent;
   Button button;

    DatabaseReference reff;
    EventTable eventTable;
//
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

        reff = FirebaseDatabase.getInstance().getReference().child("EventTable");

        button = findViewById(R.id.btnsubmit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                eventTable.setManagerName(managerName.getText().toString().trim());
                eventTable.setDate(date.getText().toString().trim());
                eventTable.setTime(time.getText().toString().trim());
                eventTable.setAddress(address.getText().toString().trim());
                eventTable.setNameOfevent(nameOfevent.getText().toString().trim());
                reff.push().setValue(eventTable);

                getNumEvent();
           }
//      });



    });
   }
//here the manager will send a link to the people he wants to join or any other way
    public void getNumEvent() {
        Intent intent = new Intent(this, getNumEvent.class);
        startActivity(intent);
    }
}