package com.example.narek.project_mobilization_yandex.ui.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.example.narek.project_mobilization_yandex.R;

public class SynonymView extends android.support.v7.widget.AppCompatTextView {
    public SynonymView(Context context) {
        super(context);
    }

    public SynonymView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        setTextColor(ContextCompat.getColor(getContext(), R.color.blue));
    }
}
