package com.example.narek.project_mobilization_yandex.ui.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

import com.example.narek.project_mobilization_yandex.R;

public class ExampleView extends android.support.v7.widget.AppCompatTextView {

    public ExampleView(Context context) {
        super(context);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setTypeface(null, Typeface.ITALIC);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        setTextColor(ContextCompat.getColor(getContext(), R.color.blue));
    }
}
