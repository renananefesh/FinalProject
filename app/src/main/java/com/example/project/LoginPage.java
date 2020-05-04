package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class LoginPage extends AppCompatActivity {
    private Button login_button, signin_button;
    EditText username, password;
    String filename = "test.txt";
    String text, thisUserName,thisPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        login_button = (Button) findViewById(R.id.loginbutton);
        username =(EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (filename.toString() != null && filename.trim() != "") {
                    FileInputStream fileInputStream = null;
                    InputStream inputStream       = null;
                    try {
                        inputStream = new FileInputStream("/data/data/com.example.project/files/test.txt");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    Reader inputStreamReader = new InputStreamReader(inputStream);

                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    StringBuilder stringBuilder = new StringBuilder();
                    thisUserName =username.getText().toString();
                    thisPassword=password.getText().toString();
                    try {
                        while ((text = bufferedReader.readLine()) != null) {
                            //stringBuilder.append(text);
                            if(text.equals(thisUserName)) {
                                text = bufferedReader.readLine();
                                if(text.equals((thisPassword)))
                                   openUserPage();
                                   //return;
                                else
                                    Toast.makeText(LoginPage.this, "password is incorrect ", Toast.LENGTH_LONG).show();
                                    return;
                            }

                        }
                        Toast.makeText(LoginPage.this, "no such user, please sign up", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(LoginPage.this, "no such user, please sign up ", Toast.LENGTH_LONG).show();
                    }


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
    };

    public void openUserPage() {
        Intent intent = new Intent(this, UserEvents.class);
        intent.putExtra("username", thisUserName);
        startActivity(intent);
    }
    public void openNewUserPage() {
        Intent intent = new Intent(this, NewUser.class);
        startActivity(intent);
    }
}