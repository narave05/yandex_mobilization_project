package com.example.narek.project_mobilization_yandex.data.model.dao;

import com.example.narek.project_mobilization_yandex.data.model.clean.Example;
import com.example.narek.project_mobilization_yandex.data.model.clean.Synonym;
import com.example.narek.project_mobilization_yandex.data.model.clean.TranslationItem;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

public class TranslationItemDao extends RealmObject {


    private RealmList<SynonymDao> mSynonymDaoList;
    private RealmList<RealmString> mMeanList;
    private RealmList<ExampleDao> mExampleDaoList;


    public TranslationItemDao() {

    }

    public TranslationItemDao(TranslationItem translationItem) {
        mSynonymDaoList = generateSynonymItems(translationItem.getSynonymList());
        mMeanList = generateMeanItems(translationItem.getMeanList());
        mExampleDaoList = generateExampleItems(translationItem.getExampleList());
    }

    private RealmList<SynonymDao> generateSynonymItems(List<Synonym> synonyms) {
        if (synonyms == null) {
            return null;
        }
        RealmList<SynonymDao> items = new RealmList<>();
        for (Synonym synonym : synonyms) {
            items.add(new SynonymDao(synonym));
        }

        return items;
    }

    private RealmList<RealmString> generateMeanItems(List<String> meanList) {

        if (meanList == null) {
            return null;
        }

        RealmList<RealmString> items = new RealmList<>();
        for (String mean : meanList) {
            items.add(new RealmString(mean));
        }

        return items;
    }

    private RealmList<ExampleDao> generateExampleItems(List<Example> exampleList) {


        if (exampleList == null) {
            return null;
        }

        RealmList<ExampleDao> items = new RealmList<>();
        for (Example ex : exampleList) {
            items.add(new ExampleDao(ex));
        }

        return items;
    }

    public List<SynonymDao> getSynonymDaoList() {
        return mSynonymDaoList;
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

    public List<ExampleDao> getExampleDaoList() {
        return mExampleDaoList;
    }
}
