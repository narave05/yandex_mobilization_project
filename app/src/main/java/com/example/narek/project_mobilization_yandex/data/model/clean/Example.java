package com.example.narek.project_mobilization_yandex.data.model.clean;

import com.example.narek.project_mobilization_yandex.data.model.rest.DictionaryResponse;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.ExampleRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;

@Parcel(implementations = {ExampleRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {Example.class})

public class Example extends RealmObject{
    private String mOriginalText;
    private RealmList<RealmString> mTranslatedTextList;

    public Example() {

    }


    public Example(DictionaryResponse.Ex ex) {
        mOriginalText = ex.getText();
        mTranslatedTextList = generateTranslatedTextItems(ex.getTranslatedTextList());
    }

    private RealmList<RealmString> generateTranslatedTextItems(List<DictionaryResponse.Tr_> list) {

        if (list == null) {
            return null;
        }

        RealmList<RealmString> items = new RealmList<>();
        for (DictionaryResponse.Tr_ tr : list) {
            items.add(new RealmString(tr.getText()));
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

    @Override
    public String toString() {
        return "Example{" +
                "mOriginalText='" + mOriginalText + '\'' +
                ", mTranslatedTextList=" + mTranslatedTextList +
                '}';
    }
}
