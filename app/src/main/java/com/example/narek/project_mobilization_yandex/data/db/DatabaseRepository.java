package com.example.narek.project_mobilization_yandex.data.db;

import com.example.narek.project_mobilization_yandex.data.model.dao.TranslationDao;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class DatabaseRepository {

    public static void saveTranslation(Realm realm, final TranslationDao translationDao) {

        if (!realm.isInTransaction()) {
            realm.beginTransaction();
        }
        realm.copyToRealmOrUpdate(translationDao);
        realm.commitTransaction();

    }

    public static void updateFavoriteStatus(Realm realm, final String primaryKey, final boolean isFavorite) {

        if (!realm.isInTransaction()) {
            realm.beginTransaction();
        }
        TranslationDao translationDao = getTranslationByPrimaryKey(realm, primaryKey);
        translationDao.setFavorite(isFavorite);
        realm.commitTransaction();
    }

    public static TranslationDao getTranslationByPrimaryKey(Realm realm, String primaryKey) {

        return realm.where(TranslationDao.class)
                .equalTo("primaryKey", primaryKey)
                .findFirst();
    }

    public static RealmResults<TranslationDao> getAllTranslations(Realm realm) {

        return realm.where(TranslationDao.class)
                .findAllSorted("createdOrUpdatedDate", Sort.DESCENDING);

    }

    public static RealmResults<TranslationDao> getFavorites(Realm realm) {

        return realm.where(TranslationDao.class)
                .equalTo("isFavorite", true)
                .findAllSorted("createdOrUpdatedDate", Sort.DESCENDING);
    }
}
