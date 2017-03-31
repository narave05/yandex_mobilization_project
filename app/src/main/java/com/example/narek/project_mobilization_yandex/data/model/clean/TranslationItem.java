package com.example.narek.project_mobilization_yandex.data.model.clean;

import com.example.narek.project_mobilization_yandex.data.model.rest_response.DictionaryResponse;

import java.util.ArrayList;
import java.util.List;

public class TranslationItem {


    private final List<Synonym> mSynonymList;
    private final List<String> mMeanList;
    private final List<Example> mExampleList;

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

    public List<Synonym> getSynonymList() {
        return mSynonymList;
    }

    public List<String> getMeanList() {
        return mMeanList;
    }

    public List<Example> getExampleList() {
        return mExampleList;
    }
}
