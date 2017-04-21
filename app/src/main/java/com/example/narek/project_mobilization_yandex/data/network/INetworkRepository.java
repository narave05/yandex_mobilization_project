package com.example.narek.project_mobilization_yandex.data.network;


import com.example.narek.project_mobilization_yandex.data.model.rest.AvailableLanguagesResponse;
import com.example.narek.project_mobilization_yandex.data.model.rest.DictionaryResponse;
import com.example.narek.project_mobilization_yandex.data.model.rest.TranslationResponse;

import java.io.IOException;

import retrofit2.Response;

public interface INetworkRepository {
    Response<TranslationResponse> findTranslationText(final String inputText, final String languagePairCods) throws IOException;

    Response<AvailableLanguagesResponse> getAvailableLanguageList() throws IOException;

    Response<DictionaryResponse> findDictionaryData(final String inputText, final String languagePairCods) throws IOException;
}
