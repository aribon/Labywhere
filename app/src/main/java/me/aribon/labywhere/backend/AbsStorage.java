package me.aribon.labywhere.backend;

import java.util.List;

import rx.Observable;

/**
 * Created by aribon from Insign Mobility
 * on 18/11/2016
 */
public abstract class AbsStorage<V, K> {

    public abstract Observable<V> get(K id);

    public abstract Observable<List<V>> getAll();

//    public abstract void put(V value);

//    public abstract boolean delete(K key);

//    public abstract boolean deleteAll();
}
