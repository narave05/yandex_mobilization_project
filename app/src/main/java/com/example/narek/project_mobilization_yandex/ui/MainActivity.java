package com.example.narek.project_mobilization_yandex.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.DictionaryResponse;
import com.example.narek.project_mobilization_yandex.data.YandexDictionaryApi;
import com.example.narek.project_mobilization_yandex.data.YandexTranslateApi;
import com.example.narek.project_mobilization_yandex.data.model.AvailableLanguagesResponse;
import com.google.gson.internal.LinkedTreeMap;

import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        YandexTranslateApi translateApi = new YandexTranslateApi();
        translateApi.getAvailableLanguages().enqueue(new Callback<AvailableLanguagesResponse>() {
            @Override
            public void onResponse(Call<AvailableLanguagesResponse> call, Response<AvailableLanguagesResponse> response) {
                if (response.isSuccessful()) {
                    LinkedTreeMap<String, String> langs = response.body().langs;
                    Set<String> strings = langs.keySet();
                    for (String string : strings) {
                        Log.e("YandexTranslateApi: ", " " + string + " " + langs.get(string));
                    }
                } else {
                    Log.e("YandexTranslateApi: ", " " + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<AvailableLanguagesResponse> call, Throwable t) {
                Log.e("YandexTranslateApi: ", " " + t.getMessage());
            }
        });

        YandexDictionaryApi yandexDictionaryApi = new YandexDictionaryApi();
        yandexDictionaryApi.lookup("all").enqueue(new Callback<DictionaryResponse>() {
            @Override
            public void onResponse(Call<DictionaryResponse> call, Response<DictionaryResponse> response) {
                if (response.isSuccessful()) {
                    Log.e("yandexDictionaryApi: ", " " + response.body());
                } else {
                    Log.e("yandexDictionaryApi: ", " " + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<DictionaryResponse> call, Throwable t) {
                Log.e("yandexDictionaryApi: ", " " + t.getMessage());
            }
        });
    }
}
