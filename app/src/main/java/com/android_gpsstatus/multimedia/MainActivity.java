package com.android_gpsstatus.multimedia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android_gpsstatus.multimedia.sub_activities.SensorActivity;

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
}