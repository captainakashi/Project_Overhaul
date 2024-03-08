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
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectoverhaul.databinding.ActivityMainBinding;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    TextView marquee;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);



        marquee = findViewById(R.id.marquee);
        marquee.setSelected(true);

        Button map = (Button) findViewById(R.id.btnmap);

        map.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Dashboard.this, mapbutton.class));
            }
        });

        Button mechanic = (Button) findViewById(R.id.btnMechanic);

        mechanic.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Dashboard.this, Mechanic.class));
            }
        });

        Button mechanic2 = (Button) findViewById(R.id.btnMechanic2);

        mechanic2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Dashboard.this, Mechanic2.class));
            }
        });

        Button tow = (Button) findViewById(R.id.btnTow);

        tow.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(Dashboard.this, Tow.class));
            }
        });

    }
}