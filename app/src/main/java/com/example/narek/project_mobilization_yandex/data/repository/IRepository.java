package com.example.narek.project_mobilization_yandex.data.repository;

public interface IRepository {

    void findTranslationDataAsync(String text, String languagePairCods);

    // void saveTranslationDataAsync(TranslationDTO translationDTO);

    void updateTranslationFavoriteStatusAsync(String primaryKey, final boolean isFavorite);

    void getAvailableLanguageListAsync();

    void getHistoryListAsync();

}
