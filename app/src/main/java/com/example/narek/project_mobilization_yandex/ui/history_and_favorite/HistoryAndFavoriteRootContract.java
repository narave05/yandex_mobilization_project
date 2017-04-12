package com.example.narek.project_mobilization_yandex.ui.history_and_favorite;

import com.example.narek.project_mobilization_yandex.ui.base.BaseContract;



interface HistoryAndFavoriteRootContract {

    interface IView extends BaseContract.IView {
    }

    interface IPresenter extends BaseContract.IPresenter<IView> {
        void init();
    }
}
