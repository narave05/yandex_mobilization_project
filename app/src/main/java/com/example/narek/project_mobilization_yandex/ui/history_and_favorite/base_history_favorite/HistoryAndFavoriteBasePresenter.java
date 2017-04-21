package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.base_history_favorite;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.ui.base_repository.BaseRepositoryPresenter;

import org.greenrobot.eventbus.EventBus;

public abstract class HistoryAndFavoriteBasePresenter<V extends HistoryAndFavoriteBaseContract.IView>
        extends BaseRepositoryPresenter<V> implements HistoryAndFavoriteBaseContract.IPresenter<V>{

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
    public void handleDeleteClick() {
        if (isViewAttached()) {
            getView().deleteHistoryAndFavoriteList();
        }
    }

    @Override
    public void handleSearchText(String newText) {
        if (isViewAttached()) {
            getView().filterAdapterList(newText);
        }
    }

    @Override
    public void handleFavoriteStatusChanged(TranslationDTO translationDTO) {
        getRepository().updateTranslationFavoriteStatusAsync(translationDTO.getPrimaryKey(), translationDTO.isFavorite());
        EventBus.getDefault().post(translationDTO);
    }


}
