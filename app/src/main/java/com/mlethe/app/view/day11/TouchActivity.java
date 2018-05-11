package com.mlethe.app.view.day11;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.mlethe.app.view.R;
import com.mlethe.app.view.day10.TouchView;

public class TouchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch2);
        TouchView touchView = findViewById(R.id.touch_view);
        touchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "onClick");
            }
        });
        touchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("TAG", "onTouch -> " + event.getAction());
                return false;
            }
        });
    }
}
