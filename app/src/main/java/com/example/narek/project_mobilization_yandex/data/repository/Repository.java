package com.example.narek.project_mobilization_yandex.data.repository;

import com.example.narek.project_mobilization_yandex.App;
import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.TranslatedEvent;
import com.example.narek.project_mobilization_yandex.util.NetworkStatusChecker;

import org.greenrobot.eventbus.EventBus;

import static com.example.narek.project_mobilization_yandex.data.repository.RepositoryService.DELETE_ALL_TRANSLATION_COMMAND;
import static com.example.narek.project_mobilization_yandex.data.repository.RepositoryService.GET_HISTORY_LIST_COMMAND;
import static com.example.narek.project_mobilization_yandex.data.repository.RepositoryService.GET_LANGUAGES_FROM_DB_COMMAND;
import static com.example.narek.project_mobilization_yandex.data.repository.RepositoryService.GET_LANGUAGES_FROM_NETWORK_COMMAND;

public class Repository implements IRepository {

    private final String mErrorText = App.getInstance().getString(R.string.no_internet_connection_text);

    @Override
    public void findTranslationDataAsync(String text, String languagePairCodes) {
        if (!NetworkStatusChecker.isNetworkAvailable()) {
            EventBus.getDefault().post(new TranslatedEvent(mErrorText));
            return;
        }
        RepositoryService.startFindingTranslationData(text, languagePairCodes);
    }

    @Override
    public void updateTranslationFavoriteStatusAsync(String primaryKey, boolean isFavorite) {
        RepositoryService.startUpdateFavoriteStatus(primaryKey, isFavorite);
    }

    @Override
    public void getAvailableLanguageListAsync() {
        RepositoryService.startThisServiceByCommand(GET_LANGUAGES_FROM_DB_COMMAND);
    }

    @Override
    public void getAndSaveAvailableLanguageListAsync() {
        RepositoryService.startThisServiceByCommand(GET_LANGUAGES_FROM_NETWORK_COMMAND);
    }

    @Override
    public void getHistoryListAsync() {
        RepositoryService.startThisServiceByCommand(GET_HISTORY_LIST_COMMAND);
    }

    @Override
    public void deleteAllTranslationsAsync() {
            RepositoryService.startThisServiceByCommand(DELETE_ALL_TRANSLATION_COMMAND);
    }
}
