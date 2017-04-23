package com.example.narek.project_mobilization_yandex.data.model.dto;

import com.example.narek.project_mobilization_yandex.data.model.clean.Dictionary;
import com.example.narek.project_mobilization_yandex.data.model.dao.TranslationDao;

import java.io.Serializable;
import java.util.List;

public class TranslationDto implements Serializable {


    private String primaryKey;

    private String mLanguagePairCodText;
    private String mOriginalText;
    private List<String> mTranslatedTexts;
    private Dictionary mDictionary;
    private boolean isFavorite;

    public TranslationDto() {

    }

    public TranslationDto(TranslationDao translationDao) {
        primaryKey = translationDao.getPrimaryKey();
        mLanguagePairCodText = translationDao.getLanguagePairCodText();
        mOriginalText = translationDao.getOriginalText();
        mTranslatedTexts = translationDao.getTranslatedTextList();
        mDictionary = translationDao.getDictionary() != null ? new Dictionary(translationDao.getDictionary()) : null;
        isFavorite = translationDao.isFavorite();
    }


    public TranslationDto(String originalText, String languagePairCodText, List<String> translatedTexts, Dictionary dictionary) {
        mOriginalText = originalText;
        mTranslatedTexts = translatedTexts;
        mDictionary = dictionary;
        mLanguagePairCodText = languagePairCodText;
        primaryKey = mOriginalText + mLanguagePairCodText;
    }

    public boolean hasDictionary() {
        return mDictionary != null && mDictionary.getOriginalText() != null
                && mDictionary.getDictionaryItems() != null;
    }

    public void setDictionary(Dictionary dictionary) {
        mDictionary = dictionary;
    }

    public Dictionary getDictionary() {
        return mDictionary;
    }

    public List<String> getTranslatedTextList() {
        return mTranslatedTexts;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public String getOriginalText() {
        return mOriginalText;
    }

    public String getLanguagePairCodText() {
        return mLanguagePairCodText;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (!(obj instanceof TranslationDto)) {
            return false;
        }

        TranslationDto translationDto = ((TranslationDto) obj);
        return primaryKey.equals(translationDto.getPrimaryKey());
    }

    @Override
    public int hashCode() {
        return primaryKey.hashCode() * 31 - 154;
    }

    @Override
    public String toString() {
        return "TranslationDao{" +
                "primaryKey='" + primaryKey + '\'' +
                ", mLanguagePairCodText='" + mLanguagePairCodText + '\'' +
                ", mOriginalText='" + mOriginalText + '\'' +
                ", mTranslatedTexts=" + mTranslatedTexts +
                ", mDictionary=" + mDictionary +
                ", isFavorite=" + isFavorite +
                '}';
    }


}
