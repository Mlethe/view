package com.mlethe.app.view.navigation;

import android.content.Context;
import android.view.ViewGroup;

/**
 * 可以拿过来直接使用的导航栏
 * Created by Mlethe on 2018/2/1.
 */

public class NavigationBar extends AbsNavigationBar {

    protected NavigationBar(Builder builder) {
        super(builder);
    }

    /**
     * 导航栏的Builder
     */
    public static class Builder extends AbsNavigationBar.Builder<NavigationBar.Builder>{

        public Builder(Context context, int layoutId, ViewGroup parent) {
            super(context, layoutId, parent);
        }

        public Builder(Context context, int layoutId) {
            super(context, layoutId);
        }

        @Override
        public NavigationBar create() {
            return new NavigationBar(this);
        }

    }
}
