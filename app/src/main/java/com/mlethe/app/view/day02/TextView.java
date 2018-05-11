package com.mlethe.app.view.day02;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.mlethe.app.view.R;

/**
 * Created by Mlethe on 2018/1/18.
 */

public class TextView extends View {

    private String mText;
    private int mTextSize = 15; // px
    private int mTextColor = Color.BLACK;

    private Paint mPaint;

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
        mTextSize = array.getDimensionPixelSize(R.styleable.TextView_mletheTextSize, sp2px(mTextSize));

        // 回收
        array.recycle();

        mPaint = new Paint();
        // 抗锯齿
        mPaint.setAntiAlias(true);
        // 设置画笔的大小和颜色
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mTextColor);
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
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
        // 1、确定的值，这个时候不需要计算，给的多少就是多少
        int width = MeasureSpec.getSize(widthMeasureSpec);
        // 2、给的是wrap_content
        if (widthMode == MeasureSpec.AT_MOST){  // MeasureSpec.AT_MOST：在布局中指定了wrap_content；MeasureSpec.EXACTLY：在布局中指定了确切的值，如100dp，match_parent，fill_parent；MeasureSpec.UNSPECIFIED：尽可能的大，很少能用到，如ListView，ScrollView
            // 计算的宽度  与  字体的长度和大小有关  用画笔测量
            Rect bounds = new Rect();
            // 获取文本的Rect
            mPaint.getTextBounds(mText, 0, mText.length(), bounds);
            width = bounds.width() + getPaddingLeft() + getPaddingRight();
        }
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (heightMode == MeasureSpec.AT_MOST){
            // 计算的宽度  与  字体的长度和大小有关  用画笔测量
            Rect bounds = new Rect();
            // 获取文本的Rect
            mPaint.getTextBounds(mText, 0, mText.length(), bounds);
            height = bounds.height() + getPaddingTop() + getPaddingBottom();
        }
        // 设置控件的宽高
        setMeasuredDimension(width, height);
    }

    /**
     * 用于绘制
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 画文本
        // 计算的宽度  与  字体的长度和大小有关  用画笔测量
        Rect bounds = new Rect();
        // 获取文本的Rect
        mPaint.getTextBounds(mText, 0, mText.length(), bounds);
        // x 就是开始的位置，y 基线 baseLine

        // dy 代表的是 高度的一半到 baseLine 的距离
        Paint.FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
        // top(给定字体大小的字体中最高字形的基线以上的最大距离) 是一个负值  bottom（给定字体大小下字体中最低字形的基线下方的最大距离） 是一个正值  descent 是一个正值  ascent 是一个负值
//        int dy = (fontMetrics.descent - fontMetrics.ascent) / 2 - fontMetrics.descent;
        int dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        int baseLine = bounds.height() / 2 + dy;
        canvas.drawText(mText, 0 - bounds.left + getPaddingLeft(), baseLine + getPaddingTop(), mPaint);
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
