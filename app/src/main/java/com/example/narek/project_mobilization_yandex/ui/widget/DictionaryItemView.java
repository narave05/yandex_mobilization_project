package com.example.narek.project_mobilization_yandex.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.clean.DictionaryItem;
import com.example.narek.project_mobilization_yandex.data.model.clean.TranslationItem;

import java.util.List;

@SuppressLint("ViewConstructor")
public class DictionaryItemView extends LinearLayout {

    public DictionaryItemView(Context context, DictionaryItem dictionaryItem) {
        super(context);
        setOrientation(VERTICAL);
        init(dictionaryItem);
    }

    private void init(DictionaryItem dictionaryItem) {

        generatePartOfSpeechView(dictionaryItem);

        List<TranslationItem> translationList = dictionaryItem.getTranslationList();
        if (translationList != null) {
            boolean isNumbered = translationList.size() > 1;
            for (int i = 0; i < translationList.size(); i++) {
                generateTranslationItemView(translationList.get(i), isNumbered, i + 1);
            }
        }
    }

    private void generatePartOfSpeechView(DictionaryItem dictionaryItem) {
        TextView partOfSpeechText = new TextView(getContext());
        partOfSpeechText.setText(dictionaryItem.getPartOfSpeech());
        partOfSpeechText.setTypeface(null, Typeface.ITALIC);
        partOfSpeechText.setTextColor(ContextCompat.getColor(getContext(), R.color.light_brown));
        partOfSpeechText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        addView(partOfSpeechText);
    }

    private void generateTranslationItemView(TranslationItem translationItem,
                                             boolean isNumbered, int number) {
        TranslationItemView translationItemView = new TranslationItemView(getContext(),
                translationItem, isNumbered, number);
        addView(translationItemView);
    }

}
