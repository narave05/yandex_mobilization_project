package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.history_list;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDto;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.base_history_favorite.HistoryAndFavoriteBaseContract;

import java.util.List;


interface HistoryListContract {

    interface IView extends HistoryAndFavoriteBaseContract.IView {

        void showHistoryList(List<TranslationDto> data);

        void insertedOrAddHistoryList(TranslationDto translationDto);

        void updateHistoryList(TranslationDto translationDto);

    }

    interface IPresenter extends HistoryAndFavoriteBaseContract.IPresenter<IView> {

    }
}
