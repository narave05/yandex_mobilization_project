package com.example.narek.project_mobilization_yandex.data.repository;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.narek.project_mobilization_yandex.App;
import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.db.DatabaseRepository;
import com.example.narek.project_mobilization_yandex.data.db.IDatabaseRepository;
import com.example.narek.project_mobilization_yandex.data.model.clean.Dictionary;
import com.example.narek.project_mobilization_yandex.data.model.clean.Language;
import com.example.narek.project_mobilization_yandex.data.model.dao.LanguageDao;
import com.example.narek.project_mobilization_yandex.data.model.dao.TranslationDao;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.AllTranslationsEvent;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.AvailableLanguageEvent;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.FavoriteTranslationsEvent;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.TranslatedEvent;
import com.example.narek.project_mobilization_yandex.data.model.rest.AvailableLanguagesResponse;
import com.example.narek.project_mobilization_yandex.data.model.rest.DictionaryResponse;
import com.example.narek.project_mobilization_yandex.data.model.rest.TranslationResponse;
import com.example.narek.project_mobilization_yandex.data.network.INetworkRepository;
import com.example.narek.project_mobilization_yandex.data.network.NetworkRepository;
import com.example.narek.project_mobilization_yandex.util.NetworkStatusChecker;
import com.google.gson.internal.LinkedTreeMap;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Response;

public class RepositoryService extends IntentService {

    public static final String COMMAND = "command";
    public static final String ORIGINAL_TEXT = "originalText";
    public static final String LANGUAGE_PAIR_CODES = "languagePairCodes";
    public static final String PRIMARY_KEY = "primaryKey";
    public static final String IS_FAVORITE = "isFavorite";
    public static final int FIND_TRANSLATION_COMMAND = 1;
    public static final int UPDATE_FAVORITE_STATUS_COMMAND = 2;
    public static final int GET_LANGUAGES_FROM_DB_COMMAND = 3;
    public static final int GET_LANGUAGES_FROM_NETWORK_COMMAND = 5;
    public static final int DELETE_ALL_TRANSLATION_COMMAND = 6;
    public static final int GET_HISTORY_LIST_COMMAND = 4;

    IDatabaseRepository mDatabaseRepository = new DatabaseRepository();
    INetworkRepository mNetworkRepository = new NetworkRepository();

    public static Intent getIntent(int command) {
        Context context = App.getInstance().getApplicationContext();
        Intent intent = new Intent(context, RepositoryService.class);
        intent.putExtra(COMMAND, command);
        return intent;
    }

    public static void startThisService(Intent intent) {
        Context context = App.getInstance().getApplicationContext();
        context.startService(intent);
    }

    public static void startThisServiceByCommand(int command) {
        Intent intent = getIntent(command);
        intent.putExtra(COMMAND, command);
        startThisService(intent);
    }

    public static void startFindingTranslationData(String originalText, String languagePairCods) {
        Intent intent = getIntent(FIND_TRANSLATION_COMMAND);
        intent.putExtra(ORIGINAL_TEXT, originalText);
        intent.putExtra(LANGUAGE_PAIR_CODES, languagePairCods);
        startThisService(intent);
    }

    public static void startUpdateFavoriteStatus(String primaryKey, boolean isFavorite) {
        Intent intent = getIntent(UPDATE_FAVORITE_STATUS_COMMAND);
        intent.putExtra(PRIMARY_KEY, primaryKey);
        intent.putExtra(IS_FAVORITE, isFavorite);
        startThisService(intent);
    }

    public RepositoryService() {
        super(RepositoryService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            switch (intent.getIntExtra(COMMAND, 0)) {

                case FIND_TRANSLATION_COMMAND:
                    String originalText = intent.getStringExtra(ORIGINAL_TEXT);
                    String languagePairCods = intent.getStringExtra(LANGUAGE_PAIR_CODES);
                    findTranslationData(originalText, languagePairCods);
                    break;
                case UPDATE_FAVORITE_STATUS_COMMAND:
                    String primaryKey = intent.getStringExtra(PRIMARY_KEY);
                    boolean isFavorite = intent.getBooleanExtra(IS_FAVORITE, false);
                    updateTranslationFavoriteStatus(primaryKey, isFavorite);
                    break;
                case GET_LANGUAGES_FROM_DB_COMMAND:
                    getAvailableLanguageListFromDb();
                    break;
                case GET_HISTORY_LIST_COMMAND:
                    getHistoryList();
                    break;
                case GET_LANGUAGES_FROM_NETWORK_COMMAND:
                    getAvailableLanguageListFromNetwork();
                    break;
                case DELETE_ALL_TRANSLATION_COMMAND:
                    deleteAllTranslations();
                    break;
                default:
                    break;
            }
        }
    }


    public void findTranslationData(String originalText, String languagePairCods) {

        Realm realm = Realm.getDefaultInstance();
        final String primaryKey = originalText + languagePairCods;
        TranslationDao translationFromDb = mDatabaseRepository.getTranslationByPrimaryKey(realm, primaryKey);
        if (translationFromDb != null) {

            TranslationDTO translationDTO = new TranslationDTO(translationFromDb);

            EventBus.getDefault().post(new TranslatedEvent(translationDTO));
            if (!realm.isInTransaction()) {
                realm.beginTransaction();
            }
            translationFromDb.setCreatedOrUpdatedDate(new Date());
            saveOrUpdateTranslation(realm, translationFromDb);

            return;
        }
        realm.close();

        try {
            Response<TranslationResponse> translationResponse = mNetworkRepository.findTranslationText(originalText, languagePairCods);
            if (translationResponse.isSuccessful()) {

                List<String> translationTextList = translationResponse.body().getTextList();

                Response<DictionaryResponse> dictionaryDataResponse = mNetworkRepository.findDictionaryData(originalText, languagePairCods);

                TranslationDTO translationDTO = new TranslationDTO(originalText, languagePairCods, translationTextList, null);
                if (dictionaryDataResponse.isSuccessful()) {
                    Dictionary dictionary = new Dictionary(dictionaryDataResponse.body().getDictionaryList());
                    translationDTO.setDictionary(dictionary);
                }

                EventBus.getDefault().post(new TranslatedEvent(translationDTO));

                TranslationDao translationDao = new TranslationDao(translationDTO);
                saveOrUpdateTranslation(translationDao);

            } else {
                String error = translationResponse.errorBody().string();
                Log.e("findTranslationError: ", " " + error);
                EventBus.getDefault().post(new TranslatedEvent(error));
            }
        } catch (IOException error) {
            error.printStackTrace();
            EventBus.getDefault().post(new TranslatedEvent(error.getMessage()));
        }
    }

    public void updateTranslationFavoriteStatus(String primaryKey, boolean isFavorite) {
        Realm realm = Realm.getDefaultInstance();
        mDatabaseRepository.updateFavoriteStatus(realm, primaryKey, isFavorite);
        realm.close();
    }

    public void getHistoryList() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<TranslationDao> allTranslations = mDatabaseRepository.getAllTranslations(realm);
        if (allTranslations == null) {
            String error = getString(R.string.history_list_null_text);
            EventBus.getDefault().postSticky(new AllTranslationsEvent(error));
            EventBus.getDefault().postSticky(new FavoriteTranslationsEvent(error));
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

    public void getAvailableLanguageListFromNetwork() {

        if (!NetworkStatusChecker.isNetworkAvailable()) {
            handleLanguageListError(getString(R.string.no_internet_connection_text));
            return;
        }
        try {
            Response<AvailableLanguagesResponse> languageListResponse = mNetworkRepository.getAvailableLanguageList();
            if (languageListResponse.isSuccessful()) {

                AvailableLanguagesResponse languagesResponse = languageListResponse.body();
                LinkedTreeMap<String, String> languages = languagesResponse.getLanguages();

                List<LanguageDao> languageList = new ArrayList<>();

                Set<String> langKeys = languages.keySet();
                for (String langKey : langKeys) {
                    languageList.add(new LanguageDao(langKey, languages.get(langKey)));
                }
                saveAvailableLanguageList(languageList);
                getAvailableLanguageListFromDb();

            } else {
                String error = languageListResponse.errorBody().string();
                Log.e("getLanguageNetError: ", " " + error);
                handleLanguageListError(error);
            }

        } catch (IOException error) {
            error.printStackTrace();
            handleLanguageListError(error.getMessage());
        }
    }

    private void handleLanguageListError(String error) {
        Realm realm = Realm.getDefaultInstance();
        try {
            RealmResults<LanguageDao> languageDaoList = mDatabaseRepository.getLanguageList(realm);
            if (languageDaoList == null || languageDaoList.isEmpty())
                EventBus.getDefault().postSticky(new AvailableLanguageEvent(error));

        } catch (Exception e) {
            e.printStackTrace();
            EventBus.getDefault().postSticky(new AvailableLanguageEvent(error));
            realm.close();
        }
    }

    public void getAvailableLanguageListFromDb() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<LanguageDao> languageDaoList = mDatabaseRepository.getLanguageList(realm);

        if (languageDaoList != null && !languageDaoList.isEmpty()) {
            List<Language> languageList = new ArrayList<>();
            for (LanguageDao languageDao : languageDaoList) {
                languageList.add(new Language(languageDao.getCode(), languageDao.getFullName()));
            }
            EventBus.getDefault().post(new AvailableLanguageEvent(languageList));
        }
        realm.close();
    }

    public void saveAvailableLanguageList(List<LanguageDao> languageDaoList) {
        Realm realm = Realm.getDefaultInstance();
        mDatabaseRepository.saveLanguageList(realm, languageDaoList);
        realm.close();
    }

    private void saveOrUpdateTranslation(TranslationDao translationDao) {
        Realm realm = Realm.getDefaultInstance();
        mDatabaseRepository.saveTranslation(realm, translationDao);
        realm.close();
    }

    private void saveOrUpdateTranslation(Realm realm, TranslationDao translationDao) {
        mDatabaseRepository.saveTranslation(realm, translationDao);
        realm.close();
    }

    private void deleteAllTranslations() {
        Realm realm = Realm.getDefaultInstance();
        mDatabaseRepository.deleteAllTranslations(realm);
        realm.close();
    }
}
