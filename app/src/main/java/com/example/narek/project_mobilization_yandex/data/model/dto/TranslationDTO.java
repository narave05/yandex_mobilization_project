package com.example.narek.project_mobilization_yandex.data.model.dto;

import com.example.narek.project_mobilization_yandex.data.model.clean.Dictionary;
import com.example.narek.project_mobilization_yandex.data.model.clean.RealmString;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.TranslationDTORealmProxy;
import io.realm.annotations.PrimaryKey;

@Parcel(implementations = {TranslationDTORealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {TranslationDTO.class})
public class TranslationDTO extends RealmObject {

    @PrimaryKey
    private String primaryKey;

    private String mLanguagePairCodText;
    private String mOriginalText;
    private Date createdOrUpdatedDate;
    private RealmList<RealmString> mTranslatedTexts;
    private Dictionary mDictionary;
    private boolean isFavorite;

    public TranslationDTO() {

    }

    public TranslationDTO(String originalText, String languagePairCodText, List<String> translatedTexts, Dictionary dictionary) {
        createdOrUpdatedDate = new Date();
        mOriginalText = originalText;
        mTranslatedTexts = generateTranslatedTexts(translatedTexts);
        mDictionary = dictionary;
        mLanguagePairCodText = languagePairCodText;
        primaryKey = mOriginalText + mLanguagePairCodText;
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
                && mDictionary.getDictionaryItems() != null;
    }

    public void setTranslatedTexts(List<String> translatedTexts) {
        mTranslatedTexts = generateTranslatedTexts(translatedTexts);
    }

    public void setDictionary(Dictionary dictionary) {
        mDictionary = dictionary;
    }

    public Dictionary getDictionary() {
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

    @Override
    public String toString() {
        return "TranslationDTO{" +
                "primaryKey='" + primaryKey + '\'' +
                ", mLanguagePairCodText='" + mLanguagePairCodText + '\'' +
                ", mOriginalText='" + mOriginalText + '\'' +
                ", createdOrUpdatedDate=" + createdOrUpdatedDate +
                ", mTranslatedTexts=" + mTranslatedTexts +
                ", mDictionary=" + mDictionary +
                ", isFavorite=" + isFavorite +
                '}';
    }


}
