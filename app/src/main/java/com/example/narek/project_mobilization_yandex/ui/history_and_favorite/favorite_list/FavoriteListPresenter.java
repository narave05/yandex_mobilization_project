package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.favorite_list;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.FavoriteTranslationsEvent;
import com.example.narek.project_mobilization_yandex.ui.base_repository.BaseRepositoryPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

class FavoriteListPresenter extends BaseRepositoryPresenter<FavoriteListContract.IView>
        implements FavoriteListContract.IPresenter {
    @Override
    public void init() {
        if (isViewAttached()) {
            getView().showProgress();
        }
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void handleFavoriteStatusChanged(TranslationDTO translationDTO) {
        getRepository().updateTranslationFavoriteStatusAsync(translationDTO.getPrimaryKey(), translationDTO.isFavorite());
        EventBus.getDefault().postSticky(translationDTO);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadFavoriteListEvent(FavoriteTranslationsEvent event) {
        if (event.getError() == null) {
            if (isViewAttached()) {
                getView().hideProgress();
                getView().showFavoriteList(event.getTranslationDTOs());
            }
        } else {
            if (isViewAttached()) {
                getView().hideProgress();
                getView().showError(event.getError());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFavoriteStatusChangedEvent(TranslationDTO event) {
        if (!isViewAttached()) {
            return;
        }
        if (event.isFavorite()) {
            getView().addFavoriteItem(event);
        } else {
            getView().removeFavoriteItem(event);
        }
    }

}
