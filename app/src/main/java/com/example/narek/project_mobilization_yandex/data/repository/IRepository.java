package com.example.narek.project_mobilization_yandex.data.repository;

public interface IRepository {

    void findTranslationDataAsync(String text, String languagePairCods);

    void updateTranslationFavoriteStatusAsync(String primaryKey, final boolean isFavorite);

    void getAvailableLanguageListAsync();

    void getAndSaveAvailableLanguageListAsync();

    void getHistoryListAsync();

    void deleteAllTranslationsAsync();

}
