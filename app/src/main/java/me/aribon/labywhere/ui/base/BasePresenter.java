package me.aribon.labywhere.ui.base;

import me.aribon.labywhere.backend.utils.SubscriptionCollector;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by anthony.ribon
 * On 22/07/2017
 */

public class BasePresenter<V extends BaseMvpView>
        implements BaseMvpPresenter<V> {

    private V mvpView;

    //fixme remove: view must attach from the view
    public BasePresenter(V mvpView) {
        onAttach(mvpView); //fixme call from View
    }

    @Override
    public V getView() {
        return mvpView;
    }

    @Override
    public void onAttach(V mvpView) {
        this.mvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mvpView = null;
    }

    public <T> long subscribeTo(Observable<T> observable, Subscriber<T> subscriber) {
        Subscription subscription = observable.subscribe(subscriber);
        return SubscriptionCollector.getInstance().addSubscription(subscription);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }
}
