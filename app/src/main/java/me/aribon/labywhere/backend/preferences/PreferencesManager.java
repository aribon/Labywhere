package me.aribon.labywhere.backend.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created on 25/04/2016
 *
 * @author Anthony
 */
public class PreferencesManager {

    private static final String PREF_NAME = "Labywhere.Preferences";

    private static PreferencesManager sInstance;
    private final SharedPreferences mPref;

    public PreferencesManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null)
            sInstance = new PreferencesManager(context);
    }

    public static synchronized PreferencesManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(PreferencesManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public synchronized SharedPreferences getSharedPreferences() {
        return mPref;
    }

    public void remove(String key) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .remove(key)
                .commit();
    }

    public boolean clear() {
        return PreferencesManager.getInstance().getSharedPreferences().edit()
                .clear()
                .commit();
    }

}