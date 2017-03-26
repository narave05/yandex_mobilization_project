package com.example.narek.project_mobilization_yandex.ui.base;

import android.support.annotation.CallSuper;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;


public abstract class BasePresenter<V extends BaseContract.IView> extends MvpBasePresenter<V> {

    @CallSuper
    public void onCreate() {
    }

    @CallSuper
    public void onDestroy() {
    }
}
