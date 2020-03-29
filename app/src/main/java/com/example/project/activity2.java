package com.example.project;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;

<<<<<<< HEAD
import com.google.firebase.database.FirebaseDatabase;

public class activity2 extends AppCompatActivity {
    private Button button;


=======
public class activity2 extends AppCompatActivity {
    private Button button;

>>>>>>> 380bffe1de147263b3c090abcbeea016c1d2550d
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addParticipants();

            }
        });
    }

    public void addParticipants() {
        Intent intent = new Intent(this, addParticipants.class);
        startActivity(intent);
        }

}
