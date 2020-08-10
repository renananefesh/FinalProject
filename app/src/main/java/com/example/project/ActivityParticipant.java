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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


public class ActivityParticipant extends AppCompatActivity {
    EditText eventnumber;
    Button button;
    InputStream inputStream = null;
    String text,text1, number, eventname, user;
    String eventsid_path = "/data/data/com.example.project/files/eventsid.txt";
    FileInputStream inputStream2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent in = getIntent();
        user = in.getStringExtra("username");
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
                    checkIfUserSignedIn();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void fillInParticipantDetails() {
        Intent intent = new Intent(this, activity_participantDetails.class);
        intent.putExtra("number", number);
        intent.putExtra("username", user);
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
        inputStream = new FileInputStream(eventsid_path);
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

    private void checkIfUserSignedIn() {
        String path = "/data/data/com.example.project/files/";
        FileInputStream inputStream = null;
        //make sure one cannot sign into the same event twice
        try {
            inputStream2 = new FileInputStream(eventsid_path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Reader inputStreamReader2 = new InputStreamReader(inputStream2);
        BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);


        try {
            //open partic_names and add name
            while ((text = bufferedReader2.readLine()) != null)
                if (text.equals(number)) {
                    text = bufferedReader2.readLine();
                    eventname = text;
                    try {
                        inputStream = new FileInputStream(path + eventname + "_partic_names.txt");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Reader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    while ((text = bufferedReader.readLine()) != null)
                        if (text.equals(user)) {
                            //raise notice he is the event already
                            //go back to EventPage
                            Toast.makeText(ActivityParticipant.this, "you are  already signed in to the event ", Toast.LENGTH_LONG).show();
                            goToUserEventPage();
                        }
                }
        } catch (IOException e) {

        }
    }
    private void goToUserEventPage() {
        Intent intent = new Intent(this, UserEvents.class);
        intent.putExtra("username", user);
        startActivity(intent);
    }
}