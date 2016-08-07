package me.aribon.labywhere;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import me.aribon.labywhere.backend.preferences.PreferencesManager;

/**
 * Created on 25/04/2016
 *
 * @author Anthony
 */
public class LabywhereApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        initFacebook();
        
        initPrefs();
        initDB();
        initWebServices();
    }

    private void initFacebook() {
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
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

    public static Context getContext() {
        return context;
    }
}
