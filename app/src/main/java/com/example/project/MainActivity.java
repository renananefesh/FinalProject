package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
<<<<<<< HEAD

=======
>>>>>>> 380bffe1de147263b3c090abcbeea016c1d2550d
    Databasehelper eventDB;
    private Button maneger_button;
    private Button participant_button;

<<<<<<< HEAD


=======
>>>>>>> 380bffe1de147263b3c090abcbeea016c1d2550d
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventDB = new Databasehelper(this);//call constructer of helper that will crete the DB
        participant_button = (Button) findViewById(R.id.participant_button);
        participant_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                openParticipantPage();
            }
        });

        maneger_button = (Button) findViewById(R.id.maneger_button);
        maneger_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openManegerPage();

            }

        });
    }
    public void openManegerPage() {
        Intent intent = new Intent(this, activity2.class);
        startActivity(intent);
    }

    public void openParticipantPage() {
        Intent intent = new Intent(this, ParcitipantActivity.class);
        startActivity(intent);
    }

}
