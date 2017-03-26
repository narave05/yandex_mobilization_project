package com.example.narek.project_mobilization_yandex.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.narek.project_mobilization_yandex.R;

public class ViewHelper {

    private ViewHelper() {
        //no instance
        throw new RuntimeException("Private constructor cannot be accessed");
    }

    public static ProgressDialog showProgress(ProgressDialog progressDialog, Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);
            if (progressDialog.getWindow() != null) {
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
            progressDialog.show();
            progressDialog.setContentView(R.layout.progress_dialog);
        } else {
            progressDialog.show();
            progressDialog.setContentView(R.layout.progress_dialog);
        }
        return progressDialog;
    }

    public static void hideProgress(ProgressDialog progressDialog) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.hide();
            progressDialog.dismiss();
        }
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
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
        setViewMargins(view, margins, false);
    }

    public static void setViewMargins(View view, int[] margins, boolean inDp) {
        if (!inDp) {
            for (int i = 0; i < margins.length; i++) {
                margins[i] = DimenUtils.dpToPx(view.getContext(), margins[i]);
            }
        }
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams())
                .setMargins(margins[0], margins[1], margins[2], margins[3]);
    }
}
