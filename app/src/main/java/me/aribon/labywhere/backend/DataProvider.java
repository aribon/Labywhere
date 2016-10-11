package me.aribon.labywhere.backend;

import android.util.Log;

import me.aribon.labywhere.backend.model.Data;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created by aribon from Insign Mobility
 * on 11/10/2016
 */
public abstract class DataProvider {

    private static final String TAG = DataProvider.class.getSimpleName();

    DataProvider() {
    }

    <T extends Data> Observable.Transformer<T, T> logSource(final String source) {
        return observable -> observable.doOnNext(
                new Action1<T>() {
                    @Override
                    public void call(T t) {
                        if (t == null) {
                            Log.e(TAG, "logSource: " + source + " does not have any data.");
                        } else if (!t.isUpToDate()) {
                            Log.e(TAG, "logSource: " + source + " has stale data.");
                        } else {
                            Log.e(TAG, "logSource: " + source + " has the data you are looking for!");
                        }
                    }
                });
    }

    abstract <T extends Data> Observable.Transformer<T, T> clearDataIfStale(int id);
}
