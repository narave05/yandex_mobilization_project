package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.history_list;

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
import com.example.narek.project_mobilization_yandex.ui.base.base_repository.BaseRepositoryFragment;
import com.example.narek.project_mobilization_yandex.ui.widget.LoadingView;

import java.util.List;

import butterknife.BindView;

public class HistoryListFragment extends BaseRepositoryFragment<HistoryListContract.IView, HistoryListContract.IPresenter>
        implements HistoryListContract.IView, HistoryListAdapter.OnItemClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.loading_view)
    LoadingView mLoadingView;
    private HistoryListAdapter mAdapter;

    public static HistoryListFragment newInstance() {
        HistoryListFragment fragment = new HistoryListFragment();
        return fragment;
    }

    @NonNull
    @Override
    public HistoryListContract.IPresenter createPresenter() {
        return new HistoryListPresenter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.init();

    }

    @Override
    protected boolean whitButterKnife() {
        return true;
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
    public void onItemClickListener(String primaryKey, boolean isChecked) {
        presenter.handleFavoriteStatusChanged(primaryKey,isChecked);
    }

    @Override
    public void showHistoryList(List<TranslationDTO> data) {
        if (mAdapter == null) {
            setupAdapter(data);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    private void setupAdapter(List<TranslationDTO> translationDTOList) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter = new HistoryListAdapter(translationDTOList, this);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
    }


}
