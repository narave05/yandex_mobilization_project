package com.example.narek.project_mobilization_yandex.ui.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;

import com.example.narek.project_mobilization_yandex.util.ViewHelper;
import com.hannesdorfmann.mosby3.mvp.MvpActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity<V extends BaseContract.IView, P extends BaseContract.IPresenter<V>>
        extends MvpActivity<V, P>
        implements BaseContract.IView {

    protected Unbinder unbinder;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        unbinder = ButterKnife.bind(this);
    }

    public void setContentView(@LayoutRes int layoutResID, boolean whitButterKnife) {
        super.setContentView(layoutResID);
        if (whitButterKnife)
            unbinder = ButterKnife.bind(this);
    }

    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate();
    }

    @CallSuper
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}
