package me.aribon.labywhere.backend.provider.database.helper;

import io.realm.RealmConfiguration;

/**
 * Created by aribon from Insign Mobility
 * on 25/11/2016
 */
public class RealmDatabaseHelper extends DatabaseHelper {

    final protected static String DB_NAME = "labywhere.realm";
    final protected static int DB_VERSION = 1;

    public enum RealmConfigType {DEFAULT}

    public RealmConfiguration getConfig(RealmConfigType configType) {

        switch (configType) {

            case DEFAULT:
            default:
                return getDefaultConfig();
        }
    }

    private RealmConfiguration getDefaultConfig() {
        return new RealmConfiguration.Builder()
                .name(DB_NAME)
                .schemaVersion(DB_VERSION)
                .deleteRealmIfMigrationNeeded()
//                .modules(new LabywhereApplication.RealmDefaultModule())
                .build();
    }
}
