package com.example.narek.project_mobilization_yandex.ui.base.base_repository;

import com.example.narek.project_mobilization_yandex.ui.base.BaseActivity;

public abstract class BaseRepositoryActivity<V extends BaseRepositoryContract.IView, P extends BaseRepositoryContract.IPresenter<V>>
        extends BaseActivity<V, P>
        implements BaseRepositoryContract.IView {
}
