package me.aribon.labywhere.backend.cache;

import me.aribon.labywhere.backend.preferences.SettingsPreferences;

/**
 * Created on 19/05/2016
 *
 * @author Anthony
 */
public class AbsCacheStorage<K, V> {

    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    public static final String TAG = AbsCacheStorage.class.getSimpleName();

    /**
     * Set in millis, the last time the cache was accessed.
     */
    private void setLastCacheUpdateTimeMillis() {
        long currentMillis = System.currentTimeMillis();
        SettingsPreferences.setLastCacheUpdate(currentMillis);
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    private long getLastCacheUpdateTimeMillis() {
        return SettingsPreferences.getLastCacheUpdate();
    }
}
