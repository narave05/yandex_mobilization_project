package com.example.narek.project_mobilization_yandex.ui.language_list;

import com.example.narek.project_mobilization_yandex.data.model.clean.Language;
import com.example.narek.project_mobilization_yandex.ui.base_repository.BaseRepositoryContract;

import java.util.List;


interface LanguageListContract {

    interface IView extends BaseRepositoryContract.IView {

        void showLanguageList(List<Language> languages, int selectedItemPosition);

        void returnActivityResultOk(Language language);

        void returnActivityResultCancel();

        void finishLanguageListActivity();
    }

    interface IPresenter extends BaseRepositoryContract.IPresenter<IView> {

        void init(String checkedLanguageCod);

        void startLoadData();

        void handleLanguageSelected(Language language);

        void handleOnBackPressed();
    }
}
