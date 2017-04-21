package com.example.narek.project_mobilization_yandex.data.model.dao;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class LanguageDao extends RealmObject {

    @PrimaryKey
    private String code;

    private String fullName;

    public LanguageDao() {
    }

    public LanguageDao(String code, String fullName) {
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
