package com.example.narek.project_mobilization_yandex.data.model.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

public class AvailableLanguagesResponse {

    @SerializedName("dirs")
    @Expose
    private List<String> dirs = null;
    @SerializedName("langs")
    @Expose
    private LinkedTreeMap<String,String> langs;

    public LinkedTreeMap<String, String> getLanguages() {
        return langs;
    }
}
