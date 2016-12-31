package com.android_project.multimedia.processors;

import android.widget.TextView;

public class SensorDataProcessor {
    private TextView sideOrientationTextView;
    private TextView groundOrientationTextView;

    public SensorDataProcessor(TextView sideOrientationTextView, TextView groundOrientationTextView) {
        this.sideOrientationTextView = sideOrientationTextView;
        this.groundOrientationTextView = groundOrientationTextView;
    }

    public void changePhonePositionText(float values[]) {
        changeYawText(values[0]);
        changeGroundOrinetationText(values[2]);
    }

    private void changeYawText(float value) {
        if (value > 0) {
            sideOrientationTextView.setText("Jestem na lewym boku");
        } else if (value < 0) {
            sideOrientationTextView.setText("Jestem na prawym boku");
        } else {
            sideOrientationTextView.setText("Nie jestem na żadnym boku");
        }
    }

    private void changeGroundOrinetationText(float value) {
        if (value > 0) {
            groundOrientationTextView.setText("Jestem skierowany tyłem do ziemi");
        } else if (value < 0) {
            groundOrientationTextView.setText("Jestem skierowany przodem do ziemi");
        } else {
            groundOrientationTextView.setText("Nie jestem skierowany w stronę ziemi");
        }
    }
}
