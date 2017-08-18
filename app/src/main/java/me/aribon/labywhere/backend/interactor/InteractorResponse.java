package me.aribon.labywhere.backend.interactor;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by aribon from Insign Mobility
 * on 21/11/2016
 */
public class InteractorResponse<T> {

    enum From {CACHE, DATABASE, NETWORK, DUMMY};

    T object;
    From from;

    public static <T> InteractorResponse<T> createCacheResponse(@Nullable T object) {
        return new InteractorResponse<>(object, InteractorResponse.From.CACHE);
    }

    public static <T> InteractorResponse<T> createDatabaseResponse(@Nullable T object) {
        return new InteractorResponse<>(object, InteractorResponse.From.DATABASE);
    }

    public static <T> InteractorResponse<T> createNetworkResponse(@Nullable T object) {
        return new InteractorResponse<>(object, InteractorResponse.From.NETWORK);
    }

    public static <T> InteractorResponse<T> createDummyResponse(@Nullable T object) {
        return new InteractorResponse<>(object, InteractorResponse.From.DUMMY);
    }

    private InteractorResponse() {
    }

    private InteractorResponse(T object, @NonNull From from) {
        this.object = object;
        this.from = from;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public From getFrom() {
        return from;
    }

    public void setFrom(@NonNull From from) {
        this.from = from;
    }
}
