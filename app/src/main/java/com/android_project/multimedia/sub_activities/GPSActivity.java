package com.android_project.multimedia.sub_activities;

import android.content.Context;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android_project.multimedia.R;
import com.android_project.multimedia.listeners.GPSLocationListener;

public class GPSActivity extends AppCompatActivity {
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        locationListener = new GPSLocationListener(
                (TextView)findViewById(R.id.latitude_text_view),
                (TextView)findViewById(R.id.longitude_text_view),
                (TextView)findViewById(R.id.altitude_text_view),
                (TextView)findViewById(R.id.speed_text_view)
        );

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(locationManager.getBestProvider(getCriteria(), true), 0, 0, locationListener);
    }

    private Criteria getCriteria() {
        Criteria criteria = new Criteria();
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setSpeedRequired(true);
        criteria.setAltitudeRequired(true);
        criteria.setCostAllowed(false);

        return criteria;
    }
}
