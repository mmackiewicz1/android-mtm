package com.android_project.multimedia.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.android_project.multimedia.reponses.WeatherResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherRequestTask extends AsyncTask<URL, Integer, WeatherResponse> {
    private static final String REQUEST_URL = "http://api.openweathermap.org/data/2.5/weather?q=Gdansk,PL&units=metric";
    private static final String API_KEY_PROPERTY = "x-api-key";
    private static final String API_KEY = "";

    private ProgressDialog progressDialog;

    public WeatherRequestTask(Activity activity) {
        progressDialog = new ProgressDialog(activity);
    }

    @Override
    protected WeatherResponse doInBackground(URL... params) {
        try {
            URL url = new URL(REQUEST_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.addRequestProperty(API_KEY_PROPERTY, API_KEY);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            StringBuffer json = new StringBuffer(1024);
            String temp;
            while ((temp = reader.readLine()) != null) {
                json.append(temp).append("\n");
            }

            reader.close();

            JSONObject data = new JSONObject(json.toString());
            if (data.getInt("cod") != 200) {
                return null;
            } else {
                return parseResponse(data);
            }
        } catch(Exception e){
            return null;
        }
    }

    protected void onPreExecute() {
        progressDialog.setMessage("Ładowanie danych pogody.");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(WeatherResponse result) {
        super.onPostExecute(result);
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private WeatherResponse parseResponse(final JSONObject jsonObject) throws JSONException {
        return new WeatherResponse() {{
            setPlace(jsonObject.get("name").toString());
            setMain(jsonObject.getJSONArray("weather").getJSONObject(0).get("main").toString());
            setTemperature(jsonObject.getJSONObject("main").get("temp").toString());
            setPressure(jsonObject.getJSONObject("main").get("pressure").toString());
            setHumidity(jsonObject.getJSONObject("main").get("humidity").toString());
            setWindSpeed(jsonObject.getJSONObject("wind").get("speed").toString());
        }};
    }
}

