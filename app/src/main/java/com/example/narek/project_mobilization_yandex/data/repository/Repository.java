package com.example.narek.project_mobilization_yandex.data.repository;

import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.AvailableLanguageEvent;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.TranslatedEvent;
import com.example.narek.project_mobilization_yandex.util.NetworkStatusChecker;

import org.greenrobot.eventbus.EventBus;

public class Repository implements IRepository {

    @Override
    public void findTranslationDataAsync(String text, String languagePairCods) {
        if (!NetworkStatusChecker.isNetworkAvailable()) {
            EventBus.getDefault().post(new TranslatedEvent("no internet connection"));
            return;
        }
        RepositoryService.startThisService(1, text, languagePairCods);
    }

    @Override
    public void updateTranslationFavoriteStatusAsync(String primaryKey, boolean isFavorite) {
        if (!NetworkStatusChecker.isNetworkAvailable()) {
            // TODO: 14.04.2017
           // EventBus.getDefault().post(new TranslatedEvent("no internet connection"));
            return;
        }
        RepositoryService.startThisService(2, primaryKey, isFavorite);
    }

    @Override
    public void getAvailableLanguageListAsync() {
        if (!NetworkStatusChecker.isNetworkAvailable()) {
            EventBus.getDefault().post(new AvailableLanguageEvent("no internet connection"));
            return;
        }
        RepositoryService.startThisService(3);
    }

    @Override
    public void getHistoryListAsync() {
        RepositoryService.startThisService(4);
    }
}
