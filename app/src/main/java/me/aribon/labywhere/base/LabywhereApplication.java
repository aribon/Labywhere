package me.aribon.labywhere.base;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import io.realm.Realm;
import me.aribon.labywhere.backend.provider.preferences.PreferencesManager;
import me.aribon.labywhere.backend.utils.SubscriptionCollector;

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

        SubscriptionCollector.initialize();

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
//        RealmConfiguration realmConfig = new RealmConfiguration.Builder(this)
//                .deleteRealmIfMigrationNeeded()
//                .build();
//
//        Realm.setDefaultConfiguration(realmConfig);

        Realm.init(context);
    }

    private void initWebServices() {
    }

    public static Context getContext() {
        return context;
    }

    @Override
    public void onTerminate() {
        SubscriptionCollector.getInstance().clear();
        super.onTerminate();
    }
}
