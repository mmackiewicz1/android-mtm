package com.android_project.multimedia.views;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;
import static android.view.MotionEvent.ACTION_UP;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class MyDrawView extends View {
    private static final float TOUCH_DIFFERENCE = 5;
    private static final float BORDER_STROKE_WIDTH = 2;
    private static final float DRAWING_STROKE_WIDTH = 8;

    private Paint paint;
    private Path path;
    private float x;
    private float y;

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
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case ACTION_DOWN:
                actionDown(x, y);
                invalidate();
                break;
            case ACTION_MOVE:
                actionMove(x, y);
                invalidate();
                break;
            case ACTION_UP:
                actionUp();
                invalidate();
                break;
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
        drawBorder(canvas);
    }

    private void initializeView() {
        paint = new Paint();
        paint.setDither(true);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(DRAWING_STROKE_WIDTH);

        path = new Path();
    }

    private void actionDown(float x, float y) {
        path.moveTo(x, y);
        this.x = x;
        this.y = y;
    }

    private void actionMove(float x, float y) {
        if (Math.abs(x - this.x) >= TOUCH_DIFFERENCE || Math.abs(y - this.y) >= TOUCH_DIFFERENCE) {
            path.quadTo(this.x, this.y, (x + this.x)/2, (y + this.y)/2);
            this.x = x;
            this.y = y;
        }
    }

    private void actionUp() {
        path.lineTo(x, y);
    }

    private void drawBorder(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(BORDER_STROKE_WIDTH);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
    }
}
