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
        callGetHistoryAndFavoriteLists();
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
    public void handleHistoryChange(boolean shouldShowDeleteIcon) {
        if (!isViewAttached()) {
            return;
        }
        if (shouldShowDeleteIcon) {
            getView().showDeleteIcon();
        } else {
            getView().hideDeleteIcon();
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }


    private void callGetHistoryAndFavoriteLists() {
        getRepository().getHistoryListAsync();
    }
}
