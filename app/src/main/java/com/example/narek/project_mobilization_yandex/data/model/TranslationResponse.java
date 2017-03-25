package com.example.narek.project_mobilization_yandex.data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TranslationResponse {

    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("text")
    @Expose
    private List<String> text = null;

    public int getCode() {
        return code;
    }

    public String getLang() {
        return lang;
    }

    public List<String> getText() {
        return text;
    }
}