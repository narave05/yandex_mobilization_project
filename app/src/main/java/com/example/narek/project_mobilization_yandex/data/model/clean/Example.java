package com.example.narek.project_mobilization_yandex.data.model.clean;

import com.example.narek.project_mobilization_yandex.data.model.rest_response.DictionaryResponse;

import java.util.ArrayList;
import java.util.List;

public class Example {
    private final String mOriginalText;
    private final List<String> mTranslatedTextList;

    private Example(String originalText, List<String> translatedTextList) {
        mOriginalText = originalText;
        mTranslatedTextList = translatedTextList;
    }

    public Example(DictionaryResponse.Ex ex) {
        mOriginalText = ex.getText();
        mTranslatedTextList = generateTranslatedTextItems(ex.getTranslatedTextList());
    }

    private List<String> generateTranslatedTextItems(List<DictionaryResponse.Tr_> list) {

        if (list == null) {
            return null;
        }

        List<String> items = new ArrayList<>();
        for (DictionaryResponse.Tr_ tr : list) {
            items.add(tr.getText());
        }

        return items;
    }

    public String getOriginalText() {
        return mOriginalText;
    }

    public List<String> getTranslatedTextList() {
        return mTranslatedTextList;
    }
}
