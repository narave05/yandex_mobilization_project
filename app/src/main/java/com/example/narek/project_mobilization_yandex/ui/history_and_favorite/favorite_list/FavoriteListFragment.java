package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.favorite_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.base_history_favorite.HistoryAndFavoriteBaseFragment;

import java.util.ArrayList;
import java.util.List;

public class FavoriteListFragment extends HistoryAndFavoriteBaseFragment<FavoriteListContract.IView, FavoriteListContract.IPresenter>
        implements FavoriteListContract.IView {

    public static FavoriteListFragment newInstance() {
        FavoriteListFragment fragment = new FavoriteListFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite_list, container, false);
    }

    @NonNull
    @Override
    public FavoriteListContract.IPresenter createPresenter() {
        return new FavoriteListPresenter();
    }

    @Override
    public void showFavoriteList(List<TranslationDTO> data) {
        setupAdapter(data);
    }

    @Override
    public void addFavoriteItem(TranslationDTO translationDTO) {
        if (mAdapter == null) {
            List<TranslationDTO> list = new ArrayList<>();
            list.add(translationDTO);
            setupAdapter(list);
        } else {
            mAdapter.addFirst(translationDTO);
        }
    }

    @Override
    public void removeFavoriteItem(TranslationDTO translationDTO) {
        mAdapter.removeItem(translationDTO);
    }
}
