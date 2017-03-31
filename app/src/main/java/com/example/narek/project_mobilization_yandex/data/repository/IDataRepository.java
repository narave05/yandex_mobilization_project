package com.example.narek.project_mobilization_yandex.data.repository;

import com.example.narek.project_mobilization_yandex.data.interfaces.ResultCallback;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;

import retrofit2.Call;

public interface IDataRepository {
    void getTranslationData(String text, ResultCallback<TranslationDTO> callback);
}
