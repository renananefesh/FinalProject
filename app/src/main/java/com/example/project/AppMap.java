package com.example.project;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.GeoPoint;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

public class AppMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String address, eventname;
    int y = 0;
    InputStream inputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent in = getIntent();
        address = in.getStringExtra("address");
        eventname = in.getStringExtra("eventname");

        setContentView(R.layout.activity_app_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        String path = "/data/data/com.example.project/files/";
        // Add a marker in Sydney and move the camera
        LatLng point;
        Context context =this;
        point = getLocationFromAddress(context,"Sydney");

        LatLng sydney = new LatLng(-34, 151);

        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        OpenFileForPeerInfo(path, eventname);
    }

    private void OpenFileForPeerInfo(String path, String eventname) {
        try {
            //open details
            inputStream = new FileInputStream(path + eventname + "_partic_details.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        try {
            int count = 0;
            String line, text = "";
            while ((line = bufferedReader.readLine()) != null) {
                text += ("          " + line);
                if (count % 2 != 0) {
                    CreatePeerInfo(text);
                    text = "";
                }
                ++count;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void CreatePeerInfo(String text) {
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.rel);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(100, 50 + y, 100, 640);
        y = y + 70;
        final Button add_btn = new Button(this);
        add_btn.setText(text);
        rl.addView(add_btn, layoutParams);

//
//            RelativeLayout llMain = findViewById(R.id.rel);
//            TextView textView = new TextView(this);
//            textView.setText("I am added dynamically to the view");
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//           params.setMargins(100, 350+y, 100, 640);
//            y = y + 150;
//
//            textView.setLayoutParams(params);
//            llMain.addView(textView);
        final Context context = this;
    }


        public LatLng getLocationFromAddress (Context context, String strAddress){

            Geocoder coder = new Geocoder(context);
            List<Address> address;
            LatLng p1 = null;

            try {
                // May throw an IOException
                address = coder.getFromLocationName(strAddress, 5);
                if (address == null) {
                    return null;
                }

                Address location = address.get(0);
                p1 = new LatLng(location.getLatitude(), location.getLongitude());

            } catch (IOException ex) {

                ex.printStackTrace();
            }

            return p1;
        }

}

