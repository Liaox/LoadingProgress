package com.peng.progress;

import android.content.Context;
import android.graphics.*;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import com.peng.progress.base.BaseBuilder;
import com.peng.progress.base.BaseProgress;

/**
 * Created by peng on 16-10-18.
 * The CircleProgress class
 * extends BaseProgress
 */
public class CircleProgress extends BaseProgress {

    //The Paint of the Ring
    private Paint mCirclePaint;
    //The Size of the Ring
    private float mCircleWidth;
    //The progress Color for the Ring
    private int mCircleProgressColor;
    //The bottom color for the Ring
    private int mCircleBottomColor;
    //the radius of the Ring
    private int mCircleRadius;
    //The Style of the Circle
    private CircleStyle mCircleStyle=CircleStyle.RING;

    /*
        initCircleProgress
     */

    @Override
    public void initProperty() {
        super.initProperty();
        //init mCircleproerty
        mCircleProgressColor = 0xaa59c8cc;
        mCircleBottomColor = 0x80000000;
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStrokeWidth(10);
        mCircleWidth = 8;
        mCircleRadius = 40;

    }

    @Override
    public void DrawOther(Canvas canvas) {
        drawCircle(canvas);
        drawArc(canvas);
    }

    /*
        draw the circle background
     */
    private void drawCircle(Canvas canvas) {
        mCirclePaint.setColor(mCircleBottomColor);
        mCirclePaint.setStrokeWidth(mCircleWidth / 2);
        mCirclePaint.setStyle(mCircleStyle==CircleStyle.RING? Paint.Style.STROKE:Paint.Style.FILL);
        Rect bounds = getBounds();

        int xpos = bounds.left + bounds.width() / 2;
        int ypos = bounds.bottom - bounds.height() / 2;
        canvas.drawCircle(xpos, ypos, mCircleStyle==CircleStyle.RING?mCircleRadius:mCircleRadius+1, mCirclePaint);
    }

    /*
    draw the arc for the progress
     */
    private void drawArc(Canvas canvas) {
        mCirclePaint.setColor(mCircleProgressColor);
        mCirclePaint.setStrokeWidth(mCircleWidth);
        Rect bounds = getBounds();
        int xpos = bounds.left + bounds.width() / 2;
        int ypos = bounds.bottom - bounds.height() / 2;
        RectF rectF = new RectF(xpos - mCircleRadius, ypos - mCircleRadius, xpos + mCircleRadius,
                ypos + mCircleRadius);
        float degree = (float) mProgress / (float) MAX_VALUE * 360;
        mCirclePaint.setStyle(mCircleStyle==CircleStyle.RING? Paint.Style.STROKE:Paint.Style.FILL);
        canvas.drawArc(rectF, 270, degree, mCircleStyle==CircleStyle.FAN, mCirclePaint);
    }


    public static class Builder extends BaseBuilder<CircleProgress, Builder> {
        public Builder() {
            mProgress = new CircleProgress();
        }

        public Builder setCircleWidth(float width) {
            mProgress.mCircleWidth = width;
            return this;
        }

        public Builder setCircleRadius(int Radius) {
            mProgress.mCircleRadius = Radius;

            return this;
        }

        public Builder setCircleProgressColor(@ColorInt int Color) {
            mProgress.mCircleProgressColor = Color;

            return this;
        }

        public Builder setCircleBottomColor(@ColorInt int Color) {
            mProgress.mCircleBottomColor = Color;
            return this;
        }

        public Builder setCircleBottomColorRes(@ColorRes int ColorRes, Context context) {
            mProgress.mCircleBottomColor = context.getResources().getColor(ColorRes);
            return this;
        }

        public Builder setCircleProgressColorRes(@ColorRes int ColorRes, Context context) {
            mProgress.mCircleProgressColor = context.getResources().getColor(ColorRes);
            return this;
        }

        public Builder setCircleStyle(CircleStyle style) {
            mProgress.mCircleStyle = style;
            return this;
        }
    }


    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
    }

    @Override
    public int getOpacity() {
        return 0;
    }

}
