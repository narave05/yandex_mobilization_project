package com.example.narek.project_mobilization_yandex.data.model.clean;


public class Synonym {
    private final String mTranslatedText;
    private final String mGen;

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
}
