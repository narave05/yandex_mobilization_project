package com.example.narek.project_mobilization_yandex.data.network_api;

import com.example.narek.project_mobilization_yandex.data.model.AvailableLanguagesResponse;
import com.example.narek.project_mobilization_yandex.data.model.TranslationResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TranslateRestService {

    @GET("translate")
    Call<TranslationResponse> callTranslationToSelectedLanguage(@Query("key") String key, @Query("text") String text,
                                                                @Query("lang") String lang);

    @GET("getLangs")
    Call<AvailableLanguagesResponse> callAvailableLanguagesList(@Query("key") String key, @Query("ui") String ui);

    @GET("detect")
    Call<Object> callDetectLanguageByText(@Query("key") String key, @Query("text") String text);


}
