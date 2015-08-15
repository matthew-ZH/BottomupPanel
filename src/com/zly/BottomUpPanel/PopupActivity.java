package com.zly.BottomUpPanel;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by mattlyzheng on 2015/8/15.
 */
public class PopupActivity extends Activity {
    private int[] location = new int[2];
    private PopupPanel panel;
    private ListView lv;
    private ArrayAdapter<String> arrays;
    private String[] strs = new String[] {
            "first", "second", " third", "fourth"
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        panel = new PopupPanel(this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.BOTTOM);
        setContentView(panel, lp);
        panel.setConentLayout(R.layout.popup_layout);
        arrays = new ArrayAdapter<String>(this, R.layout.item, strs);
        lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(arrays);
        panel.startAnimation();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        panel.getLocation(location);
        if (event.getY() < location[1]) {
            finish();
        }
        return super.onTouchEvent(event);
    }


}