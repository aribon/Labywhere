package me.aribon.labywhere.ui.base;

import me.aribon.labywhere.ui.base.lib.AndroidLifecycle;
import me.aribon.labywhere.ui.base.lib.MvpPresenter;
import rx.Observable;
import rx.Subscriber;

/**
 * Created by anthony.ribon
 * On 22/07/2017
 */

public interface BaseMvpPresenter<V extends BaseMvpView>
    extends MvpPresenter<V>, AndroidLifecycle {

  <T> long subscribeTo(Observable<T> observable, Subscriber<T> subscriber);

}
