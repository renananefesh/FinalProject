package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class LoginPage extends AppCompatActivity {
    private Button login_button, signin_button;
EditText username, password;
String filename = "test.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        login_button = (Button) findViewById(R.id.loginbutton);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUserPage();
                username =(EditText) findViewById(R.id.username);
                password = (EditText) findViewById(R.id.password);
              FileOutputStream fileOutputStream;
            String s =  username.getText().toString();
                try {
                    fileOutputStream = openFileOutput(filename, Context.MODE_PRIVATE);
                    fileOutputStream.write(s.getBytes());
                    fileOutputStream.write(5);
                } catch (IOException e) {
                    e.printStackTrace();
                }
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