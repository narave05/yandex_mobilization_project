package com.example.narek.project_mobilization_yandex.data.network_api;

import com.example.narek.project_mobilization_yandex.data.model.DictionaryResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DictionaryRestService {

    @GET("lookup")
    Call<DictionaryResponse> lookup(@Query("key") String key, @Query("text") String text,
                                    @Query("lang") String lang);

}
