package com.example.projectoverhaul;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class mapbutton extends AppCompatActivity {


    public static java.lang.StringBuffer stringBuffer = new StringBuffer();

    ListView centersListView;

    double latitude, longitude;

    Button scanButton, viewMapButton;

    LocationManager locationManager;

    Location location;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapbutton);






        viewMapButton = (Button) findViewById(R.id.viewMapButton);

        viewMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewMapButton();
            }
        });


        this.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                try {

                    updateLoc();

                    GeometryController.loading = true;

                    loadLocation();

                    while (GeometryController.loading) {
                        Log.d("Message=>>>>", "Waiting");
                    }


                } catch (IllegalArgumentException e) {
                    Toast.makeText(mapbutton.this, e.getMessage(), Toast.LENGTH_LONG).show();

                    e.printStackTrace();
                    finish();
                }
            }
        });
    }

    void viewMapButton() {

        Intent intent = new Intent(mapbutton.this, MapsActivity.class);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        startActivity(intent);
    }


    public void updateLoc() {

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            throw new IllegalArgumentException("No GPS");
        } else if (!isGooglePlayServicesAvailable(this)) {
            throw new IllegalArgumentException("No Google Play Services Available");
        } else getLocation();

    }

    public boolean isGooglePlayServicesAvailable(Context context) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(context);
        return resultCode == ConnectionResult.SUCCESS;
    }

    void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Log.d("Permission", "Not Granted Improving back");
            return;
        }

        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (location != null) {
            Log.d("Achieved latitude=>", location.getLatitude() + ", longitide=> " + location.getLongitude());
        }

        if (location == null) {
            Log.d("GPS PRovider", "Enabled");
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

        if (location == null) throw new IllegalArgumentException("Cann't trace location");

        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    void loadLocation() {
        try {
            new RetrieveFeedTask().execute();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (stringBuffer.length() == 0) Log.d("Message", "buffer reading");
                    GeometryController.manipulateData(mapbutton.stringBuffer);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class RetrieveFeedTask extends AsyncTask<StringBuffer, StringBuffer, StringBuffer > {

        @SuppressLint("LongLogTag")
        @Override
        protected StringBuffer doInBackground(StringBuffer... stringBuffers) {
            try {
                /** initializing StringBuilder  */
                StringBuilder stringBuilder = new StringBuilder()
                        .append("https://maps.googleapis.com/maps/api/place/search/json?rankby=distance&keyword=autorepairshop&location=")
                        .append(latitude)
                        .append(",")
                        .append(longitude)
                        .append("&key=AIzaSyC6-gwhsbRMAbtSNhR56y2EBV9S16bZhHE&sensor=false&libraries=places");

                /** searching for url */
                URL url = new URL(stringBuilder.toString());
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer buffer = new StringBuffer();

                String n = "";
                while((n=bufferedReader.readLine())!=null){
                    buffer.append(n);
                }

                Log.d("loaded with size of  => ", "Size is " + buffer.length());

                mapbutton.stringBuffer = buffer;
                return buffer;

            } catch (Exception e) {
                return null;
            }
        }
    }
}