package com.example.project;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


//we need to add here to read the particapint file and yes to add a button
public class UserEvents extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String text;
        final String user;
        final String eventname;
        final String path;
        final String path2;
        String text1;
        Button button_manage, button_participant;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_events);
        button_manage = (Button) findViewById(R.id.manage_a_new_activity);
        button_participant = (Button) findViewById(R.id.sing_into_activity);
        Intent in = getIntent();
        user = in.getStringExtra("username");
        eventname = in.getStringExtra("eventname");
        FileInputStream fileInputStream = null, fileInputStream1 = null, fileInputStream2= null;
        InputStream inputStream = null, inputStream1=null, inputStream2 = null;
        path = "/data/data/com.example.project/files/";
        path2 = "/data/data/com.example.project/files/eventnames.txt";


        try {
            try {
                //open eventnames.txt file
                inputStream = new FileInputStream(path2);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            Reader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            try {
                //for each event try to see if the user is managing it
                while ((text1 = bufferedReader.readLine()) != null)
                    try {
                        inputStream1 = new FileInputStream(path + text1 + ".txt");

                        Reader inputStreamReader1 = new InputStreamReader(inputStream1);

                        BufferedReader bufferedReader1 = new BufferedReader(inputStreamReader1);
                        StringBuilder stringBuilder1 = new StringBuilder();
                        try {

                            while ((text = bufferedReader1.readLine()) != null)
                                if (text.equals(user)) {

                                    //add a button to the layout
                                    final RelativeLayout rl = (RelativeLayout) findViewById(R.id.layout);
                                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                    layoutParams.setMargins(100, 1100, 100, 40);
                                    Button add_btn = new Button(this);
                                    add_btn.setText(eventname);

                                    rl.addView(add_btn, layoutParams);
                                    final Context context = this;

                                    add_btn.setOnClickListener(new View.OnClickListener() {

                                        @Override
                                        public void onClick(View v) {

                                            //  goToActivity.needs to move to function once we build the page
                                            Intent intent = new Intent(context, getNumEvent.class);
                                            intent.putExtra("username", user);
                                            startActivity(intent);
                                        }
                                    });
                                    this.setContentView(rl);
                                    break;
                                }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        //try to see if user participating in text1 = eventname
                        try {
                            inputStream2 = new FileInputStream(path + text1 +" partic_names" + ".txt");
                            Reader inputStreamReader2 = new InputStreamReader(inputStream2);

                            BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);
                            StringBuilder stringBuilder2 = new StringBuilder();
                            try {
                                while ((text = bufferedReader2.readLine()) != null)
                                    if (text.equals(user)) {
                                        //reads the name of event
                                        //add a button to the layout
                                        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.layout);
                                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                                                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                        layoutParams.setMargins(100, 100, 100, 1140);
                                        Button add_btn = new Button(this);
                                        add_btn.setText(eventname);

                                        rl.addView(add_btn, layoutParams);
                                        final Context context = this;

                                        add_btn.setOnClickListener(new View.OnClickListener() {

                                            @Override
                                            public void onClick(View v) {

                                                //  goToActivity.needs to move to function once we build the page
                                                Intent intent = new Intent(context, getNumEvent.class);
                                                intent.putExtra("username", user);
                                                startActivity(intent);
                                            }
                                        });
                                        this.setContentView(rl);
                                        break;
                                    }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (FileNotFoundException e) {
                        }

            }catch (Exception e){}
            } catch (Exception e) {
            }

            button_manage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToActivityMangerPage();
                }
            });

            button_participant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    goToActivityParticipantPage();

                }
            });
        }finally {

        }
    }

    private void goToActivityMangerPage() {
        Intent intent = new Intent(this, activity_manager.class);
        startActivity(intent);
    }

    private void goToActivityParticipantPage() {
        Intent intent = new Intent(this, ActivityParticipant.class);
        startActivity(intent);
    }

}