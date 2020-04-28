package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewUser extends AppCompatActivity {
    UserTable userTable;
    Button button;
    DatabaseReference reff;
    TextView username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        button = (Button) findViewById(R.id.sign_up);

        username = (TextView) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        userTable = new UserTable();
        reff = FirebaseDatabase.getInstance().getReference().child("UserTable");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userTable.setUsername(username.getText().toString().trim());
                userTable.setPassword(password.getText().toString().trim());


                reff.child("user1").setValue(userTable);
                Toast.makeText(NewUser.this, "Welcome to Way2Go ", Toast.LENGTH_LONG).show();
                goToUserEventPage();
            }

        });
    }
    private void goToUserEventPage () {
        Intent intent = new Intent(this, UserEvents.class);
        startActivity(intent);
    }


}