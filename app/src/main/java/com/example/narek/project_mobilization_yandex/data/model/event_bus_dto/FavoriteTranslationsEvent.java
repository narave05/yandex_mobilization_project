package com.example.narek.project_mobilization_yandex.data.model.event_bus_dto;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;

import java.util.List;

public class FavoriteTranslationsEvent {

    String mError;
    List<TranslationDTO> mTranslationDTOs;

    public FavoriteTranslationsEvent(String error) {
        mError = error;
    }


    public FavoriteTranslationsEvent(List<TranslationDTO> translationDTOs) {
        mTranslationDTOs = translationDTOs;
    }

    public String getError() {
        return mError;
    }

    public void setError(String error) {
        mError = error;
    }

    public List<TranslationDTO> getTranslationDTOs() {
        return mTranslationDTOs;
    }

    public void setTranslationDTOs(List<TranslationDTO> translationDTOs) {
        mTranslationDTOs = translationDTOs;
    }
}
