package com.mlethe.app.architect.day37;

/**
 * 这个类可有可无，一个协议类，多人协作开发的时候u，就可以先把这个写好
 * Created by Mlethe on 2018/2/7.
 */

public interface UserInfoContract {

    // user View 层
    interface UserInfoView {
        // 1、正在加载中
        void onLoading();
        // 2、获取出错了
        void onError();
        // 3、成功了要显示数据
        void onSucceed(UserInfo userInfo);
    }

    // user presenter 层
    interface UserInfoPresenter {
        void getUsers(String token);
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,如是否使用缓存
    interface UserInfoModel {
        UserInfo getUsers(String token) throws Exception;
    }

}
