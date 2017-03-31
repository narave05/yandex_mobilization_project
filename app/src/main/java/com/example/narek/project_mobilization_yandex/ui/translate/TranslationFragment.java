package com.example.narek.project_mobilization_yandex.ui.translate;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.clean.Dictionary;
import com.example.narek.project_mobilization_yandex.ui.base.BaseFragment;
import com.example.narek.project_mobilization_yandex.ui.widget.DictionaryView;
import com.example.narek.project_mobilization_yandex.ui.widget.LoadingView;
import com.example.narek.project_mobilization_yandex.ui.widget.TranslationInputView;
import com.example.narek.project_mobilization_yandex.util.ViewHelper;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;
import net.yslibrary.android.keyboardvisibilityevent.Unregistrar;

import butterknife.BindView;
import butterknife.OnClick;

public class TranslationFragment extends BaseFragment<TranslationContract.IView, TranslationContract.IPresenter>
        implements TranslationContract.IView,
        TranslationInputView.TextChangListener,
        TranslationInputView.OnCancelClick {

    @BindView(R.id.root_scroll_view)
    NestedScrollView mRootScrollView;

    @BindView(R.id.translated_text)
    TextView mTranslatedText;

    @BindView(R.id.translation_input_container)
    TranslationInputView mTranslationInputContainer;

    @BindView(R.id.loading_view)
    LoadingView mLoadingView;

    Unregistrar mUnregistrar;


    public static TranslationFragment newInstance() {
        TranslationFragment fragment = new TranslationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_translation, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTranslationInputContainer.setTextChangListener(this);
        mTranslationInputContainer.setOnCancelClick(this);

        mUnregistrar = KeyboardVisibilityEvent.registerEventListener(getActivity(), new KeyboardVisibilityEventListener() {
            @Override
            public void onVisibilityChanged(boolean isOpen) {
                if (!isOpen) {
                    mTranslationInputContainer.cancelViewFocus();
                }
            }
        });

    }

    @NonNull
    @Override
    public TranslationContract.IPresenter createPresenter() {
        return new TranslationPresenter();
    }

    @Override
    protected boolean whitButterKnife() {
        return true;
    }


    @Override
    public void onTextChanged(String text) {
        presenter.handleTextInput(text);

    }

    @Override
    public void onCancelClick() {
        presenter.handleCancelInputData();
    }

    @Override
    public void resetTranslationView() {
        mRootScrollView.removeAllViews();
        mTranslatedText.setText(null);
    }


    @Override
    public void showProgress() {
        mLoadingView.show();
    }

    @Override
    public void hideProgress() {
        mLoadingView.hide();
    }

    @Override
    public void showError(String error) {
        if (getActivity() != null) {
            ViewHelper.showToast(getActivity(), error);
        }
    }

    @Override
    public void showTranslation(String text, Dictionary dictionary) {
        mTranslatedText.setText(text);
        DictionaryView dictionaryView = new DictionaryView(getActivity(), dictionary);
        mRootScrollView.addView(dictionaryView);
    }

    @Override
    public void showTranslation(String text) {
        mTranslatedText.setText(text);
    }


    @OnClick({R.id.translated_text, R.id.translated_text_scroll, R.id.fragment_container})
    public void onViewClicked(View view) {
        mTranslationInputContainer.cancelViewFocus();
        ViewHelper.hideKeyboard(mTranslationInputContainer);
    }
}
