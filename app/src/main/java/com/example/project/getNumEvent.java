package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.os.Bundle;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class getNumEvent extends AppCompatActivity {
EditText eventnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participants);
        Random rand = new Random();
       // DatabaseReference reff;



        // Generate random integers in range 0 to 999999
        int rand_int1 = rand.nextInt(1000000);
        eventnumber= findViewById(R.id.editText3);
        String num = String.valueOf(rand_int1);
        eventnumber.setText(num);
       // reff = FirebaseDatabase.getInstance().getReference().child("EventTable");
       // eventTable.setEventNumber(eventnumber.getText().toString().trim());
       // reff.push().setValue(eventTable);




    }
}
