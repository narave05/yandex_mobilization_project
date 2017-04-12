package com.example.narek.project_mobilization_yandex.data.network.api;


import com.example.narek.project_mobilization_yandex.data.model.rest.DictionaryResponse;
import com.example.narek.project_mobilization_yandex.data.network.RestServiceGenerator;
import com.example.narek.project_mobilization_yandex.util.constant.AppConfig;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.narek.project_mobilization_yandex.util.constant.Constants.SHORT_POS;

public class YandexDictionaryApi {

    private DictionaryRestService mRestService = RestServiceGenerator.createService(DictionaryRestService.class, AppConfig.DICTIONARY_API_URL);

    public Call<DictionaryResponse> lookupDictionaryResult(String text, String languagePairCods) {
        return mRestService.callLookupDictionaryResult(AppConfig.DICTIONARY_API_KEY, text, languagePairCods, SHORT_POS);
    }


    private interface DictionaryRestService {
        @GET("lookup")
        Call<DictionaryResponse> callLookupDictionaryResult(@Query("key") String key, @Query("text") String text,
                                                            @Query("lang") String lang, @Query("flags") int flags);
    }
}
