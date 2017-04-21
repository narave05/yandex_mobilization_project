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

public class DatabaseRepository implements IDatabaseRepository{

    @Override
    public void saveTranslation(Realm realm, final TranslationDao translationDao) {

        if (!realm.isInTransaction()) {
            realm.beginTransaction();
        }
        realm.copyToRealmOrUpdate(translationDao);
        realm.commitTransaction();

    }

    @Override
    public void saveLanguageList(Realm realm, final List<LanguageDao> languageDaoList) {

        if (!realm.isInTransaction()) {
            realm.beginTransaction();
        }
        for (LanguageDao languageDao : languageDaoList) {
            realm.copyToRealmOrUpdate(languageDao);
        }
        realm.commitTransaction();

    }

    @Override
    public void updateFavoriteStatus(Realm realm, final String primaryKey, final boolean isFavorite) {

        if (!realm.isInTransaction()) {
            realm.beginTransaction();
        }
        TranslationDao translationDao = getTranslationByPrimaryKey(realm, primaryKey);
        if (translationDao != null) {
            translationDao.setFavorite(isFavorite);
        }
        realm.commitTransaction();
    }

    @Override
    public void deleteAllTranslations(Realm realm) {
        deleteRealmResult(realm, TranslationDao.class);
        deleteRealmResult(realm, TranslationItemDao.class);
        deleteRealmResult(realm, DictionaryDao.class);
        deleteRealmResult(realm, DictionaryItemDao.class);
        deleteRealmResult(realm, SynonymDao.class);
        deleteRealmResult(realm, ExampleDao.class);
    }

    private <T extends RealmObject> void deleteRealmResult(Realm realm, Class<T> clazz) {
        if (!realm.isInTransaction()) {
            realm.beginTransaction();
        }
        RealmResults<T> realmResults = realm.where(clazz).findAll();
        realmResults.deleteAllFromRealm();
        realm.commitTransaction();
    }

    @Override
    public TranslationDao getTranslationByPrimaryKey(Realm realm, String primaryKey) {
        return realm.where(TranslationDao.class)
                .equalTo(TranslationDao.PRIMARY_KEY, primaryKey)
                .findFirst();
    }

    @Override
    public RealmResults<TranslationDao> getAllTranslations(Realm realm) {

        return realm.where(TranslationDao.class)
                .findAllSorted(TranslationDao.CREATED_OR_UPDATED_DATE, Sort.DESCENDING);

    }

    @Override
    public RealmResults<LanguageDao> getLanguageList(Realm realm) {

        return realm.where(LanguageDao.class)
                .findAllSorted(LanguageDao.FULL_NAME);

    }
}
