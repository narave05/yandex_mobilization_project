package com.example.narek.project_mobilization_yandex.ui.main;

import com.example.narek.project_mobilization_yandex.ui.base.BaseContract;


interface RootContract {

    interface IView extends BaseContract.IView {
    }

    interface IPresenter extends BaseContract.IPresenter<IView> {

    }
}
