package com.example.narek.project_mobilization_yandex.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.clean.Example;
import com.example.narek.project_mobilization_yandex.data.model.clean.Synonym;
import com.example.narek.project_mobilization_yandex.data.model.clean.TranslationItem;
import com.example.narek.project_mobilization_yandex.util.DimenUtils;
import com.example.narek.project_mobilization_yandex.util.ViewHelper;

import java.util.List;

@SuppressLint("ViewConstructor")
public class TranslationItemView extends LinearLayout {

    private LinearLayout mNestedLinearLayout;

    public TranslationItemView(Context context, TranslationItem translationItem, boolean isNumbered, int number) {
        super(context);
        setOrientation(VERTICAL);
        init(translationItem, isNumbered, number);
    }

    private void init(TranslationItem translationItem, boolean isNumbered, int number) {

        FrameLayout rootFrameLayout = new FrameLayout(getContext());
        addView(rootFrameLayout);
        mNestedLinearLayout = new LinearLayout(getContext());
        mNestedLinearLayout.setOrientation(VERTICAL);
        rootFrameLayout.addView(mNestedLinearLayout);

        if (isNumbered) {
            generateNumberView(number, rootFrameLayout);
        }

        List<Synonym> synonymList = translationItem.getSynonymList();
        if (synonymList != null) {
            generateSynonymViews(synonymList);
        }

        List<String> meanList = translationItem.getMeanList();
        if (meanList != null) {
            generateMeanViews(meanList);
        }

        List<Example> exampleList = translationItem.getExampleList();
        if (exampleList != null) {
            generateExampleViews(exampleList);
        }
    }

    private void generateNumberView(int number, FrameLayout rootFrameLayout) {
        TextView numberText = new TextView(getContext());
        numberText.setTextColor(ContextCompat.getColor(getContext(), R.color.color4));
        numberText.setText(String.valueOf(number));
        numberText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        int topPadding = DimenUtils.dpToPx(getContext(), 3);
        numberText.setPadding(0, topPadding, 0, 0);
        rootFrameLayout.addView(numberText);
    }

    private void generateSynonymViews(List<Synonym> synonymList) {
        SynonymView synonymView = new SynonymView(getContext());

        int size = synonymList.size();

        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        for (int i = 0; i < size; i++) {

            Synonym synonym = synonymList.get(i);
            String translatedText = synonym.getTranslatedText();
            spannableString.append(translatedText);
            spannableString.append(" ");
            String genText = synonym.getGen();

            if (genText != null) {
                Spannable text = new SpannableString(genText);
                int start = 0;
                int end = genText.length();
                text.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.color4)),
                        start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                text.setSpan(new StyleSpan(Typeface.ITALIC), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.append(text);
            }

            if (i != size - 1) {
                spannableString.append(", ");
            }
        }
        synonymView.setText(spannableString);
        mNestedLinearLayout.addView(synonymView);
        ViewHelper.setViewMargins(synonymView, new int[]{19, 0, 0, 0});
    }

    private void generateMeanViews(List<String> meanList) {

        MeanView meanView = new MeanView(getContext());

        StringBuilder sb = new StringBuilder();
        sb.append("(");
        int size = meanList.size();
        for (int i = 0; i < size; i++) {
            String mean = meanList.get(i);
            sb.append(mean);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        meanView.setText(sb.toString());
        mNestedLinearLayout.addView(meanView);
        ViewHelper.setViewMargins(meanView, new int[]{19, 0, 0, 0});
    }

    private void generateExampleViews(List<Example> exampleList) {
        ExampleView exampleView = new ExampleView(getContext());

        StringBuilder sb = new StringBuilder();
        int size = exampleList.size();
        for (int i = 0; i < size; i++) {

            Example example = exampleList.get(i);
            sb.append(example.getOriginalText()).append(" - ").append(example.getTranslatedTextList().get(0));
            if (i != size - 1) {
                sb.append("\n");
            }
        }
        exampleView.setText(sb.toString());
        mNestedLinearLayout.addView(exampleView);
        ViewHelper.setViewMargins(exampleView, new int[]{40, 0, 0, 0});
    }
}
