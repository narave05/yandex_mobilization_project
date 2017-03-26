package com.example.narek.project_mobilization_yandex.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.ui.base.BaseActivity;
import com.example.narek.project_mobilization_yandex.util.ViewHelper;

public class RootActivity extends BaseActivity<RootContract.IView, RootContract.IPresenter>
        implements RootContract.IView {

    public static void startThisActivity(Context context) {
        Intent starter = new Intent(context, RootActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @NonNull
    @Override
    public RootContract.IPresenter createPresenter() {
        return new RootPresenter();
    }

    @Override
    public void showMessage(String message) {
        ViewHelper.showToast(this, message);
    }
}
