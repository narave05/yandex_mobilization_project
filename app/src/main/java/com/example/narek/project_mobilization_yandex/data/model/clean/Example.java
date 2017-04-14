package com.example.narek.project_mobilization_yandex.data.model.clean;

import com.example.narek.project_mobilization_yandex.data.model.dao.ExampleDao;
import com.example.narek.project_mobilization_yandex.data.model.rest.DictionaryResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Example implements Serializable {
    private String mOriginalText;
    private List<String> mTranslatedTextList;

    public Example() {

    }

    public Example(ExampleDao exampleDao) {
        mOriginalText = exampleDao.getOriginalText();
        mTranslatedTextList = exampleDao.getTranslatedTextList();
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

    @Override
    public String toString() {
        return "ExampleDao{" +
                "mOriginalText='" + mOriginalText + '\'' +
                ", mTranslatedTextList=" + mTranslatedTextList +
                '}';
    }
}
