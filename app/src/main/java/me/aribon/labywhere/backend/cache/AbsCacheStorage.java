package me.aribon.labywhere.backend.cache;

import android.support.annotation.NonNull;

import me.aribon.labywhere.backend.preferences.SettingsPreferences;

/**
 * Created on 19/05/2016
 *
 * @author Anthony
 */
public abstract class AbsCacheStorage<K, V> {

    protected static final long EXPIRATION_TIME_MILLIS = /*60 * 10 * 1000*/ 10 * 1000;

    public static final String TAG = AbsCacheStorage.class.getSimpleName();

    public abstract V get(K key);

    public abstract V getAll();

    public abstract void put(K key, V value);

    public abstract boolean isCached(K key);

    public abstract boolean isExpired();

    public abstract boolean isExpired(@NonNull K key);

    public abstract boolean delete(K key);

    public abstract boolean deleteAll();


    /**
     * Set in millis, the last time the cache was accessed.
     */
    protected void setLastCacheUpdateTimeMillis() {
        long currentMillis = System.currentTimeMillis();
        SettingsPreferences.setLastCacheUpdate(currentMillis);
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    protected long getLastCacheUpdateTimeMillis() {
        return SettingsPreferences.getLastCacheUpdate();
    }
}
