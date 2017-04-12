package com.example.narek.project_mobilization_yandex.data.model.clean;

public class LanguagePair {

    private Language mFirstLang;
    private Language mSecondLang;

    public LanguagePair() {
        mFirstLang = new Language("en", "Английский");
        mSecondLang = new Language("ru", "Русский");
    }

    public LanguagePair(Language firstLanguage, Language secondLanguage) {
        mFirstLang = firstLanguage;
        mSecondLang = secondLanguage;
    }

    public void swap() {
        Language temp = mFirstLang;
        mFirstLang = mSecondLang;
        mSecondLang = temp;
    }

    public String getLanguagePairCods() {
        return mFirstLang.getCode() + "-" + mSecondLang.getCode();
    }

    public boolean setFirstLanguage(Language firstLang) {
        if (mSecondLang.equals(firstLang)) {
            swap();
            return false;
        }
        mFirstLang = firstLang;
        return true;
    }

    public boolean setSecondLanguage(Language secondLang) {
        if (mFirstLang.equals(secondLang)) {
            swap();
            return false;
        }
        mSecondLang = secondLang;
        return true;
    }

    public Language getFirstLanguage() {
        return mFirstLang;
    }

    public Language getSecondLanguage() {
        return mSecondLang;
    }
}
