package com.zly.BottomUpPanel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SampleActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void onClick(View v) {
        Intent intent = new Intent(this, PopupActivity.class);
        startActivity(intent);
    }
}
