package com.example.narek.project_mobilization_yandex.data.repository;

import com.example.narek.project_mobilization_yandex.data.interfaces.ResultCallback;
import com.example.narek.project_mobilization_yandex.data.model.clean.Dictionary;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.data.model.rest_response.DictionaryResponse;
import com.example.narek.project_mobilization_yandex.data.model.rest_response.TranslationResponse;
import com.example.narek.project_mobilization_yandex.data.network.api.YandexDictionaryApi;
import com.example.narek.project_mobilization_yandex.data.network.api.YandexTranslateApi;
import com.example.narek.project_mobilization_yandex.util.NetworkStatusChecker;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRepository implements IDataRepository {

    private static final DataRepository ourInstance = new DataRepository();
    private YandexDictionaryApi mDictionaryApi = new YandexDictionaryApi();
    private YandexTranslateApi mTranslateApi = new YandexTranslateApi();

    public static DataRepository getInstance() {
        return ourInstance;
    }

    private DataRepository() {
    }


    @Override
    public void getTranslationData(final String inputText, final ResultCallback<TranslationDTO> callback) {

        callback.onStart();
        if (!NetworkStatusChecker.isNetworkAvailable()) {
            callback.onError(new Exception("no internet connection"));
            return;
        }
        mTranslateApi.translationToSelectedLanguage(inputText, "en-ru").enqueue(new Callback<TranslationResponse>() {
            @Override
            public void onResponse(Call<TranslationResponse> call, Response<TranslationResponse> response) {
                if (response.isSuccessful()) {
                    TranslationResponse body = response.body();
                        getDictionaryResult(inputText, body.getTextList(), callback);
                } else {
                    callback.onError(new Exception(String.valueOf(response.code())));
                }
            }

            @Override
            public void onFailure(Call<TranslationResponse> call, Throwable t) {
                callback.onError(t);
            }
        });

    }

    private void getDictionaryResult(final String inputText, final List<String> textResults, final ResultCallback<TranslationDTO> callback) {

        mDictionaryApi.lookupDictionaryResult(inputText).enqueue(new Callback<DictionaryResponse>() {
            @Override
            public void onResponse(Call<DictionaryResponse> call, Response<DictionaryResponse> response) {
                if (response.isSuccessful() && !call.isCanceled()) {
                    Dictionary dictionary = new Dictionary(response.body().getDictionaryList());
                    TranslationDTO translationDTO = new TranslationDTO(inputText, textResults, dictionary);
                    callback.onResult(translationDTO);
                } else {
                    callback.onError(new Exception(String.valueOf(response.code())));
                }
            }

            @Override
            public void onFailure(Call<DictionaryResponse> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
