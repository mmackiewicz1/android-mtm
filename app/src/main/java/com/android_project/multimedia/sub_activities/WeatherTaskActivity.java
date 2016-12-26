package com.android_project.multimedia.sub_activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android_project.multimedia.R;
import com.android_project.multimedia.reponses.WeatherResponse;
import com.android_project.multimedia.tasks.WeatherRequestTask;

public class WeatherTaskActivity extends AppCompatActivity {
    private static final String TEMPERATURE_STRING_TEMPLATE = "%s C";
    private static final String PRESSURE_STRING_TEMPLATE = "%s hPa";
    private static final String HUMIDITY_STRING_TEMPLATE = "%s %%";
    private static final String WIND_SPEED_STRING_TEMPLATE = "%s m/s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        try {
            WeatherResponse weatherResponse = new WeatherRequestTask(this).execute().get();

            ((TextView)findViewById(R.id.placeTextView)).setText(weatherResponse.getPlace());
            ((TextView)findViewById(R.id.skyTextView)).setText(weatherResponse.getMain());
            ((TextView)findViewById(R.id.temperatureTextView)).setText(String.format(TEMPERATURE_STRING_TEMPLATE, weatherResponse.getTemperature()));
            ((TextView)findViewById(R.id.pressureTextView)).setText(String.format(PRESSURE_STRING_TEMPLATE, weatherResponse.getPressure()));
            ((TextView)findViewById(R.id.humidityTextView)).setText(String.format(HUMIDITY_STRING_TEMPLATE, weatherResponse.getHumidity()));
            ((TextView)findViewById(R.id.windSpeedTextView)).setText(String.format(WIND_SPEED_STRING_TEMPLATE, weatherResponse.getWindSpeed()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
