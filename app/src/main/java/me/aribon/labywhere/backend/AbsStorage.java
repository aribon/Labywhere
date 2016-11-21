package me.aribon.labywhere.backend;

import java.util.List;

import rx.Observable;

/**
 * Created by aribon
 * on 18/11/2016
 */
public abstract class AbsStorage<V> {

    public abstract Observable<V> get(int id);

    public abstract Observable<List<V>> getAll();

    public abstract void put(V value);

    public abstract void delete(int key);
}
