package com.android_project.multimedia.sub_activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.android_project.multimedia.R;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class CameraOpenCVActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {
    private CameraBridgeViewBase cameraBridgeView;

    private BaseLoaderCallback baseLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    cameraBridgeView.enableView();
                }
                break;

                default: {
                    super.onManagerConnected(status);
                }
                break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_open_cv);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        cameraBridgeView = (CameraBridgeViewBase) findViewById(R.id.openCVCameraView);
        cameraBridgeView.setVisibility(SurfaceView.VISIBLE);
        cameraBridgeView.setCvCameraViewListener(this);
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
    }

    @Override
    public void onCameraViewStopped() {
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Mat inputFrameRGBA = inputFrame.rgba();
        Mat grayScaledMat = new Mat();
        Imgproc.cvtColor(inputFrameRGBA, grayScaledMat, Imgproc.COLOR_BGR2GRAY);

        Mat thresh = new Mat();
        Imgproc.threshold(grayScaledMat, thresh, 30, 255, Imgproc.THRESH_BINARY_INV);

        return thresh;
    }

    @Override
    public void onResume() {
        super.onResume();
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_9, this, baseLoaderCallback);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (cameraBridgeView != null)
            cameraBridgeView.disableView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (cameraBridgeView != null)
            cameraBridgeView.disableView();
    }
}
