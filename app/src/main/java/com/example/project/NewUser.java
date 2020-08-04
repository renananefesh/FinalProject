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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewUser extends AppCompatActivity {
    Button button;
    EditText password, username, email;
    String filename = "test.txt", name, pass,em, path = "/data/data/com.example.project/files/test.txt";
    Boolean aprovedName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        button = (Button) findViewById(R.id.sign_up);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);


        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                name = username.getText().toString();
                try {
                   aprovedName = checkUserNameTaken(name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pass = password.getText().toString();
                em =email.getText().toString();
                if(emailValidator(em)) {
                    try (FileWriter fw = new FileWriter(path, true);
                         BufferedWriter bw = new BufferedWriter(fw);
                         PrintWriter out = new PrintWriter(bw)) {
                        if (aprovedName) {
                            out.println(name);
                            out.println(pass);
                            out.println(em);
                        } else {
                            Toast.makeText(NewUser.this, "Username is taken, please choose a different one ", Toast.LENGTH_LONG).show();
                            return;
                        }
                    } catch (IOException e) {
                    }


                    Toast.makeText(NewUser.this, "Welcome to Way2Go ", Toast.LENGTH_LONG).show();
                    goToUserEventPage();
                }else {
                    Toast.makeText(NewUser.this, "Email not valid", Toast.LENGTH_LONG).show();
                    return;
                }
            }

        });
    }

    private void goToUserEventPage() {
        Intent intent = new Intent(this, UserEvents.class);
        intent.putExtra("username", name);
        startActivity(intent);
    }
    //user name is unique. this function checks the username isnt taken
    private Boolean checkUserNameTaken(String name) throws IOException {
        InputStream inputStream = null;
        String usedNames;
        try {
            inputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        while ((usedNames = bufferedReader.readLine()) != null) {
            if (usedNames.equals(name)) {
                return false;
            }
        }
        return true;
    }
    public boolean emailValidator(String email)
    {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}