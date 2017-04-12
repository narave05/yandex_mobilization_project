package com.example.narek.project_mobilization_yandex.data.db;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.SaveTranslationEvent;

import org.greenrobot.eventbus.EventBus;

public class DatabaseRepositoryService extends IntentService {

    public static final String DB_ACTION = "dbActionIntentKey";

    public DatabaseRepositoryService() {
        super(DatabaseRepositoryService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            switch (intent.getIntExtra(DB_ACTION, 0)) {
                case 1:
                    break;
                case 2:
                    String primaryKey = intent.getStringExtra("primaryKeyExtra");
                    getTranslationByPrimaryKey(primaryKey);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    break;
            }
        }
    }


    public void getTranslationByPrimaryKey(final String primary) {
        TranslationDTO translationDTO = DatabaseRepository.getTranslationByPrimaryKey(primary);
        Log.e("onHandleIntent: ", " " + translationDTO);
        EventBus.getDefault().post(new SaveTranslationEvent());
    }

    public void updateFavoriteStatus(final String primaryKey, final boolean isFavorite) {

    }

    public void getAllTranslations() {
        DatabaseRepository.getAllTranslations();
    }

    public void getFavorites() {

    }
}
