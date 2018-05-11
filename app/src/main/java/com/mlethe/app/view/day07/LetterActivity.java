package com.mlethe.app.view.day07;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mlethe.app.view.R;

public class LetterActivity extends AppCompatActivity {

    private TextView mLetterTv;
    private LetterSideBar mLetterSideBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter);
        mLetterTv = findViewById(R.id.letter_tv);
        mLetterSideBar = findViewById(R.id.letter_side_bar);
        mLetterSideBar.setOnLetterTouchListener(new LetterSideBar.LetterTouchListener() {
            @Override
            public void touch(String letter, boolean isTouch) {
                if (isTouch){
                    mLetterTv.setVisibility(View.VISIBLE);
                    mLetterTv.setText(letter);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mLetterTv.setVisibility(View.GONE);
                            mLetterTv.setText("");
                        }
                    }, 500);
                }
            }
        });
    }
}
