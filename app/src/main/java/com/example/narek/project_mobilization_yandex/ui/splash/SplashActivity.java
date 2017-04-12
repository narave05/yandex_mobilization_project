package com.example.narek.project_mobilization_yandex.ui.splash;

import android.support.annotation.NonNull;

import com.example.narek.project_mobilization_yandex.ui.base.BaseActivity;
import com.example.narek.project_mobilization_yandex.ui.main.RootActivity;


public class SplashActivity extends BaseActivity<SplashContract.IView, SplashContract.IPresenter>
        implements SplashContract.IView {

    @NonNull
    @Override
    public SplashContract.IPresenter createPresenter() {
        return new SplashPresenter();
    }

    @Override
    public void openTargetActivity() {
        RootActivity.startThisActivity(this);
        finish();
    }

    @Override
    public void showError(String error) {

    }
}
