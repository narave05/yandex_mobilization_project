package com.example.narek.project_mobilization_yandex.ui.main;

import android.util.Log;

import com.example.narek.project_mobilization_yandex.data.network.api.YandexTranslateApi;
import com.example.narek.project_mobilization_yandex.data.storage.rest_model.TranslationResponse;
import com.example.narek.project_mobilization_yandex.ui.base.BasePresenter;
import com.example.narek.project_mobilization_yandex.util.StringUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


class RootPresenter extends BasePresenter<RootContract.IView>
        implements RootContract.IPresenter {

    @Override
    public void onCreate() {
        super.onCreate();
        YandexTranslateApi translateApi = new YandexTranslateApi();
        translateApi.translationToSelectedLanguage("all", "en-ru").enqueue(new Callback<TranslationResponse>() {
            @Override
            public void onResponse(Call<TranslationResponse> call, Response<TranslationResponse> response) {
                if (response.isSuccessful()) {
                    Log.e("YandexTranslateApi: ", " " + response.body());
                    if (isViewAttached()) {
                        getView().showMessage(StringUtils.join(response.body().getTextList()));
                    }
                } else {
                    Log.e("YandexTranslateApi: ", " " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<TranslationResponse> call, Throwable t) {
                Log.e("YandexTranslateApi: ", " " + t.getMessage());
            }
        });
    }
}
