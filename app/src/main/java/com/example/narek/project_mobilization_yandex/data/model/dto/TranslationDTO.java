package com.example.narek.project_mobilization_yandex.data.model.dto;

import com.example.narek.project_mobilization_yandex.data.model.clean.Dictionary;

import java.util.List;

public class TranslationDTO {

    private final String mOriginalText;
    private final List<String> mTranslatedTexts;
    private final Dictionary mDictionary;

    public TranslationDTO(String originalText, List<String> translatedTexts, Dictionary dictionary) {
        mOriginalText = originalText;
        mTranslatedTexts = translatedTexts;
        mDictionary = dictionary;
    }

    public boolean hasDictionary() {
        return mDictionary.getOriginalText() != null && mDictionary.getTranscription() != null
                && mDictionary.getDictionaryItems() != null;
    }

    public Dictionary getDictionary() {
        return mDictionary;
    }

    public List<String> getTranslatedTextList() {
        return mTranslatedTexts;
    }

    public String getOriginalText() {
        return mOriginalText;
    }
}
