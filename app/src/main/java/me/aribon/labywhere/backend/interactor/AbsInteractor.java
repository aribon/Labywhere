package me.aribon.labywhere.backend.interactor;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import me.aribon.labywhere.backend.model.Data;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

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

    //***********
    //TRANSFORMER
    //***********
    <T extends Data> Observable.Transformer<T, T> logSource(final String source) {
        return observable -> observable
            .doOnNext(AbsInteractor.this.logSourceNextAction(source))
            .doOnError(AbsInteractor.this.logSourceErrorAction(source));
//            .doOnCompleted(AbsInteractor.this.logCompletedAction());
    }

    <T extends Data> Observable.Transformer<T, T> logCompleted() {
        return observable -> observable.doOnCompleted(logCompletedAction());
    }

    <T extends Data> Observable.Transformer<T, T> logNext() {
        return observable -> observable.doOnNext(logNextAction());
    }

    <T extends Data> Observable.Transformer<T, T> logError() {
        return observable -> observable.doOnError(logErrorAction());
    }

    //******************
    //TRANSFORMER ACTION
    //******************
    private Action0 logCompletedAction() {
        return () -> Log.i(TAG, "Work was COMPLETED");
    }

    private <T extends Data> Action1<T> logNextAction() {
        return t -> Log.i(TAG, "NEXT result was provided");
    }

    private Action1<Throwable> logErrorAction() {
        return throwable -> Log.e(TAG, "Work generated ERROR");
    }

    private <T extends Data> Action1<T> logSourceNextAction(final String source) {
        return t -> {
            if (t == null) {
                Log.i(TAG, source + " does not have any data.");
            } else if (!t.isUpToDate()) {
                Log.i(TAG, source + " has stale data.");
            } else {
                Log.i(TAG, source + " has the data you are looking for!");
            }
        };
    }

    private Action1<Throwable> logSourceErrorAction(final String source) {
        return throwable -> Log.e(TAG , source + " generate an error -> " + throwable.getMessage());
    }

    <T extends Data> boolean ifStale(InteractorResponse<T> response) {
        return response != null
                && response.getObject() != null
                && response.getObject().isUpToDate();
    }

    abstract <T extends Data> Observable.Transformer<T, T> clearCacheDataIfStale(int id);
}
