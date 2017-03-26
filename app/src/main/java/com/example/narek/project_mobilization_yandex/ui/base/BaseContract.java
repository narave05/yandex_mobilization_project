package com.example.narek.project_mobilization_yandex.ui.base;


import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public interface BaseContract {

    interface IPresenter<V extends IView> extends MvpPresenter<V> {

        void onCreate();

        void onDestroy();
    }

    interface IView extends MvpView {

    }
}
