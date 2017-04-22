package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.base_history_favorite;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.ui.base_repository.BaseRepositoryContract;

public interface HistoryAndFavoriteBaseContract {

    interface IPresenter<V extends BaseRepositoryContract.IView> extends BaseRepositoryContract.IPresenter<V> {

        void init();

        void handleDeleteClick();

        void handleListStateChange(boolean isEmpty, boolean isSearchResult);

        void handleSearchText(String newText);

        void handleFavoriteStatusChanged(TranslationDTO translationDTO);
    }

    interface IView extends BaseRepositoryContract.IView {

        void deleteHistoryAndFavoriteList();

        void filterAdapterList(String text);

        void showHintLayout();

        void showSearchView();

        void hideHintLayout();

        void hideSearchView();

    }

}
