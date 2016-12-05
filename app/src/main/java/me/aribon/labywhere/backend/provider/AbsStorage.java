package me.aribon.labywhere.backend.provider;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;

/**
 * Created by aribon
 * on 18/11/2016
 */
public abstract class AbsStorage<V> {

    public abstract void post(@NonNull V value);

    public abstract Observable<V> get(int id);

    public abstract Observable<List<V>> getAll();

    public abstract void put(@NonNull V value);

    public abstract void delete(@NonNull V value);
}
