package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.history_favorite_root;

import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.DeleteAllTranslationsEvent;
import com.example.narek.project_mobilization_yandex.ui.base_repository.BaseRepositoryPresenter;

import org.greenrobot.eventbus.EventBus;

class HistoryAndFavoriteRootPresenter extends BaseRepositoryPresenter<HistoryAndFavoriteRootContract.IView>
        implements HistoryAndFavoriteRootContract.IPresenter {
    @Override
    public void init() {
        if (isViewAttached()) {
            getView().initViewpager();
        }
        findHistory();
    }

    @Override
    public void handleDeleteClick() {
        if (isViewAttached()) {
            getView().hideKeyboard();
            getView().deleteHistoryAndFavoriteLists();
            getRepository().deleteAllTranslationsAsync();
            EventBus.getDefault().post(new DeleteAllTranslationsEvent());
        }
    }

    @Override
    public void handlePageChanged() {
        if (isViewAttached()) {
            getView().hideKeyboard();
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }


    private void findHistory() {
        getRepository().getHistoryListAsync();
    }
}
