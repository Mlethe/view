package com.mlethe.app.view.day05;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mlethe.app.view.R;

public class ShapeActivity extends AppCompatActivity {

    private ShapeView shapeView;

    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            shapeView.exchange();
            handler.postDelayed(runnable, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);
        shapeView = findViewById(R.id.shape_view_test);
    }

    public void start(View view){
        handler.postDelayed(runnable, 1000);
    }

    public void end(View view){
        handler.removeCallbacks(runnable);
    }
}
