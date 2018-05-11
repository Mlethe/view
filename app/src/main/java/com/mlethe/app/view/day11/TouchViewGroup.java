package com.mlethe.app.view.day11;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class TouchViewGroup extends LinearLayout {
    public TouchViewGroup(Context context) {
        this(context, null);
    }

    public TouchViewGroup(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // 事件分发
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("TAG", "ViewGroup dispatchTouchEvent -> " + ev.getAction());
        return super.dispatchTouchEvent(ev);
    }

    // 事件拦截
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("TAG", "ViewGroup onInterceptTouchEvent -> " + ev.getAction());
        return super.onInterceptTouchEvent(ev);
    }

    // 事件触摸
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("TAG", "ViewGroup onTouchEvent -> " + event.getAction());
        return super.onTouchEvent(event);
    }
}
