package me.aribon.labywhere.backend.interactor;

import android.util.Log;

import java.util.List;

import me.aribon.labywhere.backend.model.Data;
import rx.Observable;

/**
 * Created by aribon
 * on 18/11/2016
 */
public abstract class Interactor<V> {

    private static final String TAG = Interactor.class.getSimpleName();

    public abstract Observable<InteractorResponse<V>> retrieve(int id);

    public abstract Observable<InteractorResponse<List<V>>> retrieveAll();

    public abstract void update(V value);

    public abstract void remove(int id);

    <T extends Data> Observable.Transformer<T, T> logSource(final String source) {
        return observable -> observable.doOnNext(
                t -> {
                    if (t == null) {
                        Log.e(TAG, "logSource: " + source + " does not have any data.");
                    } else if (!t.isUpToDate()) {
                        Log.e(TAG, "logSource: " + source + " has stale data.");
                    } else {
                        Log.e(TAG, "logSource: " + source + " has the data you are looking for!");
                    }
                });
    }

    public <T extends Data> boolean ifStale(InteractorResponse<T> response) {
        return response != null
                && response.getObject() != null
                && response.getObject().isUpToDate();
    }

    abstract <T extends Data> Observable.Transformer<T, T> clearCacheDataIfStale(int id);
}
