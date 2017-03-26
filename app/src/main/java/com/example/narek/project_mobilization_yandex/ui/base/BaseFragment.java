package com.example.narek.project_mobilization_yandex.ui.base;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<V extends BaseContract.IView, P extends BaseContract.IPresenter<V>>
        extends MvpFragment<V, P>
        implements BaseContract.IView {

    private Unbinder unbinder;

    @CallSuper
    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (whitButterKnife())
            unbinder = ButterKnife.bind(this, view);
        presenter.onCreate();
    }

    @CallSuper
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    protected abstract boolean whitButterKnife();
}
