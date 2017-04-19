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
import com.example.narek.project_mobilization_yandex.ui.base_repository.BaseRepositoryFragment;
import com.example.narek.project_mobilization_yandex.ui.widget.LoadingView;
import com.example.narek.project_mobilization_yandex.ui.widget.SearchView;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;
import net.yslibrary.android.keyboardvisibilityevent.Unregistrar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnTextChanged;

public class HistoryListFragment extends BaseRepositoryFragment<HistoryListContract.IView, HistoryListContract.IPresenter>
        implements HistoryListContract.IView, HistoryListAdapter.OnItemClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.loading_view)
    LoadingView mLoadingView;

    @BindView(R.id.search_bar)
    SearchView mSearchView;

    private HistoryListAdapter mAdapter;
    private Unregistrar mUnregistrar;

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
        registerKeyboardVisibilityEvent();

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
    public void onItemClickListener(TranslationDTO translationDTO) {
        presenter.handleFavoriteStatusChanged(translationDTO);
    }

    @Override
    public void showHistoryList(List<TranslationDTO> data) {
        if (mAdapter == null) {
            setupAdapter(data);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void insertedOrAddHistoryList(TranslationDTO translationDTO) {
        if (mAdapter == null) {
            List<TranslationDTO> list = new ArrayList<>();
            list.add(translationDTO);
            setupAdapter(list);
        } else {
            mAdapter.insertedOrUpdatePositionItem(translationDTO);
        }
    }

    @Override
    public void updateHistoryList(TranslationDTO translationDTO) {
        mAdapter.updateItem(translationDTO);
    }

    @OnTextChanged(R.id.search_view)
    protected void onTextChanged(CharSequence text) {
        mAdapter.getFilter().filter(text);
    }

    private void setupAdapter(List<TranslationDTO> translationDTOList) {
        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter = new HistoryListAdapter(translationDTOList, this);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
    }

    private void registerKeyboardVisibilityEvent() {
        mUnregistrar = KeyboardVisibilityEvent.registerEventListener(getActivity(), new KeyboardVisibilityEventListener() {
            @Override
            public void onVisibilityChanged(boolean isOpen) {
                if (!isOpen) {
                    mSearchView.cancelViewFocus();
                }
            }
        });
    }

}
