package com.example.narek.project_mobilization_yandex.data.model.clean;


import org.parceler.Parcel;

import io.realm.RealmObject;
import io.realm.SynonymRealmProxy;

@Parcel(implementations = {SynonymRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {Synonym.class})
public class Synonym extends RealmObject {
    private String mTranslatedText;
    private String mGen;

    public Synonym() {

    }

    public Synonym(String translatedText, String gen) {
        mTranslatedText = translatedText;
        mGen = gen;
    }

    public String getTranslatedText() {
        return mTranslatedText;
    }

    public String getGen() {
        return mGen;
    }

    @Override
    public String toString() {
        return "Synonym{" +
                "mTranslatedText='" + mTranslatedText + '\'' +
                ", mGen='" + mGen + '\'' +
                '}';
    }
}
