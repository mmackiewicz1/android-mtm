package com.android_project.multimedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android_project.multimedia.sub_activities.CameraActivity;
import com.android_project.multimedia.sub_activities.DrawActivity;
import com.android_project.multimedia.sub_activities.GPSActivity;
import com.android_project.multimedia.sub_activities.SensorActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToSensorActivity(View view) {
        Intent intent = new Intent(this, SensorActivity.class);
        startActivity(intent);
    }

    public void goToGPSActivity(View view) {
        Intent intent = new Intent(this, GPSActivity.class);
        startActivity(intent);
    }

    public void goToCameraActivity(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    public void goToDrawActivity(View view) {
        Intent intent = new Intent(this, DrawActivity.class);
        startActivity(intent);
    }
}