package com.example.narek.project_mobilization_yandex.ui.translate;

import com.example.narek.project_mobilization_yandex.data.model.clean.Dictionary;
import com.example.narek.project_mobilization_yandex.ui.base.BaseContract;


interface TranslationContract {

    interface IView extends BaseContract.IView {

        void showProgress();

        void hideProgress();

        void showError(String error);

        void showTranslation(String text, Dictionary dictionary);

        void showTranslation(String text);

        void resetTranslationView();

    }

    interface IPresenter extends BaseContract.IPresenter<IView> {

        void handleTextInput(String text);

        void handleCancelInputData();
    }
}
