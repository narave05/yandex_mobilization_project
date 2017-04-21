package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.history_list;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.base_history_favorite.HistoryAndFavoriteBaseContract;

import java.util.List;


interface HistoryListContract {

    interface IView extends HistoryAndFavoriteBaseContract.IView {

        void showHistoryList(List<TranslationDTO> data);

        void insertedOrAddHistoryList(TranslationDTO translationDTO);

        void updateHistoryList(TranslationDTO translationDTO);

    }

    interface IPresenter extends HistoryAndFavoriteBaseContract.IPresenter<IView> {

    }
}
