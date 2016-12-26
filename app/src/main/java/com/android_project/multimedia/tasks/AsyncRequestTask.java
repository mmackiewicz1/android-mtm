package com.android_project.multimedia.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.URL;

public class AsyncRequestTask extends AsyncTask<String, Void, Bitmap> {
    private static final String REQUEST_URL = "https://i.imgsafe.org/1857455bd9.jpg";

    private ImageView imageView;
    private ProgressDialog progressDialog;

    public AsyncRequestTask(ImageView imageView, Activity activity) {
        this.imageView = imageView;
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(activity);
        }
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(new URL(REQUEST_URL).openStream());
        } catch(Exception e){
            throw new RuntimeException(e);
        }

        return bitmap;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        progressDialog.setMessage("Ładowanie danych obrazu.");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        if (result != null) {
            imageView.setImageBitmap(result);
        }
    }
}

