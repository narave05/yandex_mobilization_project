package com.example.narek.project_mobilization_yandex.ui.base.base_repository;

import com.example.narek.project_mobilization_yandex.ui.base.BaseFragment;

public abstract class BaseRepositoryFragment<V extends BaseRepositoryContract.IView,
        P extends BaseRepositoryContract.IPresenter<V>>
        extends BaseFragment<V, P>
        implements BaseRepositoryContract.IView {
    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }
}
