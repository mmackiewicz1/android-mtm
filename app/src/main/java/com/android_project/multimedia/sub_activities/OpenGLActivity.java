package com.android_project.multimedia.sub_activities;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android_project.multimedia.R;
import com.android_project.multimedia.models.CustomRenderer;

public class OpenGLActivity extends AppCompatActivity {
    private GLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gl);

        glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setRenderer(new CustomRenderer(this));
        setContentView(glSurfaceView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }
}
