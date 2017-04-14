package com.example.narek.project_mobilization_yandex.data.model.clean;

import com.example.narek.project_mobilization_yandex.data.model.dao.ExampleDao;
import com.example.narek.project_mobilization_yandex.data.model.dao.SynonymDao;
import com.example.narek.project_mobilization_yandex.data.model.dao.TranslationItemDao;
import com.example.narek.project_mobilization_yandex.data.model.rest.DictionaryResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TranslationItem implements Serializable {


    private List<Synonym> mSynonymList;
    private List<String> mMeanList;
    private List<Example> mExampleList;


    public TranslationItem() {

    }

    public TranslationItem(TranslationItemDao translationItemDao) {
        mSynonymList = generateSynonymItemList(translationItemDao.getSynonymDaoList());
        mMeanList = translationItemDao.getMeanList();
        mExampleList = generateExampleItemList(translationItemDao.getExampleDaoList());
    }

    public TranslationItem(DictionaryResponse.Tr tr) {
        mSynonymList = generateSynonymItems(tr.getText(), tr.getGen(), tr.getSynonymList());
        mMeanList = generateMeanItems(tr.getMeanList());
        mExampleList = generateExampleItems(tr.getExampleList());
    }

    private List<Synonym> generateSynonymItems(String translatedText, String gen,
                                               List<DictionaryResponse.Syn> synonymList) {
        List<Synonym> items = new ArrayList<>();
        items.add(new Synonym(translatedText, gen));

        if (synonymList != null) {
            for (DictionaryResponse.Syn syn : synonymList) {
                items.add(new Synonym(syn.getText(), syn.getGen()));
            }
        }

        return items;
    }

    private List<Synonym> generateSynonymItemList(List<SynonymDao> synonymList) {

        if (synonymList == null) {
            return null;
        }
        List<Synonym> items = new ArrayList<>();

        for (SynonymDao syn : synonymList) {
            items.add(new Synonym(syn));
        }

        return items;
    }

    private List<String> generateMeanItems(List<DictionaryResponse.Mean> meanList) {

        if (meanList == null) {
            return null;
        }

        List<String> items = new ArrayList<>();
        for (DictionaryResponse.Mean mean : meanList) {
            items.add(mean.getText());
        }

        return items;
    }

    private List<Example> generateExampleItems(List<DictionaryResponse.Ex> exampleList) {


        if (exampleList == null) {
            return null;
        }

        List<Example> items = new ArrayList<>();
        for (DictionaryResponse.Ex ex : exampleList) {
            items.add(new Example(ex));
        }

        return items;
    }

    private List<Example> generateExampleItemList(List<ExampleDao> exampleList) {


        if (exampleList == null) {
            return null;
        }

        List<Example> items = new ArrayList<>();
        for (ExampleDao ex : exampleList) {
            items.add(new Example(ex));
        }

        return items;
    }

    public List<Synonym> getSynonymList() {
        return mSynonymList;
    }

    public List<String> getMeanList() {
        return mMeanList;
    }

    public List<Example> getExampleList() {
        return mExampleList;
    }

    @Override
    public String toString() {
        return "TranslationItemDao{" +
                "mSynonymList=" + mSynonymList +
                ", mMeanList=" + mMeanList +
                ", mExampleList=" + mExampleList +
                '}';
    }
}
