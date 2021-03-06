package com.example.narek.project_mobilization_yandex.ui.splash;

import com.example.narek.project_mobilization_yandex.ui.base.BaseContract;
import com.example.narek.project_mobilization_yandex.ui.base_repository.BaseRepositoryContract;


interface SplashContract {

    interface IView extends BaseRepositoryContract.IView {

        void openTargetActivity();
    }

    interface IPresenter extends BaseRepositoryContract.IPresenter<IView> {

        void init();
    }
}
