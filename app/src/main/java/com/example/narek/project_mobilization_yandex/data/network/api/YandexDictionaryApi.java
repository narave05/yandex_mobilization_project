package com.example.narek.project_mobilization_yandex.data.network.api;


import com.example.narek.project_mobilization_yandex.data.storage.rest_model.DictionaryResponse;
import com.example.narek.project_mobilization_yandex.data.network.RestServiceGenerator;
import com.example.narek.project_mobilization_yandex.util.constant.AppConfig;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class YandexDictionaryApi {

    private DictionaryRestService mRestService = RestServiceGenerator.createService(DictionaryRestService.class, AppConfig.DICTIONARY_API_URL);

    public Call<DictionaryResponse> lookup(String text) {
        return mRestService.lookup(AppConfig.DICTIONARY_API_KEY, text, "en-ru");
    }


    private interface DictionaryRestService {

        @GET("lookup")
        Call<DictionaryResponse> lookup(@Query("key") String key, @Query("text") String text,
                                        @Query("lang") String lang);

    }
}
