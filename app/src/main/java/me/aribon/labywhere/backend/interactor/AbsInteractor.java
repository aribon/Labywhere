package me.aribon.labywhere.backend.interactor;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import me.aribon.labywhere.backend.model.Data;
import rx.Observable;

/**
 * Created by aribon
 * on 18/11/2016
 */
abstract class AbsInteractor<V> {

    private static final String TAG = AbsInteractor.class.getSimpleName();

    public abstract void create(@NonNull V value);

    public abstract Observable<InteractorResponse<V>> retrieve(int id);

    public abstract Observable<InteractorResponse<List<V>>> retrieveAll();

    public abstract void update(@NonNull V value);

    public abstract void remove(int id);

    <T extends Data> Observable.Transformer<T, T> logSource(final String source) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.doOnNext(
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
        };
    }

    <T extends Data> boolean ifStale(InteractorResponse<T> response) {
        return response != null
                && response.getObject() != null
                && response.getObject().isUpToDate();
    }

    abstract <T extends Data> Observable.Transformer<T, T> clearCacheDataIfStale(int id);
}
