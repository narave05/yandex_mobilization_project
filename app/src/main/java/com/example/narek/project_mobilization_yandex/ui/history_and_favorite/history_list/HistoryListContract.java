package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.history_list;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.ui.base.base_repository.BaseRepositoryContract;

import java.util.List;


interface HistoryListContract {

    interface IView extends BaseRepositoryContract.IView {
        void showHistoryList(List<TranslationDTO> data);
    }

    interface IPresenter extends BaseRepositoryContract.IPresenter<HistoryListContract.IView> {
        void init();

        void handleFavoriteStatusChanged(String primaryKey, boolean isChecked);
    }
}
