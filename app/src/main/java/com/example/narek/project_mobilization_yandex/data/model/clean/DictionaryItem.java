package com.example.narek.project_mobilization_yandex.data.model.clean;

import com.example.narek.project_mobilization_yandex.data.model.dao.DictionaryDao;
import com.example.narek.project_mobilization_yandex.data.model.dao.DictionaryItemDao;
import com.example.narek.project_mobilization_yandex.data.model.dao.TranslationItemDao;
import com.example.narek.project_mobilization_yandex.data.model.rest.DictionaryResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;


public class DictionaryItem implements Serializable {

    // FIXME: 11.04.2017 primary key chkas
    private String mPartOfSpeech;
    private List<TranslationItem> mItemList;

    public DictionaryItem() {

    }
    public DictionaryItem(DictionaryItemDao dictionaryItemDao) {
        mPartOfSpeech = dictionaryItemDao.getPartOfSpeech();
        mItemList = generateTranslationItemList(dictionaryItemDao.getTranslationList());
    }
    public DictionaryItem(DictionaryResponse.Def dictionaryResponse) {
        mPartOfSpeech = dictionaryResponse.getPartOfSpeech();
        mItemList = generateTranslationItems(dictionaryResponse.getTranslatedList());
    }

    private List<TranslationItem> generateTranslationItems(List<DictionaryResponse.Tr> translatedList) {
        if (translatedList == null) {
            return null;
        }
        List<TranslationItem> items = new ArrayList<>();
        for (DictionaryResponse.Tr tr : translatedList) {
            items.add(new TranslationItem(tr));
        }
        return items;
    }

    private List<TranslationItem> generateTranslationItemList(List<TranslationItemDao> translatedList) {
        if (translatedList == null) {
            return null;
        }
        List<TranslationItem> items = new ArrayList<>();
        for (TranslationItemDao tr : translatedList) {
            items.add(new TranslationItem(tr));
        }
        return items;
    }

    public String getPartOfSpeech() {
        return mPartOfSpeech;
    }

    public List<TranslationItem> getTranslationList() {
        return mItemList;
    }

    @Override
    public String toString() {
        return "DictionaryItemDao{" +
                "mPartOfSpeech='" + mPartOfSpeech + '\'' +
                ", mItemList=" + mItemList +
                '}';
    }
}
