package com.example.narek.project_mobilization_yandex.data.model.event_bus_dto;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDto;

public class TranslatedEvent {
    private String mError;
    private TranslationDto mTranslationDto;

    public TranslatedEvent( TranslationDto translationDto) {
        mTranslationDto = translationDto;
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

    public TranslationDto getTranslationDto() {
        return mTranslationDto;
    }

    public void setTranslationDto(TranslationDto translationDto) {
        mTranslationDto = translationDto;
    }
}
