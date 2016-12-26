package com.android_project.multimedia.sub_activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android_project.multimedia.R;
import com.android_project.multimedia.reponses.AsyncResponse;
import com.android_project.multimedia.tasks.AsyncRequestTask;

public class AsyncTaskActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        try {
            AsyncResponse asyncResponse = new AsyncRequestTask(this).execute().get();

            ((TextView)findViewById(R.id.userIdTextView)).setText(asyncResponse.getUserId());
            ((TextView)findViewById(R.id.idTextView)).setText(asyncResponse.getId());
            ((TextView)findViewById(R.id.titleTextView)).setText(asyncResponse.getTitle());
            ((TextView)findViewById(R.id.bodyTextView)).setText(asyncResponse.getBody());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
