package me.aribon.labywhere.backend.cache;

import me.aribon.labywhere.backend.AbsStorage;
import me.aribon.labywhere.backend.preferences.SettingsPreferences;

/**
 * Created on 19/05/2016
 *
 * @author Anthony
 */
public abstract class AbsCacheStorage<V> extends AbsStorage<V> {

    static final long EXPIRATION_TIME_MILLIS = /*60 * 10 * 1000*/ 10 * 1000;

    public static final String TAG = AbsCacheStorage.class.getSimpleName();

    //TODO move to AbsStorage
    public abstract void put(int id, V value);

    public abstract boolean isCached(int id);

    public abstract boolean isExpired();

    public abstract boolean isExpired(int id);

    //TODO move to AbsStorage
    public abstract void delete(int id);

    /**
     * Set in millis, the last time the cache was accessed.
     */
    void setLastCacheUpdateTimeMillis() {
        long currentMillis = System.currentTimeMillis();
        SettingsPreferences.setLastCacheUpdate(currentMillis);
    }

    /**
     * Get in millis, the last time the cache was accessed.
     */
    long getLastCacheUpdateTimeMillis() {
        return SettingsPreferences.getLastCacheUpdate();
    }
}
