package com.beziercurves.basic;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.view.View;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class PathOpView extends View {

    private Paint mPaint;

    public PathOpView(Context context) {
        super(context);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setStrokeWidth(10);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // DIFFERENCE -- 减去Path2后Path1区域剩下的部分
//        drawDifferenceOp(canvas);

        // INTERSECT --- 保留Path2 和 Path1 共同的部分
//        drawIntersectOp(canvas);

        // UNION -- 保留Path1 和 Path 2
        drawUnionOp(canvas);

        // XOR --- 保留Path1 和 Path2 还有共同的部分
//         drawXorOp(canvas);

        // REVERSE_DIFFERENCE --- 减去Path1后Path2区域剩下的部分
//        drawReverseDifferenceOp(canvas);
    }


    private void drawDifferenceOp(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(150, 150, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(200, 200, 100, Path.Direction.CW);
        path1.op(path2, Path.Op.DIFFERENCE);
        canvas.drawPath(path1, mPaint);
        mPaint.setColor(Color.DKGRAY);
        mPaint.setStrokeWidth(2);
        canvas.drawCircle(150, 150, 100, mPaint);
        canvas.drawCircle(200, 200, 100, mPaint);
    }

    private void drawIntersectOp(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(150, 150, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(200, 200, 100, Path.Direction.CW);
        path1.op(path2, Path.Op.INTERSECT);
        canvas.drawPath(path1, mPaint);
        mPaint.setColor(Color.DKGRAY);
        mPaint.setStrokeWidth(2);
        canvas.drawCircle(150, 150, 100, mPaint);
        canvas.drawCircle(200, 200, 100, mPaint);
    }

    private void drawUnionOp(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(150, 150, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(200, 200, 100, Path.Direction.CW);
        path1.op(path2, Path.Op.UNION);
        canvas.drawPath(path1, mPaint);
        mPaint.setColor(Color.DKGRAY);
        mPaint.setStrokeWidth(2);
        canvas.drawCircle(150, 150, 100, mPaint);
        canvas.drawCircle(200, 200, 100, mPaint);
    }

    private void drawXorOp(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(150, 150, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(200, 200, 100, Path.Direction.CW);
        path1.op(path2, Path.Op.XOR);
        canvas.drawPath(path1, mPaint);
        mPaint.setColor(Color.DKGRAY);
        mPaint.setStrokeWidth(2);
        canvas.drawCircle(150, 150, 100, mPaint);
        canvas.drawCircle(200, 200, 100, mPaint);
    }

    private void drawReverseDifferenceOp(Canvas canvas) {
        Path path1 = new Path();
        path1.addCircle(150, 150, 100, Path.Direction.CW);
        Path path2 = new Path();
        path2.addCircle(200, 200, 100, Path.Direction.CW);
        path1.op(path2, Path.Op.REVERSE_DIFFERENCE);
        canvas.drawPath(path1, mPaint);
        mPaint.setColor(Color.DKGRAY);
        mPaint.setStrokeWidth(2);
        canvas.drawCircle(150, 150, 100, mPaint);
        canvas.drawCircle(200, 200, 100, mPaint);
    }


}
