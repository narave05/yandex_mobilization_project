package com.example.narek.project_mobilization_yandex.data.model.event_bus_dto;

import com.example.narek.project_mobilization_yandex.data.model.clean.Language;

import java.util.List;

public class AvailableLanguageEvent {

    String mError;
    List<Language> mLanguageList;

    public AvailableLanguageEvent(String error) {
        mError = error;
    }


    public AvailableLanguageEvent(List<Language> languageList) {
        mLanguageList = languageList;
    }

    public String getError() {
        return mError;
    }

    public void setError(String error) {
        mError = error;
    }

    public List<Language> getLanguageList() {
        return mLanguageList;
    }

    public void setLanguageList(List<Language> languageList) {
        mLanguageList = languageList;
    }
}
