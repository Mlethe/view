package com.mlethe.app.view.day05;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.mlethe.app.view.R;
import com.mlethe.app.view.utils.DensityUtil;

/**
 * Created by Mlethe on 2018/1/31.
 */

public class ShapeView extends View {

    public static final int CIRCLE = 100;   //shape circle
    public static final int SQUARE = 101;   //shape square
    public static final int TRIANGLE = 102; //shape triangle

    // 圆的颜色
    private int mCircleColor = Color.BLACK;
    // 圆的半径
    private float mCircleRadius = 10;
    // 正方形的颜色
    private int mSquareColor = Color.BLACK;
    // 正方形的边长
    private float mSquareLength = 20;
    // 三角形的颜色
    private int mTriangleColor = Color.BLACK;
    // 三角形的边长
    private float mTriangleLength = 20;

    // 默认形状
    private int mType = CIRCLE;

    // 路线
    private Path mPath;

    private Paint mPaint;

    public ShapeView(Context context) {
        this(context, null);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ShapeView);
        mCircleColor = array.getColor(R.styleable.ShapeView_circleColor, mCircleColor);
        mCircleRadius = array.getDimension(R.styleable.ShapeView_circleRadius, DensityUtil.dip2px(context, mCircleRadius));
        mSquareColor = array.getColor(R.styleable.ShapeView_squareColor, mSquareColor);
        mSquareLength = array.getDimension(R.styleable.ShapeView_squareLength, DensityUtil.dip2px(context, mSquareLength));
        mTriangleColor = array.getColor(R.styleable.ShapeView_triangleColor, mTriangleColor);
        mTriangleLength = array.getDimension(R.styleable.ShapeView_triangleLength, DensityUtil.dip2px(context, mTriangleLength));
        mType = array.getInt(R.styleable.ShapeView_viewType, mType);
        array.recycle();
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mCircleColor);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {
            if (mType == CIRCLE) {
                width = (int) (mCircleRadius * 2);
                height = (int) (mCircleRadius * 2);
            } else if (mType == SQUARE) {
                width = (int) mSquareLength;
                height = (int) mSquareLength;
            } else if (mType == TRIANGLE) {
                width = (int) mTriangleLength;
                height = (int) mTriangleLength;
            }
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mType == CIRCLE) {
            /**
             * cx 圆心x坐标
             * cy 圆心y坐标
             * radius 半径
             */
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mCircleRadius, mPaint);
        } else if (mType == SQUARE) {
            /**
             * left 起点x坐标
             * top 起点y坐标
             * right 终点x坐标
             * bottom 终点y坐标
             */
            canvas.drawRect((getWidth() - mSquareLength) / 2, (getHeight() - mSquareLength) / 2, (getWidth() + mSquareLength) / 2, (getHeight() + mSquareLength) / 2, mPaint);
        } else if (mType == TRIANGLE) {
            if (mPath == null) {
                //实例化路径
                mPath = new Path();
                int height = (int) (mTriangleLength * Math.cos(Math.PI * 30 / 180));
                mPath.moveTo(getWidth() / 2, (getHeight() - height) / 2);// 此点为多边形的起点
                mPath.lineTo((getWidth() + mTriangleLength) / 2, (getHeight() + height) / 2);
                mPath.lineTo((getWidth() - mTriangleLength) / 2, (getHeight() + height) / 2);
                mPath.close(); // 使这些点构成封闭的多边形
            }
            canvas.drawPath(mPath, mPaint);
        }
    }

    /**
     * 切换形状
     */
    public void exchange() {
        if (mType == ShapeView.CIRCLE) {
            mType = ShapeView.SQUARE;
        } else if (mType == ShapeView.SQUARE) {
            mType = ShapeView.TRIANGLE;
        } else if (mType == ShapeView.TRIANGLE) {
            mType = ShapeView.CIRCLE;
        }
        invalidate();
    }

}
