package com.mlethe.app.view.day01;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mlethe.app.view.R;

/**
 * Created by Mlethe on 2018/1/18.
 */

public class TextView extends View {

    private String mText;
    private int mTextSize = 15;
    private int mTextColor = Color.BLACK;

    // 构造函数会在代码里面new的时候调用
    public TextView(Context context) {
        this(context, null);
    }
    // 在布局layout中使用（调用）
    public TextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    // 在布局layout中使用（调用），自定义样式-->style
    public TextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 获取自定义属性
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.TextView);

        mText = array.getString(R.styleable.TextView_mletheText);
        mTextColor = array.getColor(R.styleable.TextView_mletheTextColor, mTextColor);
        // 15  15px  15sp
        mTextSize = array.getDimensionPixelSize(R.styleable.TextView_mletheTextSize, mTextSize);

        // 回收
        array.recycle();
    }

    /**
     * 自定义view的测量方法
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 布局的宽高都是由这个方法指定
        // 指定控件的宽高，需要测量
        // 获取宽高的模式
        int widthMode =  MeasureSpec.getMode(widthMeasureSpec);
        int heightMode =  MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY){  // MeasureSpec.AT_MOST：在布局中指定了wrap_content；MeasureSpec.EXACTLY：在布局中指定了确切的值，如100dp，match_parent，fill_parent；MeasureSpec.UNSPECIFIED：尽可能的大，很少能用到，如ListView，ScrollView

        }
        // ScrollView + ListView嵌套会显示不全的原因：在测量子布局的时候会用UNSPECIFIED
        // 解决显示不全的问题，创建高度和模式
        // heightMeasureSpec 32位的值  30位是Integer.MAX_VALUE  2位是  MeasureSpec.AT_MOST
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
    }

    /**
     * 用于绘制
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画文本
        //canvas.drawText();
        // 画狐
        //canvas.drawArc();
        // 画圆
        //canvas.drawCircle();
    }

    /**
     * 用于处理事件（处理跟用户交互的）
     * @param event 事件分发和事件拦截
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                // 手指按下
                break;
            case MotionEvent.ACTION_MOVE:
                // 手指移动
                break;
            case MotionEvent.ACTION_UP:
                // 手指抬起
                break;
        }
        return super.onTouchEvent(event);
    }
}
