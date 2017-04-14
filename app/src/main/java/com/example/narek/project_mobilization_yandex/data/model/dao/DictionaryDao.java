package com.example.narek.project_mobilization_yandex.data.model.dao;

import com.example.narek.project_mobilization_yandex.data.model.clean.Dictionary;
import com.example.narek.project_mobilization_yandex.data.model.clean.DictionaryItem;
import com.example.narek.project_mobilization_yandex.data.model.rest.DictionaryResponse;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;


public class DictionaryDao extends RealmObject {

    private String mOriginalText;
    private String mTranscription;
    private RealmList<DictionaryItemDao> mDictionaryItemDaos;

    public DictionaryDao() {
    }

    public DictionaryDao(Dictionary dictionary) {
        mOriginalText = dictionary.getOriginalText();
        mTranscription = dictionary.getTranscription();
        mDictionaryItemDaos = generateDictionaryItems(dictionary.getDictionaryItems());
    }

    public String getOriginalText() {
        return mOriginalText;
    }

    public String getTranscription() {
        return mTranscription;
    }

    public List<DictionaryItemDao> getDictionaryItemDaos() {
        return mDictionaryItemDaos;
    }

    private RealmList<DictionaryItemDao> generateDictionaryItems(List<DictionaryItem> list) {
        if (list == null) {
            return null;
        }
        RealmList<DictionaryItemDao> items = new RealmList<>();
        for (DictionaryItem dictionaryItem : list) {
            items.add(new DictionaryItemDao(dictionaryItem));
        }
        return items;
    }
}
