package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class LoginPage extends AppCompatActivity {
    private Button login_button, signin_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        BufferedReader myr;
        String s="C:\\Users\\RenanaNefesh\\Downloads\\FinalProject-master\\app\\src\\main\\java\\com\\example\\project";
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(s+"filename.txt"));
            writer = new BufferedWriter(new FileWriter(s));

            writer.write(4);

            String line = null;
            // read all the lines till the end
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            writer.close();
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }




        login_button = (Button) findViewById(R.id.loginbutton);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserPage();
            }
        });

        signin_button = (Button) findViewById(R.id.signinbutton);
        signin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNewUserPage();
            }
        });




    }
    public void openUserPage() {
        Intent intent = new Intent(this, UserEvents.class);
        startActivity(intent);
    }
    public void openNewUserPage() {
        Intent intent = new Intent(this, NewUser.class);
        startActivity(intent);
    }
}
