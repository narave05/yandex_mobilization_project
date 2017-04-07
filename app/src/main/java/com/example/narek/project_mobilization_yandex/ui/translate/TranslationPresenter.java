package com.example.narek.project_mobilization_yandex.ui.translate;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import com.example.narek.project_mobilization_yandex.R;
import com.example.narek.project_mobilization_yandex.data.interfaces.ResultCallback;
import com.example.narek.project_mobilization_yandex.data.model.clean.Language;
import com.example.narek.project_mobilization_yandex.data.model.clean.LanguagePair;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.ui.base.base_repository.BaseRepositoryPresenter;

import static com.example.narek.project_mobilization_yandex.util.constant.Constants.FIRST_LANGUAGE_LIST;
import static com.example.narek.project_mobilization_yandex.util.constant.Constants.LANGUAGE_INTENT_KEY;
import static com.example.narek.project_mobilization_yandex.util.constant.Constants.LANGUAGE_LIST_TYPE_INTENT_KEY;
import static com.example.narek.project_mobilization_yandex.util.constant.Constants.SECOND_LANGUAGE_LIST;

class TranslationPresenter extends BaseRepositoryPresenter<TranslationContract.IView>
        implements TranslationContract.IPresenter {

    private String mLastInputText = null;
    private String mLastTranslatedText = null;
    private LanguagePair mLanguagePair = new LanguagePair();

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
    public void handleTextInput(String text) {
        if (text.isEmpty()) {
            clearInputData();
        } else {
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
                mLastInputText = mLastTranslatedText;
                callUpdateToolbarLanguages();
                getView().updateInputTranslationText(mLastInputText);
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

    private void clearInputData() {
        mLastInputText = null;
        mLastTranslatedText = null;
        if (isViewAttached()) {
            getView().hideProgress();
            getView().resetTranslationView();
        }
    }

    public void swapTranslatedAndInputText(boolean isSet) {
        if (!isSet) {
            mLastInputText = mLastTranslatedText;
            getView().updateInputTranslationText(mLastInputText);
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
        getView().resetTranslationView();
        getDataRepository().getTranslationData(mLastInputText, mLanguagePair.getLanguagePairCods(),
                new ResultCallback<TranslationDTO>() {
                    @Override
                    public void onStart() {
                        if (isViewAttached()) {
                            getView().showProgress();
                        }
                    }

                    @Override
                    public void onResult(TranslationDTO data) {
                        if (isViewAttached()) {
                            getView().hideProgress();
                            if (!data.getOriginalText().equalsIgnoreCase(mLastInputText)) {
                                return;
                            }
                            mLastTranslatedText = data.getTranslatedTextList().get(0);
                            if (data.hasDictionary()) {
                                getView().showTranslation(mLastTranslatedText, data.getDictionary());
                            } else {
                                getView().showTranslation(mLastTranslatedText);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.e("onError: ", " " + error.getMessage());
                        if (isViewAttached()) {
                            getView().hideProgress();
                            getView().showError(error.getMessage());
                        }

                    }
                });
    }
}
