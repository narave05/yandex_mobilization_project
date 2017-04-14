package com.example.narek.project_mobilization_yandex.data.model.clean;

import com.example.narek.project_mobilization_yandex.data.model.dao.DictionaryDao;
import com.example.narek.project_mobilization_yandex.data.model.dao.DictionaryItemDao;
import com.example.narek.project_mobilization_yandex.data.model.rest.DictionaryResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Dictionary implements Serializable {

    private String mOriginalText;
    private String mTranscription;
    private List<DictionaryItem> mDictionaryItems;

    public Dictionary() {
    }
    public Dictionary(DictionaryDao dictionaryDao) {
        mOriginalText = dictionaryDao.getOriginalText();
        mTranscription = dictionaryDao.getTranscription();
        mDictionaryItems = generateDictionaryItems(dictionaryDao.getDictionaryItemDaos());
    }

    public Dictionary(List<DictionaryResponse.Def> dictionaryResponses) {
        if (dictionaryResponses != null && dictionaryResponses.size() > 0) {
            DictionaryResponse.Def dictionaryResponse = dictionaryResponses.get(0);
            mOriginalText = dictionaryResponse.getText();
            mTranscription = dictionaryResponse.getTranscription();
            mDictionaryItems = generateDictionaryItemList(dictionaryResponses);
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

    private List<DictionaryItem> generateDictionaryItemList(List<DictionaryResponse.Def> list) {
        if (list == null) {
            return null;
        }
        List<DictionaryItem> items = new ArrayList<>();
        for (DictionaryResponse.Def def : list) {
            items.add(new DictionaryItem(def));
        }
        return items;
    }

    private List<DictionaryItem> generateDictionaryItems(List<DictionaryItemDao> list) {
        if (list == null) {
            return null;
        }
        List<DictionaryItem> items = new ArrayList<>();
        for (DictionaryItemDao def : list) {
            items.add(new DictionaryItem(def));
        }
        return items;
    }

    @Override
    public String toString() {
        return "DictionaryDao{" +
                "mOriginalText='" + mOriginalText + '\'' +
                ", mTranscription='" + mTranscription + '\'' +
                ", mDictionaryItems=" + mDictionaryItems +
                '}';
    }
}
