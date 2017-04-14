package com.example.narek.project_mobilization_yandex.data.model.dao;

import com.example.narek.project_mobilization_yandex.data.model.clean.Example;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class ExampleDao extends RealmObject {

    private String mOriginalText;
    private RealmList<RealmString> mTranslatedTextList;

    public ExampleDao() {
    }

    public ExampleDao(Example example) {
        mOriginalText = example.getOriginalText();
        mTranslatedTextList = generateTranslatedTextItems(example.getTranslatedTextList());
    }

    private RealmList<RealmString> generateTranslatedTextItems(List<String> stringList) {

        if (stringList == null) {
            return null;
        }

        RealmList<RealmString> items = new RealmList<>();
        for (String tr : stringList) {
            items.add(new RealmString(tr));
        }

        return items;
    }

    public String getOriginalText() {
        return mOriginalText;
    }

    public List<String> getTranslatedTextList() {
        if (mTranslatedTextList == null) {
            return null;
        }

        List<String> list = new ArrayList<>();
        for (RealmString realmString : mTranslatedTextList) {
            list.add(realmString.getString());
        }

        return list;
    }
}
