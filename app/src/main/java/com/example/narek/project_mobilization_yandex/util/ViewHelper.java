package com.example.narek.project_mobilization_yandex.util;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.ResultReceiver;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.example.narek.project_mobilization_yandex.R;

import java.lang.reflect.Method;

public class ViewHelper {

    private ViewHelper() {
        //no instance
        throw new RuntimeException("Private constructor cannot be accessed");
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

    private static void setViewMargins(View view, int[] margins, boolean inDp) {
        if (inDp) {
            for (int i = 0; i < margins.length; i++) {
                margins[i] = DimenUtils.dpToPx(view.getContext(), margins[i]);
            }
        }
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams())
                .setMargins(margins[0], margins[1], margins[2], margins[3]);
    }

    public static void createAlertDialog(Activity activity, String dialogMessage,
                                         final DialogPositiveButtonClickListener listener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(dialogMessage);
        builder.setPositiveButton(R.string.ok_text, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                listener.onPositiveButtonClick();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(R.string.cancel_text, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
