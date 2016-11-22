package me.aribon.labywhere.backend.model;

/**
 * Created by aribon from Insign Mobility
 * on 12/09/2016
 */
public class Data {

    private static final long STALE_MS = 20 * 1000; // Data is stale after 10 seconds

    protected int id;
    private long timestamp;

    Data() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     *
     * @return
     * The id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(int id) {
        this.id = id;
    }

    public Data withId(int id) {
        this.id = id;
        return this;
    }

    public boolean isUpToDate() {
        return System.currentTimeMillis() - timestamp < STALE_MS;
    }
}
