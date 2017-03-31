package com.example.narek.project_mobilization_yandex.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;


public class LoadingView extends ProgressBar {

    public LoadingView(Context context) {
        super(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void hide() {
        if (isShown() && getVisibility() == VISIBLE) {
            setVisibility(GONE);
        }
    }

    public void show() {
        if (!isShown() && (getVisibility() == GONE || getVisibility() == INVISIBLE)) {
            setVisibility(VISIBLE);
        }
    }
}
