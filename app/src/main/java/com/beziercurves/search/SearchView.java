package com.beziercurves.search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.util.Log;
import android.view.View;

public class SearchView extends View {

    private static final String TAG = SearchView.class.getSimpleName();

    private Paint mPaint;

    private Context mContext;

    private int mWidth, mHeight;

    private Path mPathCircle;

    private Path mPathSearch;

    private PathMeasure mMeasure;

    private ValueAnimator mValueAnimator;

    private long DEFAULT_DURATION = 3000;

    private float mCurAnimValue;

    private SearchState mState = SearchState.NONE;

    public SearchView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
        initPaint();
        initPath();
        initAnimation();
    }

    public void initPaint() {
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(3);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    public void initPath() {
        mPathSearch = new Path();
        mPathCircle = new Path();

        mMeasure = new PathMeasure();

        RectF oval1 = new RectF(-50, -50, 50, 50);
        mPathSearch.addArc(oval1, 45, 359.9f);

        RectF oval2= new RectF(-100, -100, 100, 100);
        mPathCircle.addArc(oval2, 45, -359.9f);

        float[] pos = new float[2];

        mMeasure.setPath(mPathCircle, false);
        mMeasure.getPosTan(0, pos, null);

        mPathSearch.lineTo(pos[0], pos[1]);

        Log.d(TAG, "pos=" + pos[0] + ":" + pos[1]);
    }

    public void initAnimation() {
        mValueAnimator = ValueAnimator.ofFloat(0f, 1.0f).setDuration(DEFAULT_DURATION);
        mValueAnimator.addUpdateListener(updateListener);
        mValueAnimator.addListener(animatorListener);
    }

    private ValueAnimator.AnimatorUpdateListener updateListener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            mCurAnimValue = (float) animation.getAnimatedValue();
            invalidate();
        }
    };

    private Animator.AnimatorListener animatorListener = new AnimatorListenerAdapter() {
        @Override
        public void onAnimationEnd(Animator animation) {
            if (mState == SearchState.START) {
                setState(SearchState.SEARCHING);
            }
        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw: ");
        drawPath(canvas);
    }

    private void drawPath(Canvas canvas) {
        canvas.translate(mWidth /2,mHeight/2);
        switch (mState) {
            case NONE:
                canvas.drawPath(mPathSearch, mPaint);
                break;
            case START:
                mMeasure.setPath(mPathSearch, true);
                Path path = new Path();
                mMeasure.getSegment(mMeasure.getLength() * mCurAnimValue, mMeasure.getLength(), path, true);
                canvas.drawPath(path, mPaint);
                break;
            case SEARCHING:
                mMeasure.setPath(mPathCircle, true);
                Path pathSearch = new Path();
                mMeasure.getSegment(mMeasure.getLength() * mCurAnimValue - 30, mMeasure.getLength() * mCurAnimValue, pathSearch, true);
                canvas.drawPath(pathSearch, mPaint);
                break;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    public void setState(SearchState state) {
        this.mState = state;
        startSearch();
    }

    public void startSearch() {
        switch (mState) {
            case START:
                mValueAnimator.setRepeatCount(0);
                break;
            case SEARCHING:
                mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
                mValueAnimator.setRepeatMode(ValueAnimator.REVERSE);
                break;
            case END:
                mValueAnimator.setRepeatCount(0);
                break;
        }
        mValueAnimator.start();
    }

    public enum SearchState {
        START,END,NONE,SEARCHING
    }
}
