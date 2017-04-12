package com.example.narek.project_mobilization_yandex.data.db;

import com.example.narek.project_mobilization_yandex.data.model.dto.TranslationDTO;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class DatabaseRepository {

    public static void saveTranslation(final TranslationDTO translationDTO) {

        final Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(translationDTO);
            }
        });
    }

    public static void updateFavoriteStatus(final String primaryKey, final boolean isFavorite) {

        Realm realm = Realm.getDefaultInstance();
        if (!realm.isInTransaction()) {
            realm.beginTransaction();
        }
        TranslationDTO translationDTO = getTranslationByPrimaryKey(primaryKey);
        translationDTO.setFavorite(isFavorite);
        realm.commitTransaction();
    }

    public static TranslationDTO getTranslationByPrimaryKey(String primaryKey) {

        Realm realm = Realm.getDefaultInstance();
        TranslationDTO translationDTO = realm.where(TranslationDTO.class)
                .equalTo("primaryKey", primaryKey)
                .findFirst();

        return translationDTO;
    }

    public static RealmResults<TranslationDTO> getAllTranslations() {

        final Realm realm = Realm.getDefaultInstance();
        RealmResults<TranslationDTO> translationDTOs = realm.where(TranslationDTO.class)
                .findAllSorted("createdOrUpdatedDate", Sort.DESCENDING);

        return translationDTOs;

    }

    public static RealmResults<TranslationDTO> getFavorites() {

        final Realm realm = Realm.getDefaultInstance();
        RealmResults<TranslationDTO> translationDTOs = realm.where(TranslationDTO.class)
                .equalTo("isFavorite", true)
                .findAllSorted("createdOrUpdatedDate", Sort.DESCENDING);

        return translationDTOs;
    }
}
