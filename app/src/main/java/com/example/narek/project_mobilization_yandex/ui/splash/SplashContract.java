package com.example.narek.project_mobilization_yandex.ui.splash;

import com.example.narek.project_mobilization_yandex.ui.base.BaseContract;


interface SplashContract {

    interface IView extends BaseContract.IView {

        void openTargetActivity();
    }

    interface IPresenter extends BaseContract.IPresenter<IView> {

        void initPresenter();
    }
}
