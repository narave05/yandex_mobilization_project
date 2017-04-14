package com.example.narek.project_mobilization_yandex.data.model.dao;

import com.example.narek.project_mobilization_yandex.data.model.clean.DictionaryItem;
import com.example.narek.project_mobilization_yandex.data.model.clean.TranslationItem;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class DictionaryItemDao extends RealmObject {

    // FIXME: 11.04.2017 primary key chkas
    private String mPartOfSpeech;
    private RealmList<TranslationItemDao> mItemList;

    public DictionaryItemDao() {
    }

    public DictionaryItemDao(DictionaryItem dictionaryItemDao) {
        mPartOfSpeech = dictionaryItemDao.getPartOfSpeech();
        mItemList = generateTranslationItems(dictionaryItemDao.getTranslationList());
    }

    private RealmList<TranslationItemDao> generateTranslationItems(List<TranslationItem> translatedList) {
        if (translatedList == null) {
            return null;
        }
        RealmList<TranslationItemDao> items = new RealmList<>();
        for (TranslationItem tr : translatedList) {
            items.add(new TranslationItemDao(tr));
        }
        return items;
    }

    public String getPartOfSpeech() {
        return mPartOfSpeech;
    }

    public List<TranslationItemDao> getTranslationList() {
        return mItemList;
    }
}
