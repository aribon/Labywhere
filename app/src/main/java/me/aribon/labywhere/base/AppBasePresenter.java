package me.aribon.labywhere.base;

import me.aribon.basemvp.presenter.BasePresenter;
import me.aribon.basemvp.view.BaseView;
import me.aribon.labywhere.backend.utils.SubscriptionCollector;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by aribon from Insign Mobility
 * on 15/11/2016
 */
abstract public class AppBasePresenter<V extends BaseView> extends BasePresenter<V> {

    public <T> long subscribeTo(Observable<T> observable, Subscriber<T> subscriber) {
        Subscription subscription = observable.subscribe(subscriber);
        return SubscriptionCollector.getInstance().addSubscription(subscription);
    }
}
