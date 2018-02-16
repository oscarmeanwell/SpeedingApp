package com.example.oscarmeanwell.sensorslab;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.test.espresso.core.deps.guava.base.Splitter;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import de.nitri.gauge.Gauge;

//https://overpass-api.de/api/interpreter?data=[out:json][timeout:25];(way(around:5,55.927313,-3.209288)[%22maxspeed%22];);(._;%3E;);out;
//https://overpass-api.de/api/interpreter?data=[out:json][timeout:25];(way(around:5,55.927313,-3.209288)["maxspeed"];);out;

public class MainActivity extends AppCompatActivity {
    TextView txtLoc;
    Button btn;
    Gauge gauge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        super.setTitle("Drive Safety");
        gauge = (Gauge) findViewById(R.id.gauge);
        txtLoc = (TextView) findViewById(R.id.txt);
        final LocationManager locationManager = (LocationManager) this
                .getSystemService(Context.LOCATION_SERVICE);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.notification);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Define a listener that responds to location updates
        android.location.LocationListener locationListener = new android.location.LocationListener() {
            public void onLocationChanged(Location location) {
                int limit = getSpeedLimit(location.getLatitude(), location.getLongitude());
                if (location.getSpeed() > limit) {
                    System.out.println("YOU ARE SPEEDING");
                    Toast.makeText(getApplicationContext(), "You are Speeding", Toast.LENGTH_SHORT);
                    mp.start();
                } else {
                    mp.stop();
                    System.out.println("Safe driver");
                }
                gauge.moveToValue(location.getSpeed());
                txtLoc.setText("Speed Limit is: " + limit);

            }

            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
                0, (android.location.LocationListener) locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0,
                0, (android.location.LocationListener) locationListener);

    }

    int getSpeedLimit(double lat, double lon) {
        int speed = 0;
        try {
            //JSONObject json = new JSONObject(IOUtils.toString(new URL("https://overpass-api.de/api/interpreter?data=[out:json][timeout:25];(way(around:5,55.927313,-3.209288)[\"maxspeed\"];);out;"), Charset.forName("UTF-8")));
            JSONObject json = new JSONObject(IOUtils.toString(new URL(String.format("https://overpass-api.de/api/interpreter?data=[out:json][timeout:25];(way(around:5,%f,%f)[\"maxspeed\"];);out;", lat, lon)), Charset.forName("UTF-8")));
            String tmp = "";
            tmp = json.get("elements").toString();
            String new1 = "";
            for (int i = 0; i < tmp.length(); i++) {
                if (String.valueOf(tmp.charAt(i)).equals("t") && String.valueOf(tmp.charAt(i + 1)).equals("a") && String.valueOf(tmp.charAt(i + 2)).equals("g") && String.valueOf(tmp.charAt(i + 3)).equals("s")) {
                    new1 = tmp.substring(i + 6, tmp.length() - 2);
                }
            }
            JSONObject newJ = new JSONObject(new1);
            speed = Integer.parseInt(newJ.get("maxspeed").toString().substring(0, 2));
            System.out.println("New is: " + speed);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return speed;
    }
}



