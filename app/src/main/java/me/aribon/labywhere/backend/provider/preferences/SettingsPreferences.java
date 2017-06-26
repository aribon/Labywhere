package me.aribon.labywhere.backend.provider.preferences;

/**
 * Created on 19/05/2016
 *
 * @author Anthony
 */
public class SettingsPreferences {

    public static final String TAG = SettingsPreferences.class.getSimpleName();

    private static final String KEY_LAST_CACHE_UPDATE = "last_cache_update";

    public static void setLastCacheUpdate(long lastCacheUpdate) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putLong(KEY_LAST_CACHE_UPDATE, lastCacheUpdate)
                .commit();
    }

    public static long getLastCacheUpdate() {
        return PreferencesManager.getInstance().getSharedPreferences().getLong(KEY_LAST_CACHE_UPDATE, 0);
    }
}
