package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.history_list;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.AllTranslationsEvent;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.TranslatedEvent;
import com.example.narek.project_mobilization_yandex.ui.base_repository.BaseRepositoryPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

class HistoryListPresenter extends BaseRepositoryPresenter<HistoryListContract.IView>
        implements HistoryListContract.IPresenter {

    @Override
    public void init() {
        findHistory();
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
        getRepository().updateTranslationFavoriteStatusAsync(translationDTO.getPrimaryKey(),
                translationDTO.isFavorite());
        EventBus.getDefault().postSticky(translationDTO);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadHistoryListEvent(AllTranslationsEvent event) {
        if (event.getError() == null) {
            if (isViewAttached()) {
                getView().hideProgress();
                getView().showHistoryList(event.getTranslationDTOs());
            }
        } else {
            if (isViewAttached()) {
                getView().hideProgress();
                getView().showError(event.getError());
            }
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadTranslationEvent(TranslatedEvent event) {
        if (event.getError() == null) {
            if (isViewAttached()) {
                getView().insertedOrAddHistoryList(event.getTranslationDTO());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFavoriteStatusChangedEvent(TranslationDTO event) {
        if (isViewAttached()) {
            getView().updateHistoryList(event);
        }
    }

    private void findHistory() {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getRepository().getHistoryListAsync();
    }
}
