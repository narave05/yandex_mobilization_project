package com.example.narek.project_mobilization_yandex.data.network.api;


import com.example.narek.project_mobilization_yandex.data.model.rest_response.DictionaryResponse;
import com.example.narek.project_mobilization_yandex.data.network.RestServiceGenerator;
import com.example.narek.project_mobilization_yandex.util.constant.AppConfig;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class YandexDictionaryApi {

    private DictionaryRestService mRestService = RestServiceGenerator.createService(DictionaryRestService.class, AppConfig.DICTIONARY_API_URL);

    public Call<DictionaryResponse> lookupDictionaryResult(String text) {
        return mRestService.callLookupDictionaryResult(AppConfig.DICTIONARY_API_KEY, text, "en-ru");
    }


    private interface DictionaryRestService {
        @GET("lookup")
        Call<DictionaryResponse> callLookupDictionaryResult(@Query("key") String key, @Query("text") String text,
                                                            @Query("lang") String lang);
    }
}
