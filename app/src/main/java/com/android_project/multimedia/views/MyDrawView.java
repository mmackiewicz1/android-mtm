package com.android_project.multimedia.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyDrawView extends View {
    private static final float TOUCH_TOLERANCE = 4;

    private Paint paint;
    private Path path;
    private float x, y;

    public MyDrawView(Context context) {
        super(context);
        initializeView();
    }

    public MyDrawView(Context context, AttributeSet attrs){
        super(context, attrs);
        initializeView();
    }

    public void resetPath() {
        path.reset();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                actionDown(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                actionMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                actionUp();
                invalidate();
                break;
        }

        return true;
    }

    private void initializeView() {
        paint = new Paint();
        paint.setDither(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(10);

        path = new Path();
    }

    private void actionDown(float x, float y) {
        path.moveTo(x, y);

        this.x = x;
        this.y = y;
    }

    private void actionMove(float x, float y) {
        float differenceX = Math.abs(x - this.x);
        float differenceY = Math.abs(y - this.y);

        if (differenceX >= TOUCH_TOLERANCE || differenceY >= TOUCH_TOLERANCE) {
            path.quadTo(this.x, this.y, (x + this.x)/2, (y + this.y)/2);
            this.x = x;
            this.y = y;
        }
    }

    private void actionUp() {
        path.lineTo(x, y);
    }
}
