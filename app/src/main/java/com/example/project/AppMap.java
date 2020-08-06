package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.SphericalUtil;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.mail.MessagingException;

public class AppMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String address, eventname, user, email;
    int y = 0;
    InputStream inputStream;
    TreeSet<Point> distance = new TreeSet<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent in = getIntent();
        address = in.getStringExtra("address");
        eventname = in.getStringExtra("eventname");
        user = in.getStringExtra("username");
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

        LatLng point;
        Context context = this;

        point = getLocationFromAddress(context, address);
        createMarkOnMap(point);

        OpenFileForPeerInfo(path, eventname, context);
        get3ClosestPeers();

    }

    private void createMarkOnMap(LatLng point) {
        mMap.addMarker(new MarkerOptions().position(point).title("My address"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(point));

    }

    private void OpenFileForPeerInfo(String path, String eventname, Context context) {
        try {
            //open details
            inputStream = new FileInputStream(path + eventname + "_partic_details.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        LatLng useraddress = getLocationFromAddress(context, address);
        try {
            int count = 0;
            String name = "", line, text = "";
            while ((line = bufferedReader.readLine()) != null) {
                text += ("          " + line);
                if (count % 2 != 0) {
                    LatLng peeraddress = getLocationFromAddress(context, line);
                    createMarkOnMap(peeraddress);
                    Double distanceBetween = SphericalUtil.computeDistanceBetween(peeraddress, useraddress);
                    Point pr = new Point(distanceBetween, name);
                    distance.add(pr);
                    text = "";
                } else {
                    name = line;
                }

                ++count;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void CreatePeerInfo(final String text) {
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.rel);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(100, 50 + y, 100, 640);
        y = y + 70;
        final Button add_btn = new Button(this);
        add_btn.setText(text);
        rl.addView(add_btn, layoutParams);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                email = getPeersMailAddress(text);

                try {
                    new GmailSender(email, user, eventname).execute();
                    Toast.makeText(AppMap.this, "you've got a way2go! email was sent to the driver", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }

            }

        });


        final Context context = this;
    }

    ;

    private String getPeersMailAddress(String peer) {
        String path = "/data/data/com.example.project/files/test.txt", text;
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


        try {
            //open test.txt and find email of user you want to join
            while ((text = bufferedReader.readLine()) != null) {
                if (text.equals(peer)) {
                    text = bufferedReader.readLine();
                    return bufferedReader.readLine();
                }
            }
        } catch (IOException e) {

        }
        return "null";
    }

    public LatLng getLocationFromAddress(Context context, String strAddress) {

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


        private void get3ClosestPeers () {
            Iterator iterator;
            iterator = distance.iterator();
            int count = 0;
            while (iterator.hasNext()) {
                point = (Point) iterator.next();
                if (point.getPair().second.equals(user))
                    continue;
                CreatePeerInfo(point.getPair().second);
                count++;
            }
        }

        private Point point;

    }

