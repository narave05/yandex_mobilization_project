package com.example.narek.project_mobilization_yandex.data.network;

import com.example.narek.project_mobilization_yandex.data.model.rest.AvailableLanguagesResponse;
import com.example.narek.project_mobilization_yandex.data.model.rest.DictionaryResponse;
import com.example.narek.project_mobilization_yandex.data.model.rest.TranslationResponse;
import com.example.narek.project_mobilization_yandex.data.network.api.YandexDictionaryApi;
import com.example.narek.project_mobilization_yandex.data.network.api.YandexTranslateApi;

import java.io.IOException;

import retrofit2.Response;

public class NetworkRepository implements INetworkRepository {

    private final YandexDictionaryApi mDictionaryApi = new YandexDictionaryApi();
    private YandexTranslateApi mTranslateApi = new YandexTranslateApi();

    @Override
    public Response<TranslationResponse> findTranslationText(final String inputText, final String languagePairCods) throws IOException {
        Response<TranslationResponse> response = mTranslateApi.translationToSelectedLanguage(inputText, languagePairCods).execute();
        return response;
    }

    @Override
    public Response<AvailableLanguagesResponse> getAvailableLanguageList() throws IOException {
        Response<AvailableLanguagesResponse> availableLanguagesResponse = mTranslateApi.getAvailableLanguages().execute();
        return availableLanguagesResponse;
    }

    @Override
    public Response<DictionaryResponse> findDictionaryData(final String inputText, final String languagePairCods) throws IOException {
        Response<DictionaryResponse> dictionaryResponse = mDictionaryApi.lookupDictionaryResult(inputText, languagePairCods).execute();
        return dictionaryResponse;
    }
}
