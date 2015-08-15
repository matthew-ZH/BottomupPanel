package com.zly.BottomUpPanel;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;

/**
 * Created by mattlyzheng on 2015/8/15.
 */
public class PopupView extends View {
    private int mHeight;
    private int mWidth;
    private int screen_height;
    private int screen_width;
    private int maxOffset;
    private int offset=0;
    private int keyPoint;
    private Paint mPaint;
    private Path mPath;
    private int color;

    public void setColor(int color) {
        this.color = color;
    }

    public PopupView(Context context) {
        this(context, null);
    }

    public PopupView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PopupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        prepareResource();
    }

    public void setMaxOffset(int maxOffset) {
        this.maxOffset = maxOffset;
        mHeight = maxOffset + maxOffset/4;
    }

    private void prepareResource() {
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screen_height = metrics.heightPixels;
        screen_width = metrics.widthPixels;
        mHeight = maxOffset + maxOffset/4;
        mWidth = screen_width;
        keyPoint = screen_width/2;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPath = new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(color);
        mPath.reset();
        mPath.moveTo(0, maxOffset);
        mPath.quadTo(keyPoint, offset, mWidth, maxOffset);
        mPath.lineTo(mWidth, mHeight);
        mPath.lineTo(0, mHeight);
        mPath.lineTo(0, maxOffset);
        canvas.drawPath(mPath, mPaint);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
        invalidate();
    }

}
