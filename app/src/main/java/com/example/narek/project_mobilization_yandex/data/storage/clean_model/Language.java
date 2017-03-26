package com.example.narek.project_mobilization_yandex.data.storage.clean_model;

public class Language {

    private final String code;
    private final String fullName;

    public Language(String code, String fullName) {
        this.code = code;
        this.fullName = fullName;
    }

    public String getCode() {
        return code;
    }

    public String getFullName() {
        return fullName;
    }
}
