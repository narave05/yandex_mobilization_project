package com.example.narek.project_mobilization_yandex.ui.translate;

import android.content.Intent;

import com.example.narek.project_mobilization_yandex.data.model.clean.Dictionary;
import com.example.narek.project_mobilization_yandex.ui.base_repository.BaseRepositoryContract;


interface TranslationContract {

    interface IView extends BaseRepositoryContract.IView {

        void showTranslation(String text, Dictionary dictionary);

        void showTranslation(String text);

        void resetTranslationView();

        void startLanguageListActivity(int languageListType, String checkedLanguageCod);

        void updateToolbarLanguages(String firstLang, String secondLang);

        void updateInputTranslationText(String text);

        void showFavoriteIcon(boolean isFavorite);

        void hideFavoriteIcon();

    }

    interface IPresenter extends BaseRepositoryContract.IPresenter<IView> {

        void init();

        void handleTextInput(String text);

        void handleCancelInputData();

        void handleLanguageSelectOrSwap(int id);

        void handleFavoriteClick();

        void handleRetryClick();

        void handleOnActivityResult(int requestCode, int resultCode, Intent data);
    }
}
