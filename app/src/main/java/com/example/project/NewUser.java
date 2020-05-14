package com.example.project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class NewUser extends AppCompatActivity {
    Button button;
    TextView username;
    EditText password;
    String filename = "test.txt", name, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        button = (Button) findViewById(R.id.sign_up);
        username = (TextView) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                FileOutputStream fileOutputStream;
                name = username.getText().toString();
                pass = password.getText().toString();
                try (FileWriter fw = new FileWriter("/data/data/com.example.project/files/test.txt", true);
                     BufferedWriter bw = new BufferedWriter(fw);
                     PrintWriter out = new PrintWriter(bw)) {
                    out.println(name);
                    out.println(pass);
                } catch (IOException e) {
                }

                Toast.makeText(NewUser.this, "Welcome to Way2Go ", Toast.LENGTH_LONG).show();
                goToUserEventPage();
            }

        });
    }

    private void goToUserEventPage() {
        Intent intent = new Intent(this, UserEvents.class);
        intent.putExtra("username", name);
        startActivity(intent);
    }


}