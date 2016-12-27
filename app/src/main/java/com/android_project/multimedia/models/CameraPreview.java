package com.android_project.multimedia.models;
import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    private static final int FIXED_ORIENTATION = 90;

    private SurfaceHolder surfaceHolder;
    private Camera camera;

    public CameraPreview(Context context) {
        super(context);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        camera = Camera.open();
        camera.setDisplayOrientation(FIXED_ORIENTATION);

        try {
            camera.setPreviewDisplay(holder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        camera.stopPreview();
        camera.release();
        camera = null;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        Camera.Parameters parameters = camera.getParameters();

        camera.getParameters().setPreviewSize(640, 480);
        holder.setFixedSize(240, 320);

        camera.setParameters(parameters);
        camera.startPreview();
    }

    public Camera getCamera() {
        return camera;
    }
}
