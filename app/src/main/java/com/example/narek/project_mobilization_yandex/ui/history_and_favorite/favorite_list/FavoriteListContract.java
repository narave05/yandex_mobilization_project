package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.favorite_list;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDto;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.base_history_favorite.HistoryAndFavoriteBaseContract;

import java.util.List;


interface FavoriteListContract {

    interface IView extends HistoryAndFavoriteBaseContract.IView {

        void showFavoriteList(List<TranslationDto> data);

        void addFavoriteItem(TranslationDto translationDto);

        void removeFavoriteItem(TranslationDto translationDto);
    }

    interface IPresenter extends HistoryAndFavoriteBaseContract.IPresenter<IView> {

    }
}
