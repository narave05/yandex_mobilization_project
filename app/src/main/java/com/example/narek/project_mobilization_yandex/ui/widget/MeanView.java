package com.example.narek.project_mobilization_yandex.ui.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

import com.example.narek.project_mobilization_yandex.R;

class MeanView extends android.support.v7.widget.AppCompatTextView{
    public MeanView(Context context) {
        super(context);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
        setTextColor(ContextCompat.getColor(getContext(), R.color.color2));
    }
}
