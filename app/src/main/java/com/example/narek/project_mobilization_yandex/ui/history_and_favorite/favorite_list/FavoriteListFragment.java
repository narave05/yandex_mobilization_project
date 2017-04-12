package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.favorite_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.ui.base.BaseFragment;
import com.example.narek.project_mobilization_yandex.ui.widget.LoadingView;

import java.util.List;

import butterknife.BindView;

public class FavoriteListFragment extends BaseFragment<FavoriteListContract.IView, FavoriteListContract.IPresenter>
        implements FavoriteListContract.IView, FavoriteListAdapter.OnItemClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.loading_view)
    LoadingView mLoadingView;
    private FavoriteListAdapter mAdapter;

    public static FavoriteListFragment newInstance() {
        FavoriteListFragment fragment = new FavoriteListFragment();
        return fragment;
    }

    @NonNull
    @Override
    public FavoriteListContract.IPresenter createPresenter() {
        return new FavoriteListPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.init();
    }

    @Override
    public void showProgress() {
        mLoadingView.show();
    }

    @Override
    public void hideProgress() {
        mLoadingView.hide();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected boolean whitButterKnife() {
        return true;
    }

    @Override
    public void onItemClickListener(String primaryKey, boolean isChecked) {
        presenter.handleFavoriteStatusChanged(primaryKey, isChecked);
    }

    @Override
    public void showFavoriteList(List<TranslationDTO> data) {
        if (mAdapter == null) {
            setupAdapter(data);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }


    private void setupAdapter(List<TranslationDTO> translationDTOList) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter = new FavoriteListAdapter(translationDTOList, this);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
    }


}
