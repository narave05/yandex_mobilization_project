package com.example.narek.project_mobilization_yandex.data.repository;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.narek.project_mobilization_yandex.App;
import com.example.narek.project_mobilization_yandex.data.db.DatabaseRepository;
import com.example.narek.project_mobilization_yandex.data.model.clean.Dictionary;
import com.example.narek.project_mobilization_yandex.data.model.clean.Language;
import com.example.narek.project_mobilization_yandex.data.model.dao.TranslationDao;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.AllTranslationsEvent;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.AvailableLanguageEvent;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.FavoriteTranslationsEvent;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.TranslatedEvent;
import com.example.narek.project_mobilization_yandex.data.model.rest.AvailableLanguagesResponse;
import com.example.narek.project_mobilization_yandex.data.model.rest.DictionaryResponse;
import com.example.narek.project_mobilization_yandex.data.model.rest.TranslationResponse;
import com.example.narek.project_mobilization_yandex.data.network.NetworkRepository;
import com.google.gson.internal.LinkedTreeMap;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Response;

public class RepositoryService extends IntentService {

    public static final String ACTION = "ActionIntentKey";
    public static final String TRANSLATION_DTO = "translationDTO";
    public static final String ORIGINAL_TEXT = "originalText";
    public static final String LANGUAGE_PAIR_CODS = "languagePairCods";
    public static final String PRIMARY_KEY = "primaryKey";
    public static final String IS_FAVORITE = "isFavorite";

    public static void startThisService(int dbAction) {
        Context context = App.getInstance().getApplicationContext();
        Intent intent = new Intent(context, RepositoryService.class);
        intent.putExtra(ACTION, dbAction);
        context.startService(intent);
    }

    public static void startThisService(int dbAction, String originalText, String languagePairCods) {
        Context context = App.getInstance().getApplicationContext();
        Intent intent = new Intent(context, RepositoryService.class);
        intent.putExtra(ACTION, dbAction);
        intent.putExtra(ORIGINAL_TEXT, originalText);
        intent.putExtra(LANGUAGE_PAIR_CODS, languagePairCods);
        context.startService(intent);
    }

    public static void startThisService(int dbAction, String primaryKey, boolean isFavorite) {
        Context context = App.getInstance().getApplicationContext();
        Intent intent = new Intent(context, RepositoryService.class);
        intent.putExtra(ACTION, dbAction);
        intent.putExtra(PRIMARY_KEY, primaryKey);
        intent.putExtra(IS_FAVORITE, isFavorite);
        context.startService(intent);
    }

    public RepositoryService() {
        super(RepositoryService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            switch (intent.getIntExtra(ACTION, 0)) {
                case 1:
                    String originalText = intent.getStringExtra(ORIGINAL_TEXT);
                    String languagePairCods = intent.getStringExtra(LANGUAGE_PAIR_CODS);
                    findTranslationData(originalText, languagePairCods);
                    break;
                case 2:
                    String primaryKey = intent.getStringExtra(PRIMARY_KEY);
                    boolean isFavorite = intent.getBooleanExtra(IS_FAVORITE, false);
                    updateTranslationFavoriteStatus(primaryKey, isFavorite);
                    break;
                case 3:
                    getAvailableLanguageList();
                    break;
                case 4:
                    getHistoryList();
                    break;
                default:
                    break;
            }
        }
    }

    public void findTranslationData(String originalText, String languagePairCods) {

        Realm realm = Realm.getDefaultInstance();
        TranslationDao translationFromDb = DatabaseRepository.getTranslationByPrimaryKey(realm, originalText + languagePairCods);
        if (translationFromDb != null) {

            TranslationDTO translationDTO = new TranslationDTO(translationFromDb);

            EventBus.getDefault().post(new TranslatedEvent(translationDTO));
            if (!realm.isInTransaction()) {
                realm.beginTransaction();
            }
            // FIXME: 14.04.2017
            translationFromDb.setCreatedOrUpdatedDate(new Date());
            saveOrUpdateTranslation(realm, translationFromDb);

            return;
        }
        realm.close();

        try {
            Response<TranslationResponse> translationResponse = NetworkRepository.findTranslationText(originalText, languagePairCods);
            if (translationResponse.isSuccessful()) {

                List<String> translationTextList = translationResponse.body().getTextList();

                Response<DictionaryResponse> dictionaryDataResponse = NetworkRepository.findDictionaryData(originalText, languagePairCods);

                TranslationDTO translationDTO = new TranslationDTO(originalText, languagePairCods, translationTextList, null);
                if (dictionaryDataResponse.isSuccessful()) {
                    Dictionary dictionary = new Dictionary(dictionaryDataResponse.body().getDictionaryList());
                    translationDTO.setDictionary(dictionary);
                }

                EventBus.getDefault().postSticky(new TranslatedEvent(translationDTO));

                TranslationDao translationDao = new TranslationDao(translationDTO);
                saveOrUpdateTranslation(translationDao);

            } else {
                // TODO: 13.04.2017 handle error
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public void updateTranslationFavoriteStatus(String primaryKey, boolean isFavorite) {
        Realm realm = Realm.getDefaultInstance();
        DatabaseRepository.updateFavoriteStatus(realm, primaryKey, isFavorite);
        realm.close();
    }

    public void getHistoryList() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<TranslationDao> allTranslations = DatabaseRepository.getAllTranslations(realm);
        if (allTranslations == null) {
            realm.close();
            return;
        }
        List<TranslationDTO> historyList = new ArrayList<>();
        List<TranslationDTO> favoriteList = new ArrayList<>();

        for (TranslationDao translationDao : allTranslations) {

            TranslationDTO translationDTO = new TranslationDTO(translationDao);
            historyList.add(translationDTO);

            if (translationDTO.isFavorite()) {
                favoriteList.add(translationDTO);
            }
        }

        EventBus.getDefault().postSticky(new AllTranslationsEvent(historyList));
        EventBus.getDefault().postSticky(new FavoriteTranslationsEvent(favoriteList));

        realm.close();
    }

    public void getAvailableLanguageList() {

        try {
            Response<AvailableLanguagesResponse> languageListResponse = NetworkRepository.getAvailableLanguageList();
            if (languageListResponse.isSuccessful()) {

                AvailableLanguagesResponse languagesResponse = languageListResponse.body();
                LinkedTreeMap<String, String> languages = languagesResponse.getLanguages();

                List<Language> languageList = new ArrayList<>();

                Set<String> langKeys = languages.keySet();
                for (String langKey : langKeys) {
                    languageList.add(new Language(langKey, languages.get(langKey)));
                }
                Collections.sort(languageList, new Comparator<Language>() {
                    @Override
                    public int compare(Language first, Language second) {
                        return first.getFullName().compareTo(second.getFullName());
                    }
                });
                EventBus.getDefault().post(new AvailableLanguageEvent(languageList));

            } else {
                // TODO: 13.04.2017 handle error
            }
            // TODO: 13.04.2017 Event Bus
        } catch (IOException error) {
            error.printStackTrace();
            // TODO: 13.04.2017 Event Bus
        }
    }

    private void saveOrUpdateTranslation(TranslationDao translationDao) {
        Realm realm = Realm.getDefaultInstance();
        DatabaseRepository.saveTranslation(realm, translationDao);
        realm.close();
    }

    private void saveOrUpdateTranslation(Realm realm, TranslationDao translationDao) {
        DatabaseRepository.saveTranslation(realm, translationDao);
        realm.close();
    }
}
