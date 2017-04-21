package com.example.narek.project_mobilization_yandex.data.db;

import com.example.narek.project_mobilization_yandex.data.model.dao.LanguageDao;
import com.example.narek.project_mobilization_yandex.data.model.dao.TranslationDao;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public interface IDatabaseRepository {
     void saveTranslation(Realm realm, final TranslationDao translationDao);

     void saveLanguageList(Realm realm, final List<LanguageDao> languageDaoList);

     void updateFavoriteStatus(Realm realm, final String primaryKey, final boolean isFavorite);

     void deleteAllTranslations(Realm realm);

     TranslationDao getTranslationByPrimaryKey(Realm realm, String primaryKey);

     RealmResults<TranslationDao> getAllTranslations(Realm realm);

     RealmResults<LanguageDao> getLanguageList(Realm realm);
}
