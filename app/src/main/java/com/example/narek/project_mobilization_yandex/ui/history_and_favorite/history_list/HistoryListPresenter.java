package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.history_list;

import android.util.Log;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.SaveTranslationEvent;
import com.example.narek.project_mobilization_yandex.ui.base.base_repository.BaseRepositoryPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

class HistoryListPresenter extends BaseRepositoryPresenter<HistoryListContract.IView>
        implements HistoryListContract.IPresenter {

    @Override
    public void init() {
        findHistory();
    }

    private void findHistory() {
        List<TranslationDTO> historyList = getRepository().getHistoryList();
        if (isViewAttached() && historyList != null) {
            getView().showHistoryList(historyList);
        }
    }

    @Override
    public void handleFavoriteStatusChanged(String primaryKey, boolean isChecked) {
        getRepository().updateTranslationFavoriteStatus(primaryKey, isChecked);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSaveTranslationEvent(SaveTranslationEvent event) {
        Log.e("onSaveEvent: ", " " + "onSaveEvent");
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
    }
}
