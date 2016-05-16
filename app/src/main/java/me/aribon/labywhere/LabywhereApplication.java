package me.aribon.labywhere;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import me.aribon.labywhere.backend.preferences.PreferencesManager;

/**
 * Created on 25/04/2016
 *
 * @author Anthony
 */
public class LabywhereApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initPrefs();
        initDB();
        initWebServices();
    }

    private void initPrefs() {
        PreferencesManager.initializeInstance(this);
    }

    private void initDB() {
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfig);
    }

    private void initWebServices() {
    }
}
