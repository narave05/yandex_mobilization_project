package com.example.narek.project_mobilization_yandex.data.network.api;


import com.example.narek.project_mobilization_yandex.data.model.rest.AvailableLanguagesResponse;
import com.example.narek.project_mobilization_yandex.data.model.rest.TranslationResponse;
import com.example.narek.project_mobilization_yandex.data.network.RestServiceGenerator;
import com.example.narek.project_mobilization_yandex.util.constant.AppConfig;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class YandexTranslateApi {

    private TranslateRestService mRestService = RestServiceGenerator.createService(TranslateRestService.class, AppConfig.TRANSLATE_API_URL);

    public Call<TranslationResponse> translationToSelectedLanguage(String text, String lang) {
        return mRestService.callTranslationToSelectedLanguage(AppConfig.TRANSLATE_API_KEY, text, lang);
    }

    // TODO: 25.03.2017 set default lang
    public Call<AvailableLanguagesResponse> getAvailableLanguages() {
        return mRestService.callAvailableLanguagesList(AppConfig.TRANSLATE_API_KEY, "ru");
    }

    public Call<Object> detectLanguageByText(String text) {
        return mRestService.callDetectLanguageByText(AppConfig.TRANSLATE_API_KEY, text);
    }

    private interface TranslateRestService {

        @GET("translate")
        Call<TranslationResponse> callTranslationToSelectedLanguage(@Query("key") String key, @Query("text") String text,
                                                                    @Query("lang") String lang);

        @GET("getLangs")
        Call<AvailableLanguagesResponse> callAvailableLanguagesList(@Query("key") String key, @Query("ui") String ui);

        @GET("detect")
        Call<Object> callDetectLanguageByText(@Query("key") String key, @Query("text") String text);

    }
}
