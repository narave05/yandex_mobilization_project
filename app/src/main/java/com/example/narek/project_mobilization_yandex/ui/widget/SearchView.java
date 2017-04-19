package com.example.narek.project_mobilization_yandex.ui.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.util.ViewHelper;

public class SearchView extends FrameLayout implements TextWatcher {

    private EditText mSearchInput;
    private AppCompatImageView mCloseIcon;
    private View mBottomLine;

    public SearchView(Context context) {
        super(context);
        init();
    }


    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        AppCompatImageView searchIcon = (AppCompatImageView) inflater.inflate(R.layout.search_icon_layout, this, false);
        addView(searchIcon);
        mSearchInput = (EditText) inflater.inflate(R.layout.search_input_layout, this, false);
        mSearchInput.addTextChangedListener(this);
        addView(mSearchInput);
        mCloseIcon = (AppCompatImageView) inflater.inflate(R.layout.close_icon_layout, this, false);
        addView(mCloseIcon);
        mBottomLine = inflater.inflate(R.layout.search_bottom_line_view, this, false);
        addView(mBottomLine);

        cancelViewFocus();

        mCloseIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchInput.setText(null);
            }
        });

        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewFocus();
            }
        };
        setOnClickListener(listener);
        mSearchInput.setOnClickListener(listener);

    }

    public void cancelViewFocus() {
        int color = ContextCompat.getColor(getContext(), R.color.language_line_color);
        mBottomLine.setBackgroundColor(color);
        mSearchInput.setCursorVisible(false);
        mSearchInput.clearFocus();
    }

    public void setViewFocus() {
        int color = ContextCompat.getColor(getContext(), R.color.colorPrimary);
        mBottomLine.setBackgroundColor(color);
        mSearchInput.setCursorVisible(true);
        mSearchInput.requestFocus();
        ViewHelper.showKeyboard(mSearchInput);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!s.toString().isEmpty()) {
            mCloseIcon.setVisibility(VISIBLE);

        } else {
            mCloseIcon.setVisibility(GONE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
