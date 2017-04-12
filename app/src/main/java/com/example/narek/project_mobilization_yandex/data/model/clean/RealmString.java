package com.example.narek.project_mobilization_yandex.data.model.clean;

import org.parceler.Parcel;

import io.realm.RealmObject;
import io.realm.RealmStringRealmProxy;

@Parcel(implementations = {RealmStringRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {RealmString.class})

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

    @Override
    public String toString() {
        return "RealmString{" +
                "mString='" + mString + '\'' +
                '}';
    }
}
