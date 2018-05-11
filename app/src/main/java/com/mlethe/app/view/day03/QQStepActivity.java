package com.mlethe.app.view.day03;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.mlethe.app.view.R;

public class QQStepActivity extends AppCompatActivity {

    private LoadingView loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqstep);
        final QQStepView qqStepView = findViewById(R.id.step_view);
        loadingView = findViewById(R.id.loading_view);
        qqStepView.setStepMax(4000);

        // 属性动画  3000步
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 3000);
        valueAnimator.setDuration(1000);
        // 设置差值器
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float currentStep = (float) valueAnimator.getAnimatedValue();
                qqStepView.setCurrentStep((int)currentStep);
            }
        });
        valueAnimator.start();
    }

    public void start(View view){
        // 属性动画  3000步
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(5000);
        // 设置差值器
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float currentStep = (float) valueAnimator.getAnimatedValue();
                loadingView.setProcess(currentStep);
            }
        });
        valueAnimator.start();
    }
}
