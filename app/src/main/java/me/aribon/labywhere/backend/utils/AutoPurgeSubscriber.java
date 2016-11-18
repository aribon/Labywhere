package me.aribon.labywhere.backend.utils;

import rx.Subscriber;


/**
 * Created by aribon from Insign Mobility
 * on 15/11/2016
 */
public class AutoPurgeSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {
        SubscriptionCollector.getInstance().update();
    }

    @Override
    public void onError(Throwable e) {
        SubscriptionCollector.getInstance().update();
    }

    @Override
    public void onNext(T t) {

    }
}
