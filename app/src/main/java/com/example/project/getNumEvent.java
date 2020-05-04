package com.example.project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class getNumEvent extends AppCompatActivity {
    EditText eventnumber;
    private Button next_button;
    String user;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participants);
        Intent in=getIntent();
        user=in.getStringExtra("username");
        Random rand = new Random();


        // Generate random integers in range 0 to 999999
        int rand_int1 = rand.nextInt(1000000);
        eventnumber = findViewById(R.id.editText3);
        String num = String.valueOf(rand_int1);
        eventnumber.setText(num);
        String numtext =  eventnumber.getText().toString();

        try(FileWriter fw = new FileWriter("/data/data/com.example.project/files/weddingevent.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw)) {
            out.println(numtext);
        } catch (IOException e) {
            e.printStackTrace();
        }
        next_button = findViewById(R.id.nextbutton);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUserEvent();
            }

        });
    }
    public void goToUserEvent() {
        Intent intent = new Intent(this, UserEvents.class);
        intent.putExtra("username", user);
        startActivity(intent);
    }
}