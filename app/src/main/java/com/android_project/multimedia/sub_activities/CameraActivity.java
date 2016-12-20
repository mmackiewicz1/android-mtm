package com.android_project.multimedia.sub_activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.android_project.multimedia.R;
import com.android_project.multimedia.listeners.OnZoomSeekBarListener;
import com.android_project.multimedia.models.CameraPreview;

public class CameraActivity extends AppCompatActivity {
    private SeekBar zoomSeekBar;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        relativeLayout = (RelativeLayout)findViewById(R.id.cameraLayout);
        CameraPreview cameraPreview = new CameraPreview(this);
        relativeLayout.addView(cameraPreview);

        zoomSeekBar = (SeekBar)findViewById(R.id.zoomSeekBar);
        zoomSeekBar.setOnSeekBarChangeListener(new OnZoomSeekBarListener(cameraPreview));
    }
}
