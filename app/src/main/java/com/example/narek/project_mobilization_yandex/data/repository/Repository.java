package com.example.narek.project_mobilization_yandex.data.repository;

import com.example.narek.project_mobilization_yandex.data.db.DatabaseRepository;
import com.example.narek.project_mobilization_yandex.data.interfaces.ResultCallback;
import com.example.narek.project_mobilization_yandex.data.model.clean.Dictionary;
import com.example.narek.project_mobilization_yandex.data.model.clean.Language;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.data.model.rest.AvailableLanguagesResponse;
import com.example.narek.project_mobilization_yandex.data.model.rest.DictionaryResponse;
import com.example.narek.project_mobilization_yandex.data.model.rest.TranslationResponse;
import com.example.narek.project_mobilization_yandex.data.network.api.YandexDictionaryApi;
import com.example.narek.project_mobilization_yandex.data.network.api.YandexTranslateApi;
import com.example.narek.project_mobilization_yandex.util.NetworkStatusChecker;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository implements IRepository {

    private static final Repository ourInstance = new Repository();
    private YandexDictionaryApi mDictionaryApi = new YandexDictionaryApi();
    private YandexTranslateApi mTranslateApi = new YandexTranslateApi();

    public static Repository getInstance() {
        return ourInstance;
    }

    private Repository() {
    }

    @Override
    public void findTranslationDataInBackground(final String inputText, final String languagePairCods,
                                                final ResultCallback<TranslationDTO> callback) {
        callback.onStart();
        if (!NetworkStatusChecker.isNetworkAvailable()) {
            callback.onError(new Exception("no internet connection"));
            return;
        }

        TranslationDTO translationDTO = DatabaseRepository.getTranslationByPrimaryKey(inputText + languagePairCods);
        if (translationDTO != null) {
            callback.onResult(translationDTO);
            return;
        }

        mTranslateApi.translationToSelectedLanguage(inputText, languagePairCods).enqueue(new Callback<TranslationResponse>() {
            @Override
            public void onResponse(Call<TranslationResponse> call, Response<TranslationResponse> response) {
                if (response.isSuccessful()) {
                    TranslationResponse body = response.body();
                    findDictionaryDataInBackground(inputText, languagePairCods, body.getTextList(), callback);
                } else {
                    callback.onError(new Exception(String.valueOf(response.code())));
                }
            }

            @Override
            public void onFailure(Call<TranslationResponse> call, Throwable t) {
                callback.onError(t);
            }
        });

    }

    @Override
    public void saveTranslationDataInBackground(TranslationDTO translationDTO, ResultCallback<TranslationDTO> callback) {

    }

    @Override
    public void saveTranslationData(TranslationDTO translationDTO) {

    }

    @Override
    public void findDictionaryDataInBackground(final String inputText, final String languagePairCods,
                                               final List<String> textResults, final ResultCallback<TranslationDTO> callback) {

        mDictionaryApi.lookupDictionaryResult(inputText, languagePairCods).enqueue(new Callback<DictionaryResponse>() {
            @Override
            public void onResponse(Call<DictionaryResponse> call, Response<DictionaryResponse> response) {
                final TranslationDTO translationDTO = new TranslationDTO(inputText, languagePairCods, textResults, null);
                if (response.isSuccessful()) {
                    Dictionary dictionary = new Dictionary(response.body().getDictionaryList());
                    translationDTO.setDictionary(dictionary);
                }

                DatabaseRepository.saveTranslation(translationDTO);

                callback.onResult(translationDTO);
            }

            @Override
            public void onFailure(Call<DictionaryResponse> call, Throwable t) {
                callback.onError(t);
            }
        });
    }


    @Override
    public void updateTranslationFavoriteStatus(String primaryKey, boolean isFavorite) {
        DatabaseRepository.updateFavoriteStatus(primaryKey, isFavorite);
    }


    @Override
    public void getAvailableLanguageListInBackground(final ResultCallback<List<Language>> callback) {
        callback.onStart();
        if (!NetworkStatusChecker.isNetworkAvailable()) {
            callback.onError(new Exception("no internet connection"));
            return;
        }

        mTranslateApi.getAvailableLanguages().enqueue(new Callback<AvailableLanguagesResponse>() {
            @Override
            public void onResponse(Call<AvailableLanguagesResponse> call, Response<AvailableLanguagesResponse> response) {
                if (response.isSuccessful()) {
                    AvailableLanguagesResponse languagesResponse = response.body();
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
                    callback.onResult(languageList);
                } else {
                    callback.onError(new Exception(String.valueOf(response.code())));
                }
            }

            @Override
            public void onFailure(Call<AvailableLanguagesResponse> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    @Override
    public void getHistoryListInBackground(ResultCallback<List<TranslationDTO>> callback) {

    }

    @Override
    public RealmResults<TranslationDTO> getHistoryList() {
        RealmResults<TranslationDTO> allTranslations = DatabaseRepository.getAllTranslations();
        return allTranslations;
    }

    @Override
    public void findFavoriteListInBackground(ResultCallback<List<TranslationDTO>> callback) {
        // mDatabaseRepository.findFavoritesAsync(callback);
    }

    @Override
    public RealmResults<TranslationDTO> findFavoriteList() {
        RealmResults<TranslationDTO> favorites = DatabaseRepository.getFavorites();
        return favorites;
    }
}
