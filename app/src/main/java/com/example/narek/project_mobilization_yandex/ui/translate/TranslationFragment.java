package com.example.narek.project_mobilization_yandex.ui.translate;


import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.clean.Dictionary;
import com.example.narek.project_mobilization_yandex.ui.base_repository.BaseRepositoryFragment;
import com.example.narek.project_mobilization_yandex.ui.language_list.LanguageListActivity;
import com.example.narek.project_mobilization_yandex.ui.widget.DictionaryView;
import com.example.narek.project_mobilization_yandex.ui.widget.ErrorLayout;
import com.example.narek.project_mobilization_yandex.ui.widget.LoadingView;
import com.example.narek.project_mobilization_yandex.ui.widget.TranslationInputView;
import com.example.narek.project_mobilization_yandex.util.ViewHelper;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;
import net.yslibrary.android.keyboardvisibilityevent.Unregistrar;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.narek.project_mobilization_yandex.util.constant.Constants.CHECKED_LANGUAGE_INTENT_KEY;
import static com.example.narek.project_mobilization_yandex.util.constant.Constants.LANGUAGE_LIST_TYPE_INTENT_KEY;

public class TranslationFragment extends BaseRepositoryFragment<TranslationContract.IView, TranslationContract.IPresenter>
        implements TranslationContract.IView,
        TranslationInputView.TextChangListener,
        TranslationInputView.OnCancelClick,
        ErrorLayout.OnClickRetry {

    public static final int REQUEST_CODE = 152;

    @BindView(R.id.root_scroll_view)
    NestedScrollView mRootScrollView;

    @BindView(R.id.translated_text)
    TextView mTranslatedText;

    @BindView(R.id.translation_input_container)
    TranslationInputView mTranslationInputContainer;

    @BindView(R.id.loading_view)
    LoadingView mLoadingView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.first_lang_id)
    TextView mFirstLangText;

    @BindView(R.id.second_lang_id)
    TextView mSecondLangText;

    @BindView(R.id.fav_icon)
    AppCompatImageView favoriteIcon;

    ErrorLayout mErrorLayout;

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
        return inflater.inflate(R.layout.fragment_translation, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.init();

        mTranslationInputContainer.setTextChangListener(this);
        mTranslationInputContainer.setOnCancelClick(this);

        registerKeyboardVisibilityEvent();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.handleOnActivityResult(requestCode, resultCode, data);
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
        if (mErrorLayout == null) {
            mErrorLayout = new ErrorLayout(getActivity());
            mErrorLayout.setErrorTextAndHint(getString(R.string.error_connection_text),
                    getString(R.string.error_connection_hint_text));
            mErrorLayout.setOnClickRetry(this);
        }
        mRootScrollView.removeAllViews();
        mRootScrollView.addView(mErrorLayout);
    }

    @Override
    public void showTranslation(String text, Dictionary dictionary) {
        mTranslatedText.setText(text);
        DictionaryView dictionaryView = new DictionaryView(getActivity(), dictionary);
        mRootScrollView.removeAllViews();
        mRootScrollView.addView(dictionaryView);
    }

    @Override
    public void startLanguageListActivity(int languageListType, String checkedLanguageCod) {
        Intent startIntent = LanguageListActivity.getStartIntent(getActivity());
        startIntent.putExtra(LANGUAGE_LIST_TYPE_INTENT_KEY, languageListType);
        startIntent.putExtra(CHECKED_LANGUAGE_INTENT_KEY, checkedLanguageCod);
        startActivityForResult(startIntent, REQUEST_CODE);
    }

    @Override
    public void updateToolbarLanguages(String firstLang, String secondLang) {
        mFirstLangText.setText(firstLang);
        mSecondLangText.setText(secondLang);
    }

    @Override
    public void updateInputTranslationText(String text) {
        mTranslationInputContainer.setTranslationInputText(text);
    }

    @Override
    public void showFavoriteIcon(boolean isFavorite) {
        int color;
        if (isFavorite) {
            color = ContextCompat.getColor(getActivity(), R.color.fav_active_icon_color);
        } else {
            color = ContextCompat.getColor(getActivity(), R.color.fav_inactive_icon_color);
        }
        favoriteIcon.setColorFilter(color, PorterDuff.Mode.SRC_IN);
        favoriteIcon.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideFavoriteIcon() {
        favoriteIcon.setVisibility(View.GONE);
    }

    @Override
    public void showTranslation(String text) {
        mTranslatedText.setText(text);
    }


    @OnClick({R.id.translated_text, R.id.translated_text_scroll, R.id.fragment_container})
    public void onTranslatedViewClicked(View view) {
        mTranslationInputContainer.cancelViewFocus();
        ViewHelper.hideKeyboard(mTranslationInputContainer);
    }

    @OnClick({R.id.switch_icon, R.id.first_lang_id, R.id.second_lang_id})
    public void onViewClicked(View view) {
        presenter.handleLanguageSelectOrSwap(view.getId());
    }

    @OnClick(R.id.fav_icon)
    public void onFavoriteIconClicked(View view) {
        presenter.handleFavoriteClick();
    }

    private void registerKeyboardVisibilityEvent() {
        mUnregistrar = KeyboardVisibilityEvent.registerEventListener(getActivity(), new KeyboardVisibilityEventListener() {
            @Override
            public void onVisibilityChanged(boolean isOpen) {
                if (!isOpen) {
                    mTranslationInputContainer.cancelViewFocus();
                }
            }
        });
    }

    @Override
    public void onClickRetry() {
        presenter.handleRetryClick();
    }
}
