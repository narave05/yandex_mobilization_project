package com.example.narek.project_mobilization_yandex.ui.language_list;

import android.os.Handler;

import com.example.narek.project_mobilization_yandex.data.interfaces.ResultCallback;
import com.example.narek.project_mobilization_yandex.data.model.clean.Language;
import com.example.narek.project_mobilization_yandex.ui.base.base_repository.BaseRepositoryPresenter;

import java.util.List;


class LanguageListPresenter extends BaseRepositoryPresenter<LanguageListContract.IView>
        implements LanguageListContract.IPresenter {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void startLoadData(String checkedLanguageCod) {
        getLanguageList(checkedLanguageCod);
    }

    @Override
    public void handleLanguageSelected(Language language) {
        if (isViewAttached()) {
            getView().returnActivityResultOk(language);
            finishActivityPostDelayed();
        }
    }

    @Override
    public void handleOnBackPressed() {
        if (isViewAttached()) {
            getView().returnActivityResultCancel();
            getView().finishLanguageListActivity();
        }
    }

    private void getLanguageList(final String checkedLanguageCod) {
        getRepository().getAvailableLanguageListInBackground(new ResultCallback<List<Language>>() {
            @Override
            public void onStart() {
                if (isViewAttached()) {
                    getView().showProgress();
                }
            }

            @Override
            public void onResult(List<Language> data) {
                if (isViewAttached()) {
                    getView().hideProgress();
                    int checkedLanguageIndex = getCheckedLanguageIndex(checkedLanguageCod, data);
                    getView().showLanguageList(data, checkedLanguageIndex);
                }
            }

            @Override
            public void onError(Throwable error) {
                if (isViewAttached()) {
                    getView().hideProgress();
                    getView().showError(error.getMessage());
                }
            }
        });
    }

    private int getCheckedLanguageIndex(String checkedLanguageCod, List<Language> languages) {
        int index = -1;
        for (int i = 0; i < languages.size(); i++) {
            if (languages.get(i).getCode().equals(checkedLanguageCod)) {
                index = i;
            }
        }
        return index;
    }

    private void finishActivityPostDelayed() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isViewAttached()) {
                    getView().finishLanguageListActivity();
                }
            }
        }, 200);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
