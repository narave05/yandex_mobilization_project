package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.favorite_list;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.ui.base.base_repository.BaseRepositoryContract;

import java.util.List;


interface FavoriteListContract {

    interface IView extends BaseRepositoryContract.IView {
        void showFavoriteList(List<TranslationDTO> data);
    }

    interface IPresenter extends BaseRepositoryContract.IPresenter<IView> {
        void init();

        void handleFavoriteStatusChanged(String primaryKey, boolean isChecked);
    }
}
