package com.android_project.multimedia.sub_activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.android_project.multimedia.R;
import com.android_project.multimedia.tasks.AsyncRequestTask;

public class AsyncTaskActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        try {
            new AsyncRequestTask(((ImageView)findViewById(R.id.asyncImageView)), this).execute();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
