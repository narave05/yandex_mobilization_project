package com.example.narek.project_mobilization_yandex.data.model.rest;

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

    public String getLanguage() {
        return lang;
    }

    public List<String> getTextList() {
        return text;
    }

    @Override
    public String toString() {
        return "TranslationResponse{" +
                "code=" + code +
                ", lang='" + lang + '\'' +
                ", text=" + text +
                '}';
    }
}