package com.mlethe.app.view.day03;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mlethe.app.view.R;
import com.mlethe.app.view.utils.DensityUtil;

/**
 * Created by Mlethe on 2018/1/31.
 */

public class LoadingView extends View {

    private int mOuterColor = Color.BLUE;
    private int mInnerColor = Color.RED;
    private float mBorderWidth = 5; // px
    private float mStepTextSize = 15;
    private int mStepTextColor = Color.RED;
    private Paint mOuterPaint, mInnerPaint, mTextPaint;

    private float mProcess = 0;

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.QQStepView);
        mOuterColor = array.getColor(R.styleable.QQStepView_outerColor, mOuterColor);
        mInnerColor = array.getColor(R.styleable.QQStepView_innerColor, mInnerColor);
        mBorderWidth = array.getDimension(R.styleable.QQStepView_borderWidth, DensityUtil.dip2px(context, mBorderWidth));
        mStepTextSize = array.getDimensionPixelSize(R.styleable.QQStepView_stepTextSize, DensityUtil.sp2px(context, mStepTextSize));
        mStepTextColor = array.getColor(R.styleable.QQStepView_stepTextColor, mStepTextColor);
        array.recycle();

        mOuterPaint = new Paint();
        // 设置抗锯齿
        mOuterPaint.setAntiAlias(true);
        mOuterPaint.setStrokeWidth(mBorderWidth);
        mOuterPaint.setColor(mOuterColor);
        //mOuterPaint.setStrokeCap(Paint.Cap.ROUND);   // 圆弧
        mOuterPaint.setStyle(Paint.Style.STROKE);  // 画笔空心：FULL实心

        mInnerPaint = new Paint();
        // 设置抗锯齿
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setStrokeWidth(mBorderWidth);
        mInnerPaint.setColor(mInnerColor);
        //mInnerPaint.setStrokeCap(Paint.Cap.ROUND);   // 圆弧
        mInnerPaint.setStyle(Paint.Style.STROKE);  // 画笔空心：FULL实心

        mTextPaint = new Paint();
        // 设置抗锯齿
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mStepTextColor);
        mTextPaint.setTextSize(mStepTextSize);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            throw new IllegalArgumentException("宽度不能设为wrap_content");
        }
        // 宽度高度不一致  取最小值，确保是个正方形
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width <= height ? width : height, width <= height ? width : height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int center = getWidth() / 2;
        float radius = getHeight() / 2 - mBorderWidth / 2;
        RectF rectF = new RectF(center - radius, center - radius, center + radius, center + radius);
        canvas.drawArc(rectF, 0, 360, false, mOuterPaint);
        if (mProcess < 0 || mProcess > 1) return;
        canvas.drawArc(rectF, 0, 360 * mProcess, false, mInnerPaint);
        String text = (int)(mProcess * 100) + "%";
        Rect bounds = new Rect();
        mTextPaint.getTextBounds(text, 0, text.length(), bounds);
        int dx = (getWidth() - bounds.width()) / 2;
        // 基线  baseLine
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        int dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(text, dx, baseLine, mTextPaint);
    }

    public synchronized void setProcess(@FloatRange(from = 0, to = 1) float process){
        this.mProcess = process;
        invalidate();
    }
}
