package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.favorite_list;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.base_history_favorite.HistoryAndFavoriteBaseContract;

import java.util.List;


interface FavoriteListContract {

    interface IView extends HistoryAndFavoriteBaseContract.IView {

        void showFavoriteList(List<TranslationDTO> data);

        void addFavoriteItem(TranslationDTO translationDTO);

        void removeFavoriteItem(TranslationDTO translationDTO);
    }

    interface IPresenter extends HistoryAndFavoriteBaseContract.IPresenter<IView> {

    }
}
