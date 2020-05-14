package com.example.project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;


public class activity_participantDetails extends AppCompatActivity {
    EditText name, departLocation, leavingTime, address, emptySpots, price;
    TextView textView;
    Button btnsubmit;
    RadioGroup radioGroup;
    RadioButton radioButton;
    validation valid;
    boolean isPressed = false;
    String username, departlocation, leavingtime, emptyspots, path, numid, pricetravel;

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
        radioButton = findViewById(R.id.driver);
        price = findViewById(R.id.price);
        path = "/data/data/com.example.project/files/";
        Intent in = getIntent();
        numid = in.getStringExtra("number");
        valid = new validation();
        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                username = name.getText().toString();
                departlocation = departLocation.getText().toString();
                leavingtime = leavingTime.getText().toString();
                emptyspots = emptySpots.getText().toString();
                pricetravel = price.getText().toString();

                if (validAll()) {
                    isPressed = false;
                    String eventsid_path = "/data/data/com.example.project/files/eventsid.txt";
                    FileInputStream inputStream2 = null;
                    try {
                        inputStream2 = new FileInputStream(eventsid_path);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Reader inputStreamReader2 = new InputStreamReader(inputStream2);
                    BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);
                    StringBuilder stringBuilder2 = new StringBuilder();

                    try {
                        String text;
                        while ((text = bufferedReader2.readLine()) != null)
                            if (text.equals(numid)) {
                                text = bufferedReader2.readLine();
                                try (FileWriter fw = new FileWriter(path + text + "_partic_names.txt", true);
                                     BufferedWriter bw = new BufferedWriter(fw);
                                     PrintWriter out = new PrintWriter(bw)) {
                                    out.println(username);

                                }
                            }

                        //open file of detales participants and add details
                        try (FileWriter fw = new FileWriter(path +"_particepant_details.txt", true);
                             BufferedWriter bw = new BufferedWriter(fw);
                             PrintWriter out = new PrintWriter(bw)) {
                            out.println(username);
                            out.println(departlocation);
                            checkWhatInfoWasFilled(out, v);
                        } catch (Exception e) {
                        }


                        Toast.makeText(activity_participantDetails.this, "data inserted successfully", Toast.LENGTH_LONG).show();
                        goToUserEventPage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
    //this function Check if driver radio button was clicked and write his details in file
    private void checkWhatInfoWasFilled(PrintWriter out, View v) {
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()) {
            case R.id.driver:
                if (checked) {
                    out.println(emptySpots);
                    out.println(leavingtime);
                    out.println(price);
                    break;
                }
        }
    }

    private void goToUserEventPage() {
        Intent intent = new Intent(this, UserEvents.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    //this function check if one of the radio button has clicked
    public void checkButton(View v) {
        boolean checked = ((RadioButton) v).isChecked();

        // Check which radio button was clicked
        switch (v.getId()) {
            case R.id.driver:
                if (checked) {
                    emptySpots.setVisibility(View.VISIBLE);
                    leavingTime.setVisibility(View.VISIBLE);
                    price.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.passenger:
                if (checked) {
                    emptySpots.setVisibility(View.INVISIBLE);
                    leavingTime.setVisibility(View.INVISIBLE);
                    price.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }

    //main function that responsible of all validation
    public boolean validAll() {
        if (isEmpty())
            if (!isPressed)
                return (validTime());
            else return true;
        return false;
    }

    //function that check if the user did not insert values to the fields
    public boolean isEmpty() {


        if (username.matches("") || departlocation.matches("")) {
            Toast.makeText(this, "one or more of the fields are empty", Toast.LENGTH_SHORT).show();
            return false;
        }

        //check if driver radio button has clicked and than check his fields
        RadioButton db, pd;
        db = (RadioButton) findViewById(R.id.driver);
        pd = (RadioButton) findViewById(R.id.passenger);
        if (pd.isChecked()) {
            isPressed = true;
            return true;
        }
        if (db.isChecked()) {
            if (leavingtime.matches("") || emptyspots.matches("") || pricetravel.matches("")) {
                Toast.makeText(this, "one or more of the fields are empty", Toast.LENGTH_SHORT).show();
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