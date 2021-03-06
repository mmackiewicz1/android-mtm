package com.android_project.multimedia.sub_activities;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android_project.multimedia.R;
import com.android_project.multimedia.processors.SensorDataProcessor;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private SensorDataProcessor sensorDataProcessor;

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.i("Sensor", "Data: " + sensor.getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorDataProcessor = new SensorDataProcessor(
                (TextView)findViewById(R.id.sideOrientationTextView),
                (TextView)findViewById(R.id.groundOrientationTextView)
        );
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    private void getAccelerometer(SensorEvent event) {
        ((TextView)findViewById(R.id.xTextView)).setText(String.valueOf(event.values[0]));
        ((TextView)findViewById(R.id.yTextView)).setText(String.valueOf(event.values[1]));
        ((TextView)findViewById(R.id.zTextView)).setText(String.valueOf(event.values[2]));

        sensorDataProcessor.changePhonePositionText(event.values);
    }
}
