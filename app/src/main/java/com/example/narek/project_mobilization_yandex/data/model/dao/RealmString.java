package com.example.narek.project_mobilization_yandex.data.model.dao;

import io.realm.RealmObject;

public class RealmString extends RealmObject {

    private String mString;

    public RealmString() {

    }

    public RealmString(String string) {
        mString = string;
    }

    public void setString(String string) {
        mString = string;
    }

    public String getString() {
        return mString;
    }

}
