package com.example.narek.project_mobilization_yandex.data;


import com.example.narek.project_mobilization_yandex.data.model.DictionaryResponse;
import com.example.narek.project_mobilization_yandex.data.network_api.DictionaryRestService;
import com.example.narek.project_mobilization_yandex.data.network_api.RestServiceGenerator;
import com.example.narek.project_mobilization_yandex.util.constant.AppConfig;

import retrofit2.Call;

public class YandexDictionaryApi {

    private DictionaryRestService mRestService = RestServiceGenerator.createService(DictionaryRestService.class, AppConfig.DICTIONARY_API_URL);

    public Call<DictionaryResponse> lookup(String text) {
        return mRestService.lookup(AppConfig.DICTIONARY_API_KEY, text, "en-ru");
    }
}
