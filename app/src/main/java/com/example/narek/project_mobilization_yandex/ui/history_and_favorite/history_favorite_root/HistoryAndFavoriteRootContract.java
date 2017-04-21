package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.history_favorite_root;

import com.example.narek.project_mobilization_yandex.ui.base.BaseContract;
import com.example.narek.project_mobilization_yandex.ui.base_repository.BaseRepositoryContract;


public interface HistoryAndFavoriteRootContract {

    interface IView extends BaseRepositoryContract.IView {
        void initViewpager();
        void deleteHistoryAndFavoriteLists();
        void hideKeyboard();
    }

    interface IPresenter extends BaseRepositoryContract.IPresenter<IView> {
        void init();
        void handleDeleteClick();
        void handlePageChanged();
    }
}
