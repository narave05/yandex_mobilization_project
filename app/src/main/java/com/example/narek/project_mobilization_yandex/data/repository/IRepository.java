package com.example.narek.project_mobilization_yandex.data.repository;

import com.example.narek.project_mobilization_yandex.data.interfaces.ResultCallback;
import com.example.narek.project_mobilization_yandex.data.model.clean.Language;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;

import java.util.List;

import io.realm.RealmResults;

public interface IRepository {

    void findTranslationDataInBackground(String text, String languagePairCods, ResultCallback<TranslationDTO> callback);

    void saveTranslationDataInBackground(TranslationDTO translationDTO,ResultCallback<TranslationDTO> callback);

    void saveTranslationData(TranslationDTO translationDTO);

    void updateTranslationFavoriteStatus(String primaryKey, final boolean isFavorite);

    void findDictionaryDataInBackground(final String inputText, final String languagePairCods,
                                        final List<String> textResults, final ResultCallback<TranslationDTO> callback);

    void getAvailableLanguageListInBackground(ResultCallback<List<Language>> callback);

    void getHistoryListInBackground(ResultCallback<List<TranslationDTO>> callback);

    List<TranslationDTO> getHistoryList();

    void findFavoriteListInBackground(ResultCallback<List<TranslationDTO>> callback);

    List<TranslationDTO> findFavoriteList();

}
