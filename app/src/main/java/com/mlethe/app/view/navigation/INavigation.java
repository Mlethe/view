package com.mlethe.app.view.navigation;

import android.view.View;
import android.view.ViewGroup;

/**
 * 导航栏的规范
 * Created by Mlethe on 2018/2/1.
 */

public interface INavigation {
    void createNavigationBar();

    /**
     * 绑定参数
     */
    void attachNavigationParams();

    /**
     * 将NavigationView添加到父布局
     * @param navigationBar
     * @param parent
     */
    void attachParent(View navigationBar, ViewGroup parent);
}
