package com.example.narek.project_mobilization_yandex.data.network;

import com.example.narek.project_mobilization_yandex.data.model.rest.AvailableLanguagesResponse;
import com.example.narek.project_mobilization_yandex.data.model.rest.DictionaryResponse;
import com.example.narek.project_mobilization_yandex.data.model.rest.TranslationResponse;
import com.example.narek.project_mobilization_yandex.data.network.api.YandexDictionaryApi;
import com.example.narek.project_mobilization_yandex.data.network.api.YandexTranslateApi;

import java.io.IOException;

import retrofit2.Response;

public class NetworkRepository {

    public static Response<TranslationResponse> findTranslationText(final String inputText, final String languagePairCods) throws IOException {
        YandexTranslateApi translateApi = new YandexTranslateApi();
        Response<TranslationResponse> response = translateApi.translationToSelectedLanguage(inputText, languagePairCods).execute();
        return response;
    }

    public static Response<AvailableLanguagesResponse> getAvailableLanguageList() throws IOException {
        YandexTranslateApi translateApi = new YandexTranslateApi();
        Response<AvailableLanguagesResponse> availableLanguagesResponse = translateApi.getAvailableLanguages().execute();
        return availableLanguagesResponse;
    }

    public static Response<DictionaryResponse> findDictionaryData(final String inputText, final String languagePairCods) throws IOException {
        YandexDictionaryApi dictionaryApi = new YandexDictionaryApi();
        Response<DictionaryResponse> dictionaryResponse = dictionaryApi.lookupDictionaryResult(inputText, languagePairCods).execute();
        return dictionaryResponse;
    }
}
