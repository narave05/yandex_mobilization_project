package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.history_list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.HistoryChangeListener;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.base_history_favorite.HistoryAndFavoriteBaseFragment;

import java.util.ArrayList;
import java.util.List;

public class HistoryListFragment extends HistoryAndFavoriteBaseFragment<HistoryListContract.IView, HistoryListContract.IPresenter>
        implements HistoryListContract.IView {

    private HistoryChangeListener mHistoryChangeListener;

    public static HistoryListFragment newInstance() {
        HistoryListFragment fragment = new HistoryListFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof HistoryChangeListener) {
            mHistoryChangeListener = (HistoryChangeListener) getParentFragment();
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history_list, container, false);
    }

    @NonNull
    @Override
    public HistoryListContract.IPresenter createPresenter() {
        return new HistoryListPresenter();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mHistoryChangeListener = null;
    }

    @Override
    public void showHistoryList(List<TranslationDTO> data) {
        setupAdapter(data);
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


    @Override
    public void onListStateChange(boolean isEmpty, boolean isSearchResult) {
        super.onListStateChange(isEmpty, isSearchResult);

        if (!isSearchResult && (mHistoryChangeListener != null)) {
            mHistoryChangeListener.onHistoryChange(!isEmpty);
        }
    }
}
