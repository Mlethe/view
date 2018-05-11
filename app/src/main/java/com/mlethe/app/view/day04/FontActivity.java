package com.mlethe.app.view.day04;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.mlethe.app.view.R;

public class FontActivity extends AppCompatActivity {

    private ColorTrackTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font);
        textView = findViewById(R.id.font_text_test);
    }

    /**
     * 从左到右
     * @param view
     */
    public void leftToRight(View view){
        textView.setDirection(ColorTrackTextView.Direction.LEFT_TO_RIGHT);
        // 属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(2000);
        // 设置差值器
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float currentStep = (float) valueAnimator.getAnimatedValue();
                textView.setProcess(currentStep);
            }
        });
        valueAnimator.start();
    }

    /**
     * 从右到左
     * @param view
     */
    public void rightToLeft(View view){
        textView.setDirection(ColorTrackTextView.Direction.RIGHT_TO_LEFT);
        // 属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(2000);
        // 设置差值器
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float currentStep = (float) valueAnimator.getAnimatedValue();
                textView.setProcess(currentStep);
            }
        });
        valueAnimator.start();
    }

    public void viewPager(View view){
        Intent intent = new Intent(this, ViewPagerActivity.class);
        startActivity(intent);
    }
}
