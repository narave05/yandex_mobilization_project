package com.example.narek.project_mobilization_yandex.ui.language_list;

import android.os.Handler;
import android.util.Log;

import com.example.narek.project_mobilization_yandex.data.model.clean.Language;
import com.example.narek.project_mobilization_yandex.data.model.event_bus_dto.AvailableLanguageEvent;
import com.example.narek.project_mobilization_yandex.ui.base_repository.BaseRepositoryPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;


class LanguageListPresenter extends BaseRepositoryPresenter<LanguageListContract.IView>
        implements LanguageListContract.IPresenter {

    private String mCheckedLanguageCod;

    @Override
    public void onCreate() {
        super.onCreate();
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
    public void init(String checkedLanguageCod) {
        mCheckedLanguageCod = checkedLanguageCod;
    }

    @Override
    public void startLoadData() {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getLanguageList();
    }

    @Override
    public void handleRetryClick() {
        getRepository().getAndSaveAvailableLanguageListAsync();
        if (isViewAttached()) {
            getView().showProgress();
            getView().hideErrorLayout();
        }
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoadLanguageListEvent(AvailableLanguageEvent event) {
        if (event.getError() == null) {
            showLanguageList(event.getLanguageList());
        } else {
            showError(event.getError());
        }
    }

    private void getLanguageList() {
        getRepository().getAvailableLanguageListAsync();
    }

    private void showError(String error) {
        int i = 0;
        if (isViewAttached()) {
            getView().hideProgress();
            getView().showError(error);
        }
    }

    private void showLanguageList(List<Language> data) {
        if (isViewAttached()) {
            getView().hideProgress();
            getView().hideErrorLayout();
            int checkedLanguageIndex = getCheckedLanguageIndex(mCheckedLanguageCod, data);
            getView().showLanguageList(data, checkedLanguageIndex);
        }
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
}
