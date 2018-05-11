package com.mlethe.app.architect.day37;

/**
 * Created by Mlethe on 2018/2/7.
 */

public class UserInfoPresenter implements UserInfoContract.UserInfoPresenter {

    // 肯定会会持有 M 和 V
    private UserInfoContract.UserInfoView mView;
    private UserInfoContract.UserInfoModel mModel;

    public UserInfoPresenter() {
        this.mModel = new UserInfoModel();
    }

    public void attach(UserInfoContract.UserInfoView view){
        this.mView = view;
    }

    /**
     * 解绑 view 加了这个之后越来越复杂，代码写起来越来越多？怎么办
     */
    public void detach(){
        this.mView = null;
    }

    @Override
    public void getUsers(String token) {
        // RxJava + OkHttp + Retrofit
        // 网络引擎 或 OkHttp
    }
}
