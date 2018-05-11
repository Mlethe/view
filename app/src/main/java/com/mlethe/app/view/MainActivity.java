package com.mlethe.app.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mlethe.app.view.day02.TextViewActivity;
import com.mlethe.app.view.day03.QQStepActivity;
import com.mlethe.app.view.day04.FontActivity;
import com.mlethe.app.view.day06.RatingBarActivity;
import com.mlethe.app.view.day07.LetterActivity;
import com.mlethe.app.view.day09.FlowActivity;
import com.mlethe.app.view.day10.TouchActivity;
import com.mlethe.app.view.navigation.NavigationBar;
import com.mlethe.app.view.day05.ShapeActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewGroup parent = findViewById(R.id.view_root);
        NavigationBar navigationBar = new NavigationBar.Builder(this, R.layout.title_bar)
                .setText(R.id.title_bar_title,"主页")
                .setOnClickListener(R.id.title_bar_back, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                })
                .create();
        // 如果你想设置其他属性呢？比如文字大小，比如文字颜色，设置图片？等等
        TextView textView = (TextView) navigationBar.findViewById(R.id.title_bar_right_text);

        // 还有就是有时候我们并不需要去关注 id 以及头部的样式，大部分情况下是类似的，所以必须还要提供一个默认的
    }

    /**
     * 点击事件
     * @param view
     */
    public void click(View view){
        int id = view.getId();
        if (id == R.id.text_view) {
            startActivity(TextViewActivity.class);
        } else if (id == R.id.qq_step_view){
            startActivity(QQStepActivity.class);
        } else if (id == R.id.font_view){
            startActivity(FontActivity.class);
        } else if (id == R.id.shape_view){
            startActivity(ShapeActivity.class);
        } else if (id == R.id.rating_bar_view){
            startActivity(RatingBarActivity.class);
        } else if (id == R.id.letter_view){
            startActivity(LetterActivity.class);
        } else if (id == R.id.tag_view){
            startActivity(FlowActivity.class);
        } else if (id == R.id.touch_view){
            startActivity(TouchActivity.class);
        } else if (id == R.id.touch_view_group){
            startActivity(com.mlethe.app.view.day11.TouchActivity.class);
        }
    }

    private void startActivity(Class<?> clazz){
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }
}
