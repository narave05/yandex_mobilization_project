package com.example.narek.project_mobilization_yandex.ui.splash;

import com.example.narek.project_mobilization_yandex.ui.base.BasePresenter;


class SplashPresenter extends BasePresenter<SplashContract.IView>
        implements SplashContract.IPresenter {

    @Override
    public void onCreate() {
        super.onCreate();
        initPresenter();
    }

    @Override
    public void initPresenter() {
        if (isViewAttached()) {
            getView().openTargetActivity();
        }
    }
}
