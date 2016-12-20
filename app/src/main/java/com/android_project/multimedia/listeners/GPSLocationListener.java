package com.android_project.multimedia.listeners;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

public class GPSLocationListener extends Service implements LocationListener {
    private TextView latitudeTextView;
    private TextView longitudeTextView;
    private TextView altitudeTextView;
    private TextView speedTextView;

    public GPSLocationListener(
            TextView latitudeTextView,
            TextView longitudeTextView,
            TextView altitudeTextView,
            TextView speedTextView) {
        this.latitudeTextView = latitudeTextView;
        this.longitudeTextView = longitudeTextView;
        this.altitudeTextView = altitudeTextView;
        this.speedTextView = speedTextView;
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location == null) {
            return;
        }

        latitudeTextView.setText(String.valueOf(location.getLatitude()));
        longitudeTextView.setText(String.valueOf(location.getLongitude()));
        altitudeTextView.setText(String.valueOf(location.getAltitude()));
        speedTextView.setText(String.valueOf((int) ((location.getSpeed() * 3600) / 1000)) + " km/h");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.i("Provider", "Provider enabled.");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.i("Provider", "Provider disabled");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
