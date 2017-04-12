package com.example.narek.project_mobilization_yandex.ui.history_and_favorite.favorite_list;

import android.util.Log;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.SaveTranslationEvent;
import com.example.narek.project_mobilization_yandex.ui.base.base_repository.BaseRepositoryPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

class FavoriteListPresenter extends BaseRepositoryPresenter<FavoriteListContract.IView>
        implements FavoriteListContract.IPresenter {
    @Override
    public void init() {
        findFavorites();
    }

    private void findFavorites() {

        List<TranslationDTO> favoriteList = getRepository().findFavoriteList();
        if (isViewAttached() && favoriteList != null) {
            getView().showFavoriteList(favoriteList);
        }
    }

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
