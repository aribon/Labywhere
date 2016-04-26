package me.aribon.labywhere;

import android.app.Application;

import me.aribon.labywhere.backend.preferences.PreferencesManager;
import me.aribon.labywhere.backend.webservice.service.AuthService;

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
        initWebServices();
    }

    private void initPrefs() {
        PreferencesManager.initializeInstance(this);
    }

    private void initWebServices() {
        AuthService.initializeInstance();
    }
}
