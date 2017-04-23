package com.example.narek.project_mobilization_yandex.ui.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.narek.project_mobilization_yandex.R;


public class ErrorLayout extends FrameLayout {

    private TextView mErrorText;
    private TextView mErrorHint;

    OnClickRetry mOnClickRetry;

    public ErrorLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public ErrorLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.error_layout, this, false);
        addView(linearLayout);
        mErrorText = (TextView) linearLayout.findViewById(R.id.error_text);
        mErrorHint = (TextView) linearLayout.findViewById(R.id.error_hint);
        Button buttonRecreate = (Button) linearLayout.findViewById(R.id.button_retry);
        buttonRecreate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickRetry != null) {
                    mOnClickRetry.onClickRetry();
                }
            }
        });
    }

    public void setOnClickRetry(OnClickRetry onClickRetry) {
        mOnClickRetry = onClickRetry;
    }

    public interface OnClickRetry {
        void onClickRetry();
    }

    public void setErrorTextAndHint(String errorText, String errorHint) {
        mErrorText.setText(errorText);
        mErrorHint.setText(errorHint);
    }
}
