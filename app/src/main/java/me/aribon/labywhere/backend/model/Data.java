package me.aribon.labywhere.backend.model;

/**
 * Created by aribon from Insign Mobility
 * on 12/09/2016
 */
public class Data {

    private static final long STALE_MS = 20 * 1000; // Data is stale after 10 seconds

    long timestamp;

    public Data() {
        this.timestamp = System.currentTimeMillis();
    }

    public boolean isUpToDate() {
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }
}
