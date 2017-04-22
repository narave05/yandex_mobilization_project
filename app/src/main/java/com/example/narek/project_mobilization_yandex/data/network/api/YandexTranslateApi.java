package com.example.narek.project_mobilization_yandex.data.network.api;


import com.example.narek.project_mobilization_yandex.data.model.rest.AvailableLanguagesResponse;
import com.example.narek.project_mobilization_yandex.data.model.rest.TranslationResponse;
import com.example.narek.project_mobilization_yandex.data.network.RestServiceGenerator;
import com.example.narek.project_mobilization_yandex.util.constant.AppConfig;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.narek.project_mobilization_yandex.util.constant.Constants.UI;

public class YandexTranslateApi {

    private TranslateRestService mRestService = RestServiceGenerator.createService(TranslateRestService.class, AppConfig.TRANSLATE_API_URL);

    public Call<TranslationResponse> translationToSelectedLanguage(String text, String lang) {
        return mRestService.callTranslationToSelectedLanguage(AppConfig.TRANSLATE_API_KEY, text, lang);
    }

    public Call<AvailableLanguagesResponse> getAvailableLanguages() {
        return mRestService.callAvailableLanguagesList(AppConfig.TRANSLATE_API_KEY, UI);
    }

    private interface TranslateRestService {

        @GET("translate")
        Call<TranslationResponse> callTranslationToSelectedLanguage(@Query("key") String key, @Query("text") String text,
                                                                    @Query("lang") String lang);

        @GET("getLangs")
        Call<AvailableLanguagesResponse> callAvailableLanguagesList(@Query("key") String key, @Query("ui") String ui);

    }
}
