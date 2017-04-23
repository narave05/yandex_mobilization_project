package com.example.narek.project_mobilization_yandex.util;

import android.content.Context;
import android.util.DisplayMetrics;

public class DimenUtils {
    private DimenUtils() {
        throw new AssertionError("No instances");
    }

    public static   int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
