package com.example.narek.project_mobilization_yandex.data.model.rest_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

public class AvailableLanguagesResponse {

    @SerializedName("dirs")
    @Expose
    public List<String> dirs = null;
    @SerializedName("langs")
    @Expose
    public LinkedTreeMap<String,String> langs;
}
