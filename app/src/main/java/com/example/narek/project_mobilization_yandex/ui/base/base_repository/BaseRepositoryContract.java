package com.example.narek.project_mobilization_yandex.ui.base.base_repository;


import com.example.narek.project_mobilization_yandex.ui.base.BaseContract;

public interface BaseRepositoryContract {

    interface IPresenter<V extends BaseContract.IView> extends BaseContract.IPresenter<V> {

        void onStart();

        void onStop();
    }

    interface IView extends BaseContract.IView {
        void showProgress();

        void hideProgress();

        void showError(String error);
    }
}
