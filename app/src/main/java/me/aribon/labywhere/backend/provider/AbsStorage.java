package me.aribon.labywhere.backend.provider;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import me.aribon.labywhere.backend.model.User;

/**
 * Created by aribon
 * on 18/11/2016
 */
public abstract class AbsStorage<V> {

    public abstract void post(@NonNull V value);

    public abstract Single<User> get(int id);

    public abstract Flowable<List<User>> getAll();

    public abstract void put(@NonNull V value);

    public abstract void delete(@NonNull V value);
}
