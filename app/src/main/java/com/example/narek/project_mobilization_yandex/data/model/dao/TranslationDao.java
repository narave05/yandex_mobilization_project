package com.example.narek.project_mobilization_yandex.data.model.dao;

import com.example.narek.project_mobilization_yandex.data.model.clean.Dictionary;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TranslationDao extends RealmObject {

    @PrimaryKey
    private String primaryKey;

    private String mLanguagePairCodText;
    private String mOriginalText;
    private Date createdOrUpdatedDate;
    private RealmList<RealmString> mTranslatedTexts;
    private DictionaryDao mDictionary;
    private boolean isFavorite;

    public TranslationDao() {
    }

    public TranslationDao(TranslationDTO translationDTO) {
        primaryKey = translationDTO.getPrimaryKey();
        createdOrUpdatedDate = new Date();
        mLanguagePairCodText = translationDTO.getLanguagePairCodText();
        mOriginalText = translationDTO.getOriginalText();
        mTranslatedTexts = generateTranslatedTexts(translationDTO.getTranslatedTextList());
        mDictionary = translationDTO.getDictionary() != null ? new DictionaryDao(translationDTO.getDictionary()) : null;
        isFavorite = translationDTO.isFavorite();
    }

    private RealmList<RealmString> generateTranslatedTexts(List<String> translatedTexts) {
        if (translatedTexts == null) {
            return null;
        }

        RealmList<RealmString> realmStrings = new RealmList<>();
        for (String text : translatedTexts) {
            realmStrings.add(new RealmString(text));
        }
        return realmStrings;
    }

    public boolean hasDictionary() {
        return mDictionary != null && mDictionary.getOriginalText() != null
                && mDictionary.getDictionaryItemDaos() != null;
    }

    public void setTranslatedTexts(List<String> translatedTexts) {
        mTranslatedTexts = generateTranslatedTexts(translatedTexts);
    }

    public void setDictionary(DictionaryDao dictionary) {
        mDictionary = dictionary;
    }

    public DictionaryDao getDictionary() {
        return mDictionary;
    }

    public List<String> getTranslatedTextList() {
        if (mTranslatedTexts == null) {
            return null;
        }

        List<String> list = new ArrayList<>();
        if (mTranslatedTexts.size() == 0) {
            list.add("");
        }
        for (RealmString realmString : mTranslatedTexts) {
            list.add(realmString.getString());
        }
        return list;
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

    public void setCreatedOrUpdatedDate(Date createdOrUpdatedDate) {
        this.createdOrUpdatedDate = createdOrUpdatedDate;
    }
}
