package com.android_gpsstatus.multimedia.listeners;

import android.hardware.Camera;
import android.util.Log;
import android.widget.SeekBar;

import com.android_gpsstatus.multimedia.models.CameraPreview;

public class OnZoomSeekBarListener implements SeekBar.OnSeekBarChangeListener {
    private CameraPreview cameraPreview;

    public OnZoomSeekBarListener(CameraPreview cameraPreview) {
        this.cameraPreview = cameraPreview;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        Camera.Parameters parameters = cameraPreview.getCamera().getParameters();
        parameters.setZoom(progress);

        cameraPreview.getCamera().setParameters(parameters);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
