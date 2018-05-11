package com.mlethe.app.view.navigation;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.FloatRange;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mlethe.app.view.R;

/**
 * 可以拿过来直接使用的 默认样式导航栏
 * Created by Mlethe on 2018/2/1.
 */

public class DefaultNavigationBar extends AbsNavigationBar<DefaultNavigationBar.Builder> {

    protected DefaultNavigationBar(Builder builder) {
        super(builder);
    }

    @Override
    public void attachNavigationParams() {
        super.attachNavigationParams();
        Builder builder = getBuilder();
        // 设置titleBar背景颜色
        RelativeLayout layout = findViewById(R.id.title_bar);
        layout.setBackgroundColor(builder.color);
        layout.setAlpha(builder.alpha);
        // 设置标题
        TextView back = findViewById(R.id.title_bar_back);
        back.setText(builder.title);
        back.setVisibility(builder.leftIconVisible);
        // 左边 要写一个默认的 finishActivity
        back.setOnClickListener(builder.leftClickListener);
        // 设置右边文字
        TextView rightText = findViewById(R.id.title_bar_right_text);
        if (builder.rightText != null) {
            rightText.setVisibility(View.VISIBLE);
            rightText.setText(builder.rightText);
            rightText.setOnClickListener(builder.rightTextClickListener);
        }
        // 设置右边的图片
        ImageView rightImg = findViewById(R.id.title_bar_right_icon);
        if (builder.rightRes != 0){
            rightImg.setVisibility(View.VISIBLE);
            rightImg.setImageDrawable(ContextCompat.getDrawable(builder.mContext, builder.rightRes));
            rightImg.setOnClickListener(builder.rightIconClickListener);
        }
    }

    /**
     * 导航栏的Builder
     */
    public static class Builder extends AbsNavigationBar.Builder<DefaultNavigationBar.Builder>{

        // 2.所有的效果放置
        public float alpha = 1;

        public String title;

        public String rightText;

        public int leftIconVisible = View.VISIBLE;

        @ColorInt
        public int color = ContextCompat.getColor(mContext, R.color.colorPrimary);

        // 右边图片
        @DrawableRes
        public int rightRes = 0;

        // 后面还有一些通用的
        public View.OnClickListener rightTextClickListener;
        public View.OnClickListener rightIconClickListener;

        public View.OnClickListener leftClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)mContext).finish();
            }
        };

        public Builder(Context context, ViewGroup parent) {
            super(context, R.layout.default_title_bar, parent);
        }

        public Builder(Context context) {
            super(context, R.layout.default_title_bar);
        }

        @Override
        public DefaultNavigationBar create() {
            return new DefaultNavigationBar(this);
        }

        /**
         * 设置title
         * @param text
         * @return
         */
        public Builder setTitle(String text){
            setText(R.id.title_bar_title, text);
            return this;
        }

        /**
         * 设置左边点击事件
         * @param clickListener
         * @return
         */
        public Builder setLeftClickListener(View.OnClickListener clickListener){
            setOnClickListener(R.id.title_bar_back, clickListener);
            return this;
        }

        /**
         * 设置右边文本
         * @param rightText
         * @return
         */
        public Builder setRightText(String rightText){
            this.rightText = rightText;
            return this;
        }

        /**
         * 设置右边点击事件
         * @param rightListener
         * @return
         */
        public Builder setRightTextClickListener(View.OnClickListener rightListener){
            this.rightTextClickListener = rightListener;
            return this;
        }

        /**
         * 设置右边点击事件
         * @param rightListener
         * @return
         */
        public Builder setRightIconClickListener(View.OnClickListener rightListener){
            this.rightIconClickListener = rightListener;
            return this;
        }

        /**
         * 设置右边的图片
         * @param rightRes
         * @return
         */
        public Builder setRightIcon(@DrawableRes int rightRes){
            this.rightRes = rightRes;
            return this;
        }

        /**
         * 设置透明
         * @param alpha
         */
        public Builder setAlpha(@FloatRange(from=0.0, to=1.0) float alpha){
            this.alpha = alpha;
            return this;
        }

        /**
         * 隐藏左边图片
         * @return
         */
        public Builder hideLeftIcon() {
            this.leftIconVisible = View.GONE;
            return this;
        }

    }
}
