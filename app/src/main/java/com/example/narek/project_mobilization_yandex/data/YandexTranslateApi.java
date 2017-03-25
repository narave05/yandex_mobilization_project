package com.example.narek.project_mobilization_yandex.data;


import com.example.narek.project_mobilization_yandex.data.model.AvailableLanguagesResponse;
import com.example.narek.project_mobilization_yandex.data.model.TranslationResponse;
import com.example.narek.project_mobilization_yandex.data.network_api.TranslateRestService;
import com.example.narek.project_mobilization_yandex.data.network_api.RestServiceGenerator;
import com.example.narek.project_mobilization_yandex.util.constant.AppConfig;

import retrofit2.Call;

public class YandexTranslateApi {

    private TranslateRestService mRestService = RestServiceGenerator.createService(TranslateRestService.class, AppConfig.TRANSLATE_API_URL);

    public Call<TranslationResponse> translationToSelectedLanguage(String text, String lang) {
        return mRestService.callTranslationToSelectedLanguage(AppConfig.TRANSLATE_API_KEY, text, lang);
    }

    // TODO: 25.03.2017 set default lang
    public Call<AvailableLanguagesResponse> getAvailableLanguages() {
        return mRestService.callAvailableLanguagesList(AppConfig.TRANSLATE_API_KEY, "en");
    }

    public Call<Object> detectLanguageByText(String text) {
        return mRestService.callDetectLanguageByText(AppConfig.TRANSLATE_API_KEY, text);
    }
}
