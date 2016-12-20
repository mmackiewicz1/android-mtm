package com.android_project.multimedia.sub_activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.android_project.multimedia.R;
import com.android_project.multimedia.views.MyDrawView;

public class DrawActivity extends AppCompatActivity {
    private MyDrawView myDrawView;

    public void clearDrawing(View view) {
        myDrawView.resetPath();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);

        myDrawView = (MyDrawView)findViewById(R.id.drawingView);
    }
}
