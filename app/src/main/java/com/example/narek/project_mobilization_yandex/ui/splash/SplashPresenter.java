package com.example.narek.project_mobilization_yandex.ui.splash;

import android.os.Handler;

import com.example.narek.project_mobilization_yandex.ui.base_repository.BaseRepositoryPresenter;


class SplashPresenter extends BaseRepositoryPresenter<SplashContract.IView>
        implements SplashContract.IPresenter {

    private static final int DELAY_MILLIS = 3000;

    @Override
    public void init() {
        getRepository().getAndSaveAvailableLanguageListAsync();
        openTargetActivityPostDelayed();
    }

    private void openTargetActivityPostDelayed() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isViewAttached()) {
                    getView().openTargetActivity();
                }
            }
        }, DELAY_MILLIS);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
