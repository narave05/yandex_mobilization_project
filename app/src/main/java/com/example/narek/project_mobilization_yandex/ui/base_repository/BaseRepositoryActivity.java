package com.example.narek.project_mobilization_yandex.ui.base_repository;

import com.example.narek.project_mobilization_yandex.ui.base.BaseActivity;

public abstract class BaseRepositoryActivity<V extends BaseRepositoryContract.IView, P extends BaseRepositoryContract.IPresenter<V>>
        extends BaseActivity<V, P>
        implements BaseRepositoryContract.IView {

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }
}
