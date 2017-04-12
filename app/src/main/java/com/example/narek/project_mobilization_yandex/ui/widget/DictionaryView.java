package com.example.narek.project_mobilization_yandex.ui.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.clean.Dictionary;
import com.example.narek.project_mobilization_yandex.data.model.clean.DictionaryItem;
import com.example.narek.project_mobilization_yandex.util.DimenUtils;
import com.example.narek.project_mobilization_yandex.util.ViewHelper;

import java.util.List;

@SuppressLint("ViewConstructor")
public class DictionaryView extends LinearLayout {

    public DictionaryView(Context context, Dictionary dictionary) {
        super(context);
        setOrientation(VERTICAL);
        int paddingDp = DimenUtils.dpToPx(getContext(), 10);
        setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
        init(dictionary);
    }

    private void init(Dictionary dictionary) {

        TextView originalTextView = new TextView(getContext());
        originalTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        originalTextView.setTextColor(Color.BLACK);

        SpannableStringBuilder headerTextBuilder = new SpannableStringBuilder();
        headerTextBuilder.append(dictionary.getOriginalText());
        headerTextBuilder.append(" ");
        String transcription = dictionary.getTranscription();
        if (transcription != null && !transcription.isEmpty()) {
            transcription = "[" + transcription + "]";
            Spannable spannableTranscription = new SpannableString(transcription);
            spannableTranscription.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.color4)),
                    0, transcription.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            headerTextBuilder.append(spannableTranscription);
        }
        originalTextView.setText(headerTextBuilder);
        addView(originalTextView);

        List<DictionaryItem> dictionaryItems = dictionary.getDictionaryItems();
        for (DictionaryItem dictionaryItem : dictionaryItems) {
            DictionaryItemView dictionaryItemView = new DictionaryItemView(getContext(), dictionaryItem);
            addView(dictionaryItemView);
            ViewHelper.setViewMargins(dictionaryItemView, new int[]{0, 8, 0, 0});
        }
    }
}
