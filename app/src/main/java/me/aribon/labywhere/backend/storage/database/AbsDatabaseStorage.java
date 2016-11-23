package me.aribon.labywhere.backend.storage.database;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import me.aribon.labywhere.backend.storage.AbsStorage;


/**
 * Created by aribon from Insign Mobility
 * on 23/11/2016
 */
public abstract class AbsDatabaseStorage<T> extends AbsStorage<T> {

    final protected static String DB_NAME = "labywhere.realm";

    private static final String TAG = AbsDatabaseStorage.class.getSimpleName();

    protected Realm realm;
    protected RealmConfiguration realmConfig;

    AbsDatabaseStorage() {

//        RealmMigration migration = new RealmMigration() {
//            @Override
//            public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
//                RealmSchema schema = realm.getSchema();
//
//                if (oldVersion == 0) {
//                    schema.create(User.TABLE_NAME)
//                            .addField(User.KEY_ID, int.class)
//                            .addField(User.KEY_TYPE, int.class)
//                            .addField(User.KEY_EMAIL, String.class)
//                            .addField(User.KEY_PROFILE, Profile.class)
//                            .addField(User.KEY_CREATED_AT, String.class)
//                            .addField(User.KEY_CHANGED_AT, String.class);
//                    oldVersion++;
//                }
//            }
//        };

        realmConfig = new RealmConfiguration.Builder()
                .name(DB_NAME)
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
//                .modules(new LabywhereApplication.RealmDefaultModule())
                .build();


//        Realm.setDefaultConfiguration(config);

//        realm = Realm.getInstance(config);
    }



//    @RealmModule(classes = { User.class, Profile.class })
//    public class RealmDefaultModule {
//    }
}
