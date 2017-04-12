package com.example.narek.project_mobilization_yandex.data.model.clean;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.data.model.rest.DictionaryResponse;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.List;

import io.realm.DictionaryRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.TranslationDTORealmProxy;


public class Dictionary extends RealmObject{

    private String mOriginalText;
    private String mTranscription;
    private RealmList<DictionaryItem> mDictionaryItems;

    public Dictionary() {
    }

    public Dictionary(List<DictionaryResponse.Def> dictionaryResponses) {
        if (dictionaryResponses != null && dictionaryResponses.size() > 0) {
            DictionaryResponse.Def dictionaryResponse = dictionaryResponses.get(0);
            mOriginalText = dictionaryResponse.getText();
            mTranscription = dictionaryResponse.getTranscription();
            mDictionaryItems = generateDictionaryItems(dictionaryResponses);
        } else {
            mOriginalText = null;
            mTranscription = null;
            mDictionaryItems = null;
        }
    }

    public String getOriginalText() {
        return mOriginalText;
    }

    public String getTranscription() {
        return mTranscription;
    }

    public List<DictionaryItem> getDictionaryItems() {
        return mDictionaryItems;
    }

    private RealmList<DictionaryItem> generateDictionaryItems(List<DictionaryResponse.Def> list) {
        if (list == null) {
            return null;
        }
        RealmList<DictionaryItem> items = new RealmList<>();
        for (DictionaryResponse.Def def : list) {
            items.add(new DictionaryItem(def));
        }
        return items;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "mOriginalText='" + mOriginalText + '\'' +
                ", mTranscription='" + mTranscription + '\'' +
                ", mDictionaryItems=" + mDictionaryItems +
                '}';
    }
}
