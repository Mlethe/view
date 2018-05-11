package com.mlethe.app.view.day04;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.FloatRange;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.mlethe.app.view.R;

/**
 * Created by Mlethe on 2018/1/30.
 */

public class ColorTrackTextView extends AppCompatTextView {

    // 绘制不变色字体的画笔
    private Paint mOriginPaint;
    // 绘制变色字体的画笔
    private Paint mChangePaint;
    // 当前的进度
    private float mProcess = 0.0f;
    // 2、实现不同朝向
    private Direction mDirection = Direction.LEFT_TO_RIGHT;
    public enum Direction {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT
    }

    public ColorTrackTextView(Context context) {
        this(context, null);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ColorTrackTextView);
        int mOriginColor = array.getColor(R.styleable.ColorTrackTextView_originColor, getTextColors().getDefaultColor());
        int mChangeColor = array.getColor(R.styleable.ColorTrackTextView_changeColor, getTextColors().getDefaultColor());
        array.recycle();
        mOriginPaint = getPaintByColor(mOriginColor);
        mChangePaint = getPaintByColor(mChangeColor);
    }

    /**
     * 根据颜色获取画笔
     *
     * @param color
     * @return
     */
    private Paint getPaintByColor(int color) {
        Paint paint = new Paint();
        // 设置抗锯齿
        paint.setAntiAlias(true);
        // 防抖动
        paint.setDither(true);
        // 设置颜色
        paint.setColor(color);
        // 设置字体的大小
        paint.setTextSize(getTextSize());
        return paint;
    }

    /**
     * 利用clipRect的API 可以裁剪   左边用一个画笔去画   右边用另一个画笔去画   不断改变中间值
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);

        //canvas.clipRect()  裁剪区域

        // 根据进度把中间值算出来
        int middle = (int) (mProcess * getWidth());

        // 从左变到右
        if (mDirection == Direction.LEFT_TO_RIGHT){ // 左边是红色右边是黑色
            // 绘制变色的
            drawText(canvas, mChangePaint, 0, middle);
            // 绘制不变色的
            drawText(canvas, mOriginPaint, middle, getWidth());
        } else {    // 右边是红色左边是黑色
            // 绘制变色的
            drawText(canvas, mChangePaint, getWidth() - middle, getWidth());
            // 绘制不变色的
            drawText(canvas, mOriginPaint, 0, getWidth() - middle);
        }
    }

    /**
     * 绘制text
     * @param canvas
     * @param paint
     * @param start
     * @param end
     */
    private void drawText(Canvas canvas, Paint paint, int start, int end){
        // 保存Canvas的状态
        canvas.save();
        // 绘制变色的
        Rect rect = new Rect(start, 0, end, getHeight());
        canvas.clipRect(rect);
        canvas.drawText(getText().toString(), getPaddingLeft(), getBaseline(), paint);
        // 恢复Canvas的状态
        canvas.restore();
    }

    public void setDirection(Direction direction){
        this.mDirection = direction;
    }

    public void setProcess(@FloatRange(from = 0, to = 1.0)float process){
        this.mProcess = process;
        invalidate();
    }

    public void setOriginColor(int originColor){
        this.mOriginPaint.setColor(originColor);
    }

    public void setChangeColor(int changeColor){
        this.mChangePaint.setColor(changeColor);
    }
}
