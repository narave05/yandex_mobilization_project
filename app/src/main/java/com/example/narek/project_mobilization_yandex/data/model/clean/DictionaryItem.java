package com.example.narek.project_mobilization_yandex.data.model.clean;

import com.example.narek.project_mobilization_yandex.data.model.rest_response.DictionaryResponse;

import java.util.ArrayList;
import java.util.List;

public class DictionaryItem {
    private final String mPartOfSpeech;
    private final List<TranslationItem> mItemList;

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

    public String getPartOfSpeech() {
        return mPartOfSpeech;
    }

    public List<TranslationItem> getTranslationList() {
        return mItemList;
    }
}
