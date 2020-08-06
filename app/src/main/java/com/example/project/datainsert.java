//package com.example.project;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class datainsert extends AppCompatActivity {
//
//        EditText managerName, date, time, address, nameOfevent;
//        Button btnsubmit;
//        DatabaseReference reff;
//        EventTable eventTable;
//
//        //    Databasehelper eventDB;
////    private Button maneger_button;
////    private Button participant_button;
//        @Override
//        protected void onCreate (Bundle savedInstanceState){
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_manager);
//            managerName = findViewById(R.id.managerName);
//            date = (EditText) findViewById(R.id.date);
//            time = (EditText) findViewById(R.id.time);
//            address = (EditText) findViewById(R.id.address);
//            nameOfevent = (EditText) findViewById(R.id.nameOfevent);
//            btnsubmit = (Button) findViewById(R.id.btnsubmit);
//            eventTable = new EventTable();
//            reff = FirebaseDatabase.getInstance().getReference().child("EventTable");
//            btnsubmit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    eventTable.setManagerName(managerName.getText().toString().trim());
//                    eventTable.setManagerName(date.getText().toString().trim());
//                    eventTable.setManagerName(time.getText().toString().trim());
//                    eventTable.setManagerName(address.getText().toString().trim());
//                    eventTable.setManagerName(nameOfevent.getText().toString().trim());
//
//                    reff.push().setValue(eventTable);
//                    Toast.makeText(datainsert.this, "data inserted successfully", Toast.LENGTH_LONG).show();
//                }
//            });
//        }
//
//}
