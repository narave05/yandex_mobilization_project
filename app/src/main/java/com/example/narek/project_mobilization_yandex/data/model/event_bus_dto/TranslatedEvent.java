package com.example.narek.project_mobilization_yandex.data.model.event_bus_dto;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;

public class TranslatedEvent {
    private String mError;
    private TranslationDTO mTranslationDTO;

    public TranslatedEvent( TranslationDTO translationDTO) {
        mTranslationDTO = translationDTO;
    }

    public TranslatedEvent( String  error) {
        mError = error;
    }

    public String getError() {
        return mError;
    }

    public void setError(String error) {
        mError = error;
    }

    public TranslationDTO getTranslationDTO() {
        return mTranslationDTO;
    }

    public void setTranslationDTO(TranslationDTO translationDTO) {
        mTranslationDTO = translationDTO;
    }
}
