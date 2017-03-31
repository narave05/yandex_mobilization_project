package com.example.narek.project_mobilization_yandex.data.model.clean;

import com.example.narek.project_mobilization_yandex.data.model.rest_response.DictionaryResponse;

import java.util.ArrayList;
import java.util.List;

public class Dictionary {

    private final String mOriginalText;
    private final String mTranscription;
    private final List<DictionaryItem> mDictionaryItems;

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

    private List<DictionaryItem> generateDictionaryItems(List<DictionaryResponse.Def> list) {
        if (list == null) {
            return null;
        }
        List<DictionaryItem> items = new ArrayList<>();
        for (DictionaryResponse.Def def : list) {
            items.add(new DictionaryItem(def));
        }
        return items;
    }


}
