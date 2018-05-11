package com.mlethe.app.view.day03;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mlethe.app.view.R;
import com.mlethe.app.view.utils.DensityUtil;

/**
 * 仿QQ运动步数
 * Created by Mlethe on 2018/1/23.
 */

public class QQStepView extends View {

    private int mOuterColor = Color.BLUE;
    private int mInnerColor = Color.RED;
    private float mBorderWidth = 5; // px
    private int mStepTextSize = 15;
    private int mStepTextColor = Color.RED;
    private Paint mOuterPaint, mInnerPaint, mTextPaint;
    // 总共的步数
    private int mStepMax = 0;
    // 当前的步数
    private int mCurrentStep = 0;

    public QQStepView(Context context) {
        this(context, null);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        // 1、分析效果
        // 2、确定自定义属性，编写attrs.xml
        // 3、在布局中使用
        // 4、在自定义view中获取自定义属性
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
        mOuterPaint.setStrokeCap(Paint.Cap.ROUND);   // 圆弧
        mOuterPaint.setStyle(Paint.Style.STROKE);  // 画笔空心：FULL实心

        mInnerPaint = new Paint();
        // 设置抗锯齿
        mInnerPaint.setAntiAlias(true);
        mInnerPaint.setStrokeWidth(mBorderWidth);
        mInnerPaint.setColor(mInnerColor);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);   // 圆弧
        mInnerPaint.setStyle(Paint.Style.STROKE);  // 画笔空心：FULL实心

        mTextPaint = new Paint();
        // 设置抗锯齿
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mStepTextColor);
        mTextPaint.setTextSize(mStepTextSize);

    }

    /**
     * 5、onMeasure()
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 调用者在布局文件中可能是 wrap_content  宽度高度不一致
        // 获取模式 AT_MOST
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            throw new IllegalArgumentException("宽度和高度不能设为wrap_content");
        }
        // 宽度高度不一致  取最小值，确保是个正方形
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width > height ? height : width, width > height ? height : width);
    }

    /**
     * 6、画外圆弧，内圆弧，文字
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 6.1 画外圆弧  分析：边缘没显示完整-->描边有宽度 mBorderWidth
        // RectF rectF = new RectF(mBorderWidth / 2, mBorderWidth / 2, getWidth() - mBorderWidth / 2, getHeight() - mBorderWidth / 2);

        int center = getWidth() / 2;
        float radius = getHeight() / 2 - mBorderWidth / 2;
        RectF rectF = new RectF(center - radius, center - radius, center + radius, center + radius);
        /**
         * startAngle -    开始角度（以时钟3点的方向为0°，顺时针为正方向）
         * sweepAngle -    扫过角度（以时钟3点的方向为0°，顺时针为正方向）
         * useCenter -     是否包含圆心
         * paint -         绘制圆弧的画笔
         */
        canvas.drawArc(rectF, 135, 270, false, mOuterPaint);
        if (mStepMax == 0) return;
        // 6.2 画内圆弧  百分比  是使用者设置的 从外面传
        float sweepAngle =  (float) mCurrentStep / mStepMax;
        canvas.drawArc(rectF, 135, sweepAngle * 270, false, mInnerPaint);
        // 6.4 画文字
        String stepText = mCurrentStep + "";
        Rect textBounds = new Rect();
        mTextPaint.getTextBounds(stepText, 0, stepText.length(), textBounds);
        int dx = center - textBounds.width() / 2;
        // 基线  baseLine
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        int dy = (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom;
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(stepText, dx, baseLine, mTextPaint);
    }

    // 7、其他  写几个方法动起来
    public synchronized void setStepMax(int stepMax){
        this.mStepMax = stepMax;
    }

    public synchronized void setCurrentStep(int currentStep){
        this.mCurrentStep = currentStep;
        // 不断绘制
        invalidate();
    }

}
