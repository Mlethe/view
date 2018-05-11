package com.mlethe.app.view.navigation;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * 导航栏的基类
 * Created by Mlethe on 2018/2/1.
 */

public class AbsNavigationBar<B extends AbsNavigationBar.Builder> implements INavigation {

    private B mBuilder;

    private View mNavigationBar;

    protected AbsNavigationBar(B builder){
        this.mBuilder = builder;
        createNavigationBar();
    }

    @Override
    public void createNavigationBar() {
        mNavigationBar = LayoutInflater.from(mBuilder.mContext).inflate(mBuilder.mLayoutId, mBuilder.mParent, false);

        // 添加
        attachParent(mNavigationBar, mBuilder.mParent);
        // 绑定参数
        attachNavigationParams();
    }

    /**
     * 绑定参数
     */
    @Override
    public void attachNavigationParams() {
        // 设置文本
        Map<Integer, CharSequence> textMaps = mBuilder.mTextMaps;
        for (Map.Entry<Integer, CharSequence> entry: textMaps.entrySet()){
            TextView textView = findViewById(entry.getKey());
            textView.setText(entry.getValue());
        }
        // 设置icon
        Map<Integer, Integer> iconMaps = mBuilder.mIconMaps;
        for (Map.Entry<Integer, Integer> entry: iconMaps.entrySet()){
            ImageView imageView = findViewById(entry.getKey());
            imageView.setImageDrawable(ContextCompat.getDrawable(mBuilder.mContext, entry.getValue()));
        }
        // 设置点击事件
        Map<Integer, View.OnClickListener> mClickListenerMap = mBuilder.mClickListenerMap;
        for (Map.Entry<Integer, View.OnClickListener> entry: mClickListenerMap.entrySet()){
            View view = findViewById(entry.getKey());
            view.setOnClickListener(entry.getValue());
        }
    }

    public  <T extends View> T findViewById(@IdRes int viewId) {
        return (T) mNavigationBar.findViewById(viewId);
    }

    /**
     * 将NavigationView添加到父布局
     * @param navigationBar
     * @param parent
     */
    @Override
    public void attachParent(View navigationBar, ViewGroup parent) {
        parent.addView(navigationBar, 0);
    }

    /**
     * 返回 Builder
     * @return
     */
    public B getBuilder(){
        return mBuilder;
    }

    /**
     * Builder 构建类
     * 构建NavigationBar 还有存储参数
     */
    public abstract static class Builder<B extends Builder>{
        public Context mContext;
        public int mLayoutId;
        public ViewGroup mParent;
        // 文本存储
        public Map<Integer, CharSequence> mTextMaps;
        // 图片存储
        public Map<Integer, Integer> mIconMaps;
        // 监听存储
        public Map<Integer, View.OnClickListener> mClickListenerMap;

        public Builder(Context context, int layoutId){
            this.mContext = context;
            this.mLayoutId = layoutId;
            // 获取activity的根布局，View源码
            ViewGroup activityRoot = (ViewGroup) ((Activity) (mContext)).getWindow().getDecorView();
            this.mParent = (ViewGroup) activityRoot.getChildAt(0);
            mTextMaps = new HashMap<>();
            mIconMaps = new HashMap<>();
            mClickListenerMap = new HashMap<>();
        }

        public Builder(Context context, int layoutId, ViewGroup parent){
            this.mContext = context;
            this.mLayoutId = layoutId;
            this.mParent = parent;
            mTextMaps = new HashMap<>();
            mIconMaps = new HashMap<>();
            mClickListenerMap = new HashMap<>();
        }

        /**
         * 用来创建NavigationBar
         * @return
         */
        public abstract AbsNavigationBar create();

        /**
         * 设置文本
         * @param viewId
         * @param text
         * @return
         */
        public B setText(@IdRes int viewId, String text){
            mTextMaps.put(viewId, text);
            return (B) this;
        }

        /**
         * 设置icon
         * @param viewId
         * @param drawableId
         * @return
         */
        public B setIcon(@IdRes int viewId, @DrawableRes int drawableId){
            mIconMaps.put(viewId, drawableId);
            return (B) this;
        }

        /**
         * 设置绑定事件
         * @param viewId
         * @param clickListener
         * @return
         */
        public B setOnClickListener(@IdRes int viewId, View.OnClickListener clickListener){
            mClickListenerMap.put(viewId, clickListener);
            return (B) this;
        }
    }
}
