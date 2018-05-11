package com.mlethe.app.view.day07;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.mlethe.app.view.R;

/**
 * Created by Mlethe on 2018/3/6.
 */

public class LetterSideBar extends View {

    // 定义26个字母
    private static String[] mLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};

    private Paint mDefaultPaint, mSelectedPaint;
    // 当前触摸的位置字母
    private String mCurrentTouchLetter;

    private int mNormalColor = Color.BLACK;
    private int mSelectedColor = Color.BLUE;
    private float mTextSize = 14.5f;

    public LetterSideBar(Context context) {
        this(context, null);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterSideBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LetterSideBar);
        mNormalColor = array.getColor(R.styleable.LetterSideBar_normalColor, mNormalColor);
        mSelectedColor = array.getColor(R.styleable.LetterSideBar_selectedColor, mSelectedColor);
        mTextSize = array.getDimension(R.styleable.LetterSideBar_normalTextSize, mTextSize);
        mDefaultPaint = getPaint(mNormalColor);
        mSelectedPaint = getPaint(mSelectedColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 计算指定宽度 = 左右的padding + 字母的宽度(取决于画笔)
        int textWidth = (int) mDefaultPaint.measureText("A");  // A字母的宽度
        int width = getPaddingLeft() + getPaddingRight() + textWidth;
        // 高度可以直接获取
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 画26个字母
        int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / mLetters.length;
        for (int i = 0; i < mLetters.length; i++) {
            // 知道每个字母的中心位置  1  字母的高度一半  2 字母高度一半+前面字符的高度
            int letterCenterY = i * itemHeight + itemHeight / 2 + getPaddingTop();
            // 基线，基于中心位置，知道中心位置还不会求基线，看一下之前的视频
            Paint.FontMetrics fontMetrics = mDefaultPaint.getFontMetrics();
            int dy = (int) ((fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom);
            int baseLine = letterCenterY + dy;
            int x = (int) ((getWidth() - mDefaultPaint.measureText(mLetters[i])) / 2);
            // 当前字母 高亮
            if (mCurrentTouchLetter != null && mCurrentTouchLetter.equals(mLetters[i])){
                canvas.drawText(mLetters[i], x, baseLine, mSelectedPaint);
            } else {
                canvas.drawText(mLetters[i], x, baseLine, mDefaultPaint);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                compute(event);
                break;
            case MotionEvent.ACTION_MOVE:
                // 计算出当前触摸字母  获取当前的位置
                compute(event);
                break;
            case MotionEvent.ACTION_UP:
                if (mListener != null){
                    mListener.touch(null, false);
                }
                mCurrentTouchLetter = null;
                invalidate();
                break;
        }
        return true;
    }

    /**
     * 获取画笔
     * @param color
     * @return
     */
    private Paint getPaint(int color){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(mTextSize);
        paint.setColor(color);
        return paint;
    }

    /**
     * 计算出当前触摸字母  获取当前的位置
     * @param event
     */
    private void compute(MotionEvent event){
        // 计算出当前触摸字母  获取当前的位置
        float currentMoveY = event.getY() - getPaddingTop();
        int itemHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / mLetters.length;
        // 位置 = currentMoveY / 字母高度 ， 通过位置获取字母  优化？
        int position = (int) (currentMoveY / itemHeight);
        if (position < 0){
            return;
        }
        if (position > mLetters.length - 1){
            return;
        }
        if (mCurrentTouchLetter == null || !mCurrentTouchLetter.equals(mLetters[position])){
            mCurrentTouchLetter = mLetters[position];
            if (mListener != null){
                mListener.touch(mCurrentTouchLetter, true);
            }
            // 重新绘制
            invalidate();
        }
    }

    private LetterTouchListener mListener;

    public void setOnLetterTouchListener(LetterTouchListener listener){
        this.mListener = listener;
    }

    public interface LetterTouchListener {
        void touch(String letter, boolean isTouch);
    }

}
