package com.example.narek.project_mobilization_yandex.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.narek.project_mobilization_yandex.R;

import java.lang.reflect.Method;

public class ViewHelper {

    private ViewHelper() {
        //no instance
        throw new RuntimeException("Private constructor cannot be accessed");
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showKeyboard(@NonNull View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService
                (Context.INPUT_METHOD_SERVICE);
        // the public methods don't seem to work for me, so… reflection.
        try {
            Method showSoftInputUnchecked = InputMethodManager.class.getMethod(
                    "showSoftInputUnchecked", int.class, ResultReceiver.class);
            showSoftInputUnchecked.setAccessible(true);
            showSoftInputUnchecked.invoke(imm, 0, null);
        } catch (Exception e) {
            // ho hum
        }
    }

    public static void hideKeyboard(@NonNull View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context
                .INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void setViewMargins(View view, int dp) {
        int px = DimenUtils.dpToPx(view.getContext(), dp);
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).setMargins(px, px, px, px);
    }

    /*
   left
   top
   right
   bottom*/
    public static void setViewMargins(View view, int[] margins) {
        setViewMargins(view, margins, true);
    }

    public static void setViewMargins(View view, int[] margins, boolean inDp) {
        if (inDp) {
            for (int i = 0; i < margins.length; i++) {
                margins[i] = DimenUtils.dpToPx(view.getContext(), margins[i]);
            }
        }
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams())
                .setMargins(margins[0], margins[1], margins[2], margins[3]);
    }
}
