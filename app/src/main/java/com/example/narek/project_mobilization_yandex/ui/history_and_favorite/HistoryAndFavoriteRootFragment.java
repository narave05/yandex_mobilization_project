package com.example.narek.project_mobilization_yandex.ui.history_and_favorite;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.ui.base.BaseFragment;
import com.example.narek.project_mobilization_yandex.util.ViewHelper;

import butterknife.BindView;

public class HistoryAndFavoriteRootFragment extends BaseFragment<HistoryAndFavoriteRootContract.IView, HistoryAndFavoriteRootContract.IPresenter>
        implements HistoryAndFavoriteRootContract.IView {

    @BindView(R.id.history_tab)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    public static HistoryAndFavoriteRootFragment newInstance() {
        HistoryAndFavoriteRootFragment fragment = new HistoryAndFavoriteRootFragment();
        return fragment;
    }

    @NonNull
    @Override
    public HistoryAndFavoriteRootContract.IPresenter createPresenter() {
        return new HistoryAndFavoriteRootPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewPager(mViewpager);
        mTabLayout.setupWithViewPager(mViewpager);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected boolean whitButterKnife() {
        return true;
    }

    private void setupViewPager(ViewPager viewPager) {
        HistoryAndFavoritePagerAdapter adapter = new HistoryAndFavoritePagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        // TODO: 19.04.2017
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ViewHelper.hideKeyboard(mViewpager);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
