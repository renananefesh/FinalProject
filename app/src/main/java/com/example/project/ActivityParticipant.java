package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class ActivityParticipant extends AppCompatActivity {
    EditText eventnumber;
    Button button;
    String number;
    InputStream inputStream = null;
    String path = "/data/data/com.example.project/files/eventsid.txt", text1;

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
                try {
                    if (validAll()) {
                        fillInParticipantDetails();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void fillInParticipantDetails() {
        Intent intent = new Intent(this, activity_participantDetails.class);
        intent.putExtra("number", number);
        startActivity(intent);
    }

    //main function that responsible of all validation
    public boolean validAll() throws IOException {
        if (isEmpty())
            return (checkIfExist());
        return false;

    }

    //this function check if fields are empty
    public boolean isEmpty() {
        String Number = eventnumber.getText().toString();
        if (Number.matches("")) {
            Toast.makeText(this, "you need to enter event number first", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //this function check if the ID number of event that user entered is exists in event id's file
    public boolean checkIfExist() throws IOException {
        inputStream = new FileInputStream(path);
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //for each event try to see if the user is managing it
            while ((text1 = bufferedReader.readLine()) != null)
                if (text1.equals(number)) {
                    return true;
                }
            Toast.makeText(this, "No such ID number exists", Toast.LENGTH_SHORT).show();
            return false;

        } finally {
        }
    }
}