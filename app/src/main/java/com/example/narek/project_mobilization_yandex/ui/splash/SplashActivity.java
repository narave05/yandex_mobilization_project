package com.example.narek.project_mobilization_yandex.ui.splash;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.ui.base_repository.BaseRepositoryActivity;
import com.example.narek.project_mobilization_yandex.ui.main.RootActivity;


public class SplashActivity extends BaseRepositoryActivity<SplashContract.IView, SplashContract.IPresenter>
        implements SplashContract.IView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        presenter.init();
    }

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
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String error) {

    }
}
