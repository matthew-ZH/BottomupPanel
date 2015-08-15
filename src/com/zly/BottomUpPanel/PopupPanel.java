package com.zly.BottomUpPanel;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by mattlyzheng on 2015/8/15.
 */
public class PopupPanel extends LinearLayout {
    private int screen_height;
    private int screen_width;
    private int backgroundColor = Color.GREEN;

    private View content_layout;
    private PopupView popupView;
    LinearLayout.LayoutParams contentParams;
    private int maxOffset = dp2px(70);
    public PopupPanel(Context context) {
        this(context, null);
    }

    public PopupPanel(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PopupPanel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        prepareResource();
    }

    private void prepareResource(){
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screen_height = metrics.heightPixels;
        screen_width = metrics.widthPixels;
        popupView = new PopupView(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.TOP);
        setOrientation(VERTICAL);
        addView(popupView, lp);
        content_layout = new View(getContext());
        contentParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(content_layout, contentParams);
        popupView.setMaxOffset(maxOffset);
        popupView.setColor(backgroundColor);
        content_layout.setBackgroundColor(backgroundColor);

    }

    public void startAnimation(){
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(this, TRANSLATION_Y, screen_height, 0).setDuration(1000);
        translationAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        ObjectAnimator springAnimator = ObjectAnimator.ofInt(popupView, "offset", 0, maxOffset).setDuration(1000);
        springAnimator.setInterpolator(new OvershootInterpolator(4));
        AnimatorSet set = new AnimatorSet();
        set.play(translationAnimator).before(springAnimator);
        set.start();
    }



    private int dp2px(float dp) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public void setConentLayout(View view){
        if (content_layout != null) {
            removeView(content_layout);
        }
        content_layout = view;
        addView(content_layout, contentParams);
        content_layout.setBackgroundColor(backgroundColor);
    }

    public void setConentLayout(int resId) {
        View view = ((Activity)getContext()).getLayoutInflater().inflate(resId, null);
        if (content_layout != null) {
            removeView(content_layout);
        }
        content_layout = view;
        addView(content_layout, contentParams);
        content_layout.setBackgroundColor(backgroundColor);
    }

    public void getLocation(int[] location){
        if (location.length == 2 ) {
            location[0] = 0;
            location[1] = screen_height - getHeight() + maxOffset;
        }
    }
}
