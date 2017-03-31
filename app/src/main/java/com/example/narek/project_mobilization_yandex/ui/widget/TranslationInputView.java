package com.example.narek.project_mobilization_yandex.ui.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.util.ViewHelper;

import java.util.Timer;
import java.util.TimerTask;


public class TranslationInputView extends RelativeLayout implements TextWatcher {

    private AppCompatImageView mImageView;
    private EditText mTranslationInputText;

    private Timer mTimer = new Timer();

    private TextChangListener mTextChangListener;
    private OnCancelClick mOnCancelClick;


    public TranslationInputView(@NonNull Context context) {
        super(context);
        init();
    }

    public TranslationInputView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        createTranslationInputText();
        createImageView();
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewFocus();
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(final CharSequence s, int start, int before, int count) {
        if (!s.toString().isEmpty()) {
            mImageView.setVisibility(VISIBLE);

        } else {
            mImageView.setVisibility(GONE);
        }
        scheduleTextChang(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void createImageView() {
        mImageView = new AppCompatImageView(getContext());
        LayoutParams paramsImg = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        paramsImg.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        paramsImg.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        mImageView.setLayoutParams(paramsImg);
        mImageView.setImageResource(R.drawable.close);
        mImageView.setVisibility(GONE);
        mImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTranslationInputText.setText(null);
                mImageView.setVisibility(GONE);
                if (mOnCancelClick != null) {
                    mOnCancelClick.onCancelClick();
                }
            }
        });
        addView(mImageView);
        ViewHelper.setViewMargins(mImageView, 4);
    }

    private void createTranslationInputText() {
        mTranslationInputText = new EditText(getContext());
        LayoutParams paramsText = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        mTranslationInputText.setLayoutParams(paramsText);
        mTranslationInputText.setBackground(null);
        mTranslationInputText.setTextColor(ContextCompat.getColor(getContext(), R.color.input_text_color));
        mTranslationInputText.setHintTextColor(ContextCompat.getColor(getContext(), R.color.color5));
        mTranslationInputText.setHint("Введите текст");
        mTranslationInputText.setSingleLine(false);
        mTranslationInputText.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
        cancelViewFocus();
        mTranslationInputText.addTextChangedListener(this);
        mTranslationInputText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewFocus();
            }
        });
        addView(mTranslationInputText);
        ViewHelper.setViewMargins(mTranslationInputText, new int[]{4, 4, 32, 32});
    }

    public void setTextChangListener(TextChangListener textChangListener) {
        mTextChangListener = textChangListener;
    }

    public void setOnCancelClick(OnCancelClick onCancelClick) {
        mOnCancelClick = onCancelClick;
    }

    private void scheduleTextChang(final CharSequence charSequence) {
        mTimer.cancel();
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                post(new Runnable() {
                    @Override
                    public void run() {
                        if (mTextChangListener != null) {
                            mTextChangListener.onTextChanged(charSequence.toString());
                        }
                    }
                });
            }
        }, 500);
    }

    public void setViewFocus() {
        setBackground(ContextCompat.getDrawable(getContext(), R.drawable.translation_input_active_bg));
        mTranslationInputText.setCursorVisible(true);
        mTranslationInputText.requestFocus();
        ViewHelper.showKeyboard(mTranslationInputText);
    }

    public void cancelViewFocus() {
        setBackground(ContextCompat.getDrawable(getContext(), R.drawable.translation_input_bg));
        mTranslationInputText.setCursorVisible(false);
        mTranslationInputText.clearFocus();
    }

    public EditText getTranslationInputText() {
        return mTranslationInputText;
    }

    public interface TextChangListener {
        void onTextChanged(String text);
    }

    public interface OnCancelClick {
        void onCancelClick();
    }


}
