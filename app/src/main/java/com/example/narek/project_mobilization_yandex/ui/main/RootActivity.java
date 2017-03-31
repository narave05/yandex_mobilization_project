package com.example.narek.project_mobilization_yandex.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.ui.base.BaseActivity;
import com.example.narek.project_mobilization_yandex.ui.translate.TranslationFragment;
import com.example.narek.project_mobilization_yandex.ui.widget.IconicTabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RootActivity extends BaseActivity<RootContract.IView, RootContract.IPresenter>
        implements RootContract.IView {


    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    @BindView(R.id.bottom_tab)
    IconicTabLayout mBottomTab;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    public static void startThisActivity(Context context) {
        Intent starter = new Intent(context, RootActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root);
        setSupportActionBar(mToolbar);
        setupViewPager(mViewpager);
        mBottomTab.setupWithViewPager(mViewpager);
        mViewpager.setOffscreenPageLimit(3);
    }

    @NonNull
    @Override
    public RootContract.IPresenter createPresenter() {
        return new RootPresenter();
    }

    private void setupViewPager(ViewPager viewPager) {
        RootViewPagerAdapter adapter = new RootViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

}
