package com.example.narek.project_mobilization_yandex.data.model.event_bus_dto;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDto;

import java.util.List;

public class FavoriteTranslationsEvent {

    private String mError;
    private List<TranslationDto> mTranslationDtos;

    public FavoriteTranslationsEvent(String error) {
        mError = error;
    }


    public FavoriteTranslationsEvent(List<TranslationDto> translationDtos) {
        mTranslationDtos = translationDtos;
    }

    public String getError() {
        return mError;
    }

    public void setError(String error) {
        mError = error;
    }

    public List<TranslationDto> getTranslationDtos() {
        return mTranslationDtos;
    }

    public void setTranslationDtos(List<TranslationDto> translationDtos) {
        mTranslationDtos = translationDtos;
    }
}
