package com.example.narek.project_mobilization_yandex.data.model.clean;


import com.example.narek.project_mobilization_yandex.data.model.dao.SynonymDao;

import java.io.Serializable;

public class Synonym implements Serializable {
    private String mTranslatedText;
    private String mGen;

    public Synonym() {

    }

    public Synonym(SynonymDao synonymDao) {
        mTranslatedText = synonymDao.getTranslatedText();
        mGen = synonymDao.getGen();
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
        return "SynonymDao{" +
                "mTranslatedText='" + mTranslatedText + '\'' +
                ", mGen='" + mGen + '\'' +
                '}';
    }
}
