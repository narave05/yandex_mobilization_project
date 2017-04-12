package com.example.narek.project_mobilization_yandex.ui.base.base_repository;

import android.support.annotation.CallSuper;

import com.example.narek.project_mobilization_yandex.data.repository.Repository;
import com.example.narek.project_mobilization_yandex.data.repository.IRepository;
import com.example.narek.project_mobilization_yandex.ui.base.BasePresenter;

public abstract class BaseRepositoryPresenter<V extends BaseRepositoryContract.IView> extends BasePresenter<V> {

    private IRepository mRepository;

    @CallSuper
    public void onCreate() {
        super.onCreate();
        mRepository = Repository.getInstance();
    }

    @CallSuper
    public void onDestroy() {
        super.onDestroy();
        mRepository = null;
    }

    public IRepository getRepository() {
        return mRepository;
    }
}
