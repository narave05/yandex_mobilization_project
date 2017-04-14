package com.example.narek.project_mobilization_yandex.data.model.dao;


import com.example.narek.project_mobilization_yandex.data.model.clean.Synonym;

import io.realm.RealmObject;

public class SynonymDao extends RealmObject {
    private String mTranslatedText;
    private String mGen;

    public SynonymDao() {
    }

    public SynonymDao(Synonym synonym) {
        mTranslatedText = synonym.getTranslatedText();
        mGen = synonym.getGen();
    }

    public SynonymDao(String translatedText, String gen) {
        mTranslatedText = translatedText;
        mGen = gen;
    }

    public String getTranslatedText() {
        return mTranslatedText;
    }

    public String getGen() {
        return mGen;
    }

}
