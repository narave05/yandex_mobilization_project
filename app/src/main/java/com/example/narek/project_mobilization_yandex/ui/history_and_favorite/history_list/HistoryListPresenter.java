package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.history_list;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDto;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.AllTranslationsEvent;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.TranslatedEvent;
import com.example.narek.project_mobilization_yandex.ui.history_and_favorite.base_history_favorite.HistoryAndFavoriteBasePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

class HistoryListPresenter extends HistoryAndFavoriteBasePresenter<HistoryListContract.IView>
        implements HistoryListContract.IPresenter {

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadHistoryListEvent(AllTranslationsEvent event) {
        if (event.getError() == null) {
            if (isViewAttached()) {
                getView().hideProgress();
                getView().showHistoryList(event.getTranslationDtos());
            }
        } else {
            if (isViewAttached()) {
                getView().hideProgress();
                getView().showHintLayout();
                getView().showError(event.getError());
            }
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadTranslationEvent(TranslatedEvent event) {
        if (event.getError() == null) {
            if (isViewAttached()) {
                getView().hideProgress();
                getView().insertedOrAddHistoryList(event.getTranslationDto());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFavoriteStatusChangedEvent(TranslationDto event) {
        if (isViewAttached()) {
            getView().updateHistoryList(event);
        }
    }
}
