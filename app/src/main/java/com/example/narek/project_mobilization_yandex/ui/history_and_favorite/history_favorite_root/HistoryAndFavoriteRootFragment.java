package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.history_favorite_root;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.ui.base_repository.BaseRepositoryFragment;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.DeleteClickCallback;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.HistoryChangeListener;
import com.example.narek.project_mobilization_yandex.util.ViewHelper;

import butterknife.BindView;
import butterknife.OnClick;

public class HistoryAndFavoriteRootFragment extends BaseRepositoryFragment<HistoryAndFavoriteRootContract.IView, HistoryAndFavoriteRootContract.IPresenter>
        implements HistoryAndFavoriteRootContract.IView, HistoryChangeListener {

    @BindView(R.id.history_tab)
    TabLayout mTabLayout;

    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    @BindView(R.id.delete_icon)
    ImageView mDeleteIcon;

    private HistoryAndFavoritePagerAdapter mAdapter;

    public static HistoryAndFavoriteRootFragment newInstance() {
        return new HistoryAndFavoriteRootFragment();
    }

    @NonNull
    @Override
    public HistoryAndFavoriteRootContract.IPresenter createPresenter() {
        return new HistoryAndFavoriteRootPresenter();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.init();
    }

    @Override
    public void initViewpager() {
        setupViewPager(mViewpager);
        mTabLayout.setupWithViewPager(mViewpager);
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

    @Override
    protected boolean whitButterKnife() {
        return true;
    }

    @OnClick(R.id.delete_icon)
    public void onDeleteIconClicked(View view) {
        presenter.handleDeleteClick();
    }

    private void setupViewPager(ViewPager viewPager) {
        mAdapter = new HistoryAndFavoritePagerAdapter(getActivity(), getChildFragmentManager());
        viewPager.setAdapter(mAdapter);
        ViewPager.SimpleOnPageChangeListener onPageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                presenter.handlePageChanged();
            }
        };
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    @Override
    public void deleteHistoryAndFavoriteLists() {
        for (int i = 0; i < mAdapter.getCount(); i++) {
            if (mAdapter.getItem(i) instanceof DeleteClickCallback) {
                DeleteClickCallback deleteClickCallback = (DeleteClickCallback) mAdapter.getItem(i);
                deleteClickCallback.onDeleteClick();
            }
        }
    }

    @Override
    public void hideKeyboard() {
        ViewHelper.hideKeyboard(mViewpager);
    }

    @Override
    public void showDeleteIcon() {
        mDeleteIcon.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDeleteIcon() {
        mDeleteIcon.setVisibility(View.GONE);
    }

    @Override
    public void onHistoryChange(boolean shouldShowDeleteIcon) {
        presenter.handleHistoryChange(shouldShowDeleteIcon);
    }
}
