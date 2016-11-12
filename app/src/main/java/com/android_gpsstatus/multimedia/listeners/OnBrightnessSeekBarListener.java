package com.android_gpsstatus.multimedia.listeners;

import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.view.TextureView;
import android.widget.SeekBar;

public class OnBrightnessSeekBarListener implements SeekBar.OnSeekBarChangeListener {
    private TextureView textureView;
    private int progressChanged = 0;

    public OnBrightnessSeekBarListener(TextureView textureView) {
        this.textureView = textureView;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        progressChanged = progress;
        textureView.setSurfaceTexture(new SurfaceTexture(Color.WHITE));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
