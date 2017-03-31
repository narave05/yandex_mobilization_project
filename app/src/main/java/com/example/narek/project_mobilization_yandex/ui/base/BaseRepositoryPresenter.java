package com.example.narek.project_mobilization_yandex.ui.base;

import android.support.annotation.CallSuper;

import com.example.narek.project_mobilization_yandex.data.repository.DataRepository;
import com.example.narek.project_mobilization_yandex.data.repository.IDataRepository;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;


public abstract class BaseRepositoryPresenter<V extends BaseContract.IView> extends BasePresenter<V> {

    private IDataRepository mDataRepository;

    @CallSuper
    public void onCreate() {
        super.onCreate();
        mDataRepository = DataRepository.getInstance();
    }

    @CallSuper
    public void onDestroy() {
        super.onDestroy();
    }

    public IDataRepository getDataRepository() {
        return mDataRepository;
    }
}
