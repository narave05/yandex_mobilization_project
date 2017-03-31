package com.example.narek.project_mobilization_yandex.ui.translate;

import android.util.Log;

import com.example.narek.project_mobilization_yandex.data.interfaces.ResultCallback;
import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;
import com.example.narek.project_mobilization_yandex.ui.base.BaseRepositoryPresenter;

import retrofit2.Call;


class TranslationPresenter extends BaseRepositoryPresenter<TranslationContract.IView>
        implements TranslationContract.IPresenter {

    private String lastInputText = null;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void handleTextInput(String text) {
        if (text.isEmpty()) {
            handleCancelInputData();
        } else {
            getData(text);
        }

    }

    @Override
    public void handleCancelInputData() {
        lastInputText = null;
        if (isViewAttached()) {
            getView().hideProgress();
            getView().resetTranslationView();
        }
    }

    private void getData(String text) {

        lastInputText = text;
        getDataRepository().getTranslationData(text, new ResultCallback<TranslationDTO>() {
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
                    getView().resetTranslationView();
                    if (!data.getOriginalText().equalsIgnoreCase(lastInputText)) {
                        return;
                    }
                    if (data.hasDictionary()) {
                        getView().showTranslation(data.getTranslatedTextList().get(0), data.getDictionary());
                    } else {
                        getView().showTranslation(data.getTranslatedTextList().get(0));
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
