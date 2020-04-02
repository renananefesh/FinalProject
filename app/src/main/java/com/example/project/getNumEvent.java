package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;
import android.os.Bundle;
import android.widget.EditText;

public class getNumEvent extends AppCompatActivity {
EditText eventnumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participants);
        Random rand = new Random();

        // Generate random integers in range 0 to 999999
        int rand_int1 = rand.nextInt(1000000);
        eventnumber= findViewById(R.id.editText3);
        String num = String.valueOf(rand_int1);
        eventnumber.setText(num);

        // Print random integers
        System.out.println("Random Integers: "+rand_int1);

    }
}
