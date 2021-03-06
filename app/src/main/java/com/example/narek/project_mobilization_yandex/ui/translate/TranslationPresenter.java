package com.example.narek.project_mobilization_yandex.ui.translate;

import android.app.Activity;
import android.content.Intent;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.model.clean.Language;
import com.example.narek.project_mobilization_yandex.data.model.clean.LanguagePair;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDto;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.DeleteAllTranslationsEvent;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.TranslatedEvent;
import com.example.narek.project_mobilization_yandex.ui.base_repository.BaseRepositoryPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static com.example.narek.project_mobilization_yandex.util.constant.Constants.FIRST_LANGUAGE_LIST;
import static com.example.narek.project_mobilization_yandex.util.constant.Constants.LANGUAGE_INTENT_KEY;
import static com.example.narek.project_mobilization_yandex.util.constant.Constants.LANGUAGE_LIST_TYPE_INTENT_KEY;
import static com.example.narek.project_mobilization_yandex.util.constant.Constants.SECOND_LANGUAGE_LIST;

class TranslationPresenter extends BaseRepositoryPresenter<TranslationContract.IView>
        implements TranslationContract.IPresenter {

    private String mLastInputText = null;
    private String mLastTranslatedText = null;
    private LanguagePair mLanguagePair = new LanguagePair();
    private TranslationDto mLastTranslationDto = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void init() {
        if (isViewAttached()) {
            callUpdateToolbarLanguages();
        }
    }

    @Override
    public void onStart() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void handleTextInput(String text) {
        if (text.isEmpty()) {
            clearInputData();
        } else {
            if (text.equals(mLastInputText)) {
                return;
            }
            mLastInputText = text;
            getTranslationData();
        }
    }

    @Override
    public void handleCancelInputData() {
        clearInputData();
    }

    @Override
    public void handleLanguageSelectOrSwap(int id) {
        if (!isViewAttached()) {
            return;
        }
        switch (id) {
            case R.id.switch_icon:
                mLanguagePair.swap();
                mLastInputText = null;
                getView().updateInputTranslationText(mLastTranslatedText);
                callUpdateToolbarLanguages();
                break;
            case R.id.first_lang_id:
                getView().startLanguageListActivity(FIRST_LANGUAGE_LIST, mLanguagePair.getFirstLanguage().getCode());
                break;
            case R.id.second_lang_id:
                getView().startLanguageListActivity(SECOND_LANGUAGE_LIST, mLanguagePair.getSecondLanguage().getCode());
                break;
        }
    }

    @Override
    public void handleFavoriteClick() {
        if (mLastTranslationDto != null) {
            mLastTranslationDto.setFavorite(!mLastTranslationDto.isFavorite());
            getRepository().updateTranslationFavoriteStatusAsync(mLastTranslationDto.getPrimaryKey(), mLastTranslationDto.isFavorite());
            EventBus.getDefault().post(mLastTranslationDto);
        }
    }

    @Override
    public void handleRetryClick() {
        getTranslationData();
    }

    @Override
    public void handleOnActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == TranslationFragment.REQUEST_CODE) {
            if (data != null) {

                Language language = (Language) data.getSerializableExtra(LANGUAGE_INTENT_KEY);
                int languageListType = data.getIntExtra(LANGUAGE_LIST_TYPE_INTENT_KEY, 0);
                boolean isSetSecond = true;
                boolean isSetFirst = true;

                switch (languageListType) {
                    case FIRST_LANGUAGE_LIST:
                        isSetFirst = mLanguagePair.setFirstLanguage(language);
                        swapTranslatedAndInputText(isSetFirst);
                        break;
                    case SECOND_LANGUAGE_LIST:
                        isSetSecond = mLanguagePair.setSecondLanguage(language);
                        swapTranslatedAndInputText(isSetSecond);
                        break;
                }
                callUpdateToolbarLanguages();
                if (isSetSecond && isSetFirst) {
                    getTranslationData();
                }
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadTranslationEvent(TranslatedEvent event) {
        if (event.getError() == null) {
            showTranslation(event.getTranslationDto());
        } else {
            showError(event.getError());
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDeleteTranslationEvent(DeleteAllTranslationsEvent event) {
        clearInputData();
        if (isViewAttached()) {
            getView().updateInputTranslationText(null);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFavoriteStatusChangedEvent(TranslationDto event) {
        if (!isViewAttached() || !event.equals(mLastTranslationDto)) {
            return;
        }
        getView().showFavoriteIcon(event.isFavorite());
    }

    private void clearInputData() {
        mLastInputText = null;
        mLastTranslatedText = null;
        mLastTranslationDto = null;
        if (isViewAttached()) {
            getView().hideFavoriteIcon();
            getView().hideProgress();
            getView().resetTranslationView();
        }
    }

    private void swapTranslatedAndInputText(boolean isSet) {
        if (!isSet) {
            mLastInputText = null;
            getView().updateInputTranslationText(mLastTranslatedText);
        }
    }

    private void callUpdateToolbarLanguages() {
        String firsLang = mLanguagePair.getFirstLanguage().getFullName();
        String secondLang = mLanguagePair.getSecondLanguage().getFullName();
        getView().updateToolbarLanguages(firsLang, secondLang);
    }

    private void getTranslationData() {

        if (mLastInputText == null || mLastInputText.isEmpty()) {
            return;
        }

        if (isViewAttached()) {
            getView().hideFavoriteIcon();
            getView().showProgress();
            getView().resetTranslationView();
        }
        getRepository().findTranslationDataAsync(mLastInputText, mLanguagePair.getLanguagePairCods());
    }

    private void showError(String error) {
        if (isViewAttached()) {
            getView().hideFavoriteIcon();
            getView().hideProgress();
            getView().showError(error);
        }
    }

    private void showTranslation(TranslationDto data) {

        if (data == null) {
            clearInputData();
        }

        if (isViewAttached()) {
            getView().hideProgress();
            getView().showFavoriteIcon(data.isFavorite());
            if (!data.getOriginalText().equalsIgnoreCase(mLastInputText)) {
                return;
            }
            mLastTranslationDto = data;
            mLastTranslatedText = data.getTranslatedTextList().get(0);
            if (data.hasDictionary()) {
                getView().showTranslation(mLastTranslatedText, data.getDictionary());
            } else {
                getView().showTranslation(mLastTranslatedText);
            }
        }
    }
}
