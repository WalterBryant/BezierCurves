package com.beziercurves.basic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CurveView extends View {
    private float mStartX, mStartY;
    private float mEndX, mEndY;
    private float mControlX = 200, mControlY = 60;
    private Paint mPaint;
    private Path mPath;

    public CurveView(Context context) {
        this(context, null);
    }

    public CurveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(4);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mStartX = 60;
        mStartY = 350;
        mEndX = 450;
        mEndY = 350;
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        mPath.moveTo(mStartX, mStartY);
        mPath.quadTo(mControlX, mControlY, mEndX, mEndY);

        canvas.drawPath(mPath, mPaint);
        canvas.drawPoint(mControlX, mControlY, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            mControlX = event.getX();
            mControlY = event.getY();
            invalidate();
        }
        return true;
    }
}
