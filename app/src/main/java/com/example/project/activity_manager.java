package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class activity_manager extends AppCompatActivity {
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);

        button = (Button) findViewById(R.id.btnsubmit);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addParticipants();

            }
        });
    }
//here the manager will send a link to the people he wants to join or any other way
    public void addParticipants() {
        Intent intent = new Intent(this, addParticipants.class);
        startActivity(intent);
    }
}