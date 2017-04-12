package com.example.narek.project_mobilization_yandex.data.model.clean;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.data.model.rest.DictionaryResponse;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.DictionaryItemRealmProxy;
import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.TranslationDTORealmProxy;

@Parcel(implementations = {DictionaryItemRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {DictionaryItem.class})

public class DictionaryItem extends RealmObject {

    // FIXME: 11.04.2017 primary key chkas
    private  String mPartOfSpeech;
    private RealmList<TranslationItem> mItemList;

    public DictionaryItem() {

    }

    public DictionaryItem(DictionaryResponse.Def dictionaryResponse) {
        mPartOfSpeech = dictionaryResponse.getPartOfSpeech();
        mItemList = generateTranslationItems(dictionaryResponse.getTranslatedList());
    }

    private RealmList<TranslationItem> generateTranslationItems(List<DictionaryResponse.Tr> translatedList) {
        if (translatedList == null) {
            return null;
        }
        RealmList<TranslationItem> items = new RealmList<>();
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

    @Override
    public String toString() {
        return "DictionaryItem{" +
                "mPartOfSpeech='" + mPartOfSpeech + '\'' +
                ", mItemList=" + mItemList +
                '}';
    }
}
