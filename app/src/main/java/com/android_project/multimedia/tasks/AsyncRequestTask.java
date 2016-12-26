package com.android_project.multimedia.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.android_project.multimedia.reponses.AsyncResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class AsyncRequestTask extends AsyncTask<URL, Integer, AsyncResponse> {
    private static final String REQUEST_URL = "http://jsonplaceholder.typicode.com/posts/1";
    private static final int BUFFER_SIZE = 32;

    private ProgressDialog progressDialog;

    public AsyncRequestTask(Activity activity) {
        progressDialog = new ProgressDialog(activity);
    }

    @Override
    protected AsyncResponse doInBackground(URL... params) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader((new URL(REQUEST_URL).openConnection()).getInputStream()));

            StringBuffer jsonData = new StringBuffer(BUFFER_SIZE);
            String temp;
            while ((temp = reader.readLine()) != null) {
                jsonData.append(temp).append("\n");
            }

            reader.close();

            return new ObjectMapper().readValue(jsonData.toString(), AsyncResponse.class);
        } catch(Exception e){
            return null;
        }
    }

    protected void onPreExecute() {
        progressDialog.setMessage("Ładowanie danych REST.");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(AsyncResponse result) {
        super.onPostExecute(result);
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}

