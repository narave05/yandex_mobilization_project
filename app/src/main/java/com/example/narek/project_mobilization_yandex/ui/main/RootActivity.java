package com.example.narek.project_mobilization_yandex.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.ui.base.BaseActivity;
import com.example.narek.project_mobilization_yandex.ui.widget.IconicTabLayout;

import butterknife.BindView;

public class RootActivity extends BaseActivity<RootContract.IView, RootContract.IPresenter>
        implements RootContract.IView {


    @BindView(R.id.viewpager)
    NonSwappableViewPager mViewpager;

    @BindView(R.id.bottom_tab)
    IconicTabLayout mBottomTab;


    public static void startThisActivity(Context context) {
        Intent starter = new Intent(context, RootActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        setupViewPager(mViewpager);
        mBottomTab.setupWithViewPager(mViewpager);
    }

    @NonNull
    @Override
    public RootContract.IPresenter createPresenter() {
        return new RootPresenter();
    }

    @Override
    public void showError(String error) {

    }

    private void setupViewPager(NonSwappableViewPager viewPager) {
        RootViewPagerAdapter adapter = new RootViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setPagingEnabled(false);
    }
}
