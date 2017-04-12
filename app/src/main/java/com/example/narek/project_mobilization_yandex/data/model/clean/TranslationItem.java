package com.example.narek.project_mobilization_yandex.data.model.clean;

import com.example.narek.project_mobilization_yandex.data.model.rest.DictionaryResponse;

import org.parceler.Parcel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.TranslationItemRealmProxy;

@Parcel(implementations = {TranslationItemRealmProxy.class},
        value = Parcel.Serialization.BEAN,
        analyze = {TranslationItem.class})
public class TranslationItem extends RealmObject {


    private RealmList<Synonym> mSynonymList;
    private RealmList<RealmString> mMeanList;
    private RealmList<Example> mExampleList;


    public TranslationItem() {

    }

    public TranslationItem(DictionaryResponse.Tr tr) {
        mSynonymList = generateSynonymItems(tr.getText(), tr.getGen(), tr.getSynonymList());
        mMeanList = generateMeanItems(tr.getMeanList());
        mExampleList = generateExampleItems(tr.getExampleList());
    }

    private RealmList<Synonym> generateSynonymItems(String translatedText, String gen,
                                                    List<DictionaryResponse.Syn> synonymList) {
        RealmList<Synonym> items = new RealmList<>();
        items.add(new Synonym(translatedText, gen));

        if (synonymList != null) {
            for (DictionaryResponse.Syn syn : synonymList) {
                items.add(new Synonym(syn.getText(), syn.getGen()));
            }
        }

        return items;
    }

    private RealmList<RealmString> generateMeanItems(List<DictionaryResponse.Mean> meanList) {

        if (meanList == null) {
            return null;
        }

        RealmList<RealmString> items = new RealmList<>();
        for (DictionaryResponse.Mean mean : meanList) {
            items.add(new RealmString(mean.getText()));
        }

        return items;
    }

    private RealmList<Example> generateExampleItems(List<DictionaryResponse.Ex> exampleList) {


        if (exampleList == null) {
            return null;
        }

        RealmList<Example> items = new RealmList<>();
        for (DictionaryResponse.Ex ex : exampleList) {
            items.add(new Example(ex));
        }

        return items;
    }

    public List<Synonym> getSynonymList() {
        return mSynonymList;
    }

    public List<String> getMeanList() {

        if (mMeanList == null) {
            return null;
        }

        List<String> list = new ArrayList<>();
        for (RealmString realmString : mMeanList) {
            list.add(realmString.getString());
        }

        return list;
    }

    public List<Example> getExampleList() {
        return mExampleList;
    }

    @Override
    public String toString() {
        return "TranslationItem{" +
                "mSynonymList=" + mSynonymList +
                ", mMeanList=" + mMeanList +
                ", mExampleList=" + mExampleList +
                '}';
    }
}
