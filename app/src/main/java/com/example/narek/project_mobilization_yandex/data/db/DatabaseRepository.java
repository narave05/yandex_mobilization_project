package com.example.narek.project_mobilization_yandex.data.db;

import com.example.narek.project_mobilization_yandex.data.model.dao.DictionaryDao;
import com.example.narek.project_mobilization_yandex.data.model.dao.DictionaryItemDao;
import com.example.narek.project_mobilization_yandex.data.model.dao.ExampleDao;
import com.example.narek.project_mobilization_yandex.data.model.dao.LanguageDao;
import com.example.narek.project_mobilization_yandex.data.model.dao.SynonymDao;
import com.example.narek.project_mobilization_yandex.data.model.dao.TranslationDao;
import com.example.narek.project_mobilization_yandex.data.model.dao.TranslationItemDao;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
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

    public static void saveLanguageList(Realm realm, final List<LanguageDao> languageDaoList) {

        if (!realm.isInTransaction()) {
            realm.beginTransaction();
        }
        for (LanguageDao languageDao : languageDaoList) {
            realm.copyToRealmOrUpdate(languageDao);
        }
        realm.commitTransaction();

    }

    public static void updateFavoriteStatus(Realm realm, final String primaryKey, final boolean isFavorite) {

        if (!realm.isInTransaction()) {
            realm.beginTransaction();
        }
        TranslationDao translationDao = getTranslationByPrimaryKey(realm, primaryKey);
        if (translationDao != null) {
            translationDao.setFavorite(isFavorite);
        }
        realm.commitTransaction();
    }

    public static void deleteAllTranslations(Realm realm) {
        deleteRealmResult(realm, TranslationDao.class);
        deleteRealmResult(realm, TranslationItemDao.class);
        deleteRealmResult(realm, DictionaryDao.class);
        deleteRealmResult(realm, DictionaryItemDao.class);
        deleteRealmResult(realm, SynonymDao.class);
        deleteRealmResult(realm, ExampleDao.class);
    }

    private static <T extends RealmObject> void deleteRealmResult(Realm realm, Class<T> clazz) {
        if (!realm.isInTransaction()) {
            realm.beginTransaction();
        }
        RealmResults<T> realmResults = realm.where(clazz).findAll();
        realmResults.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public static TranslationDao getTranslationByPrimaryKey(Realm realm, String primaryKey) {
        // TODO: 19.04.2017
        return realm.where(TranslationDao.class)
                .equalTo("primaryKey", primaryKey)
                .findFirst();
    }

    public static RealmResults<TranslationDao> getAllTranslations(Realm realm) {

        return realm.where(TranslationDao.class)
                .findAllSorted("createdOrUpdatedDate", Sort.DESCENDING);

    }

    public static RealmResults<LanguageDao> getLanguageList(Realm realm) {

        return realm.where(LanguageDao.class)
                .findAllSorted("fullName");

    }
}
