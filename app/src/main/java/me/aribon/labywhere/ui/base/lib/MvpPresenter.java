package me.aribon.labywhere.ui.base.lib;

/**
 * Created by anthony.ribon
 * On 22/07/2017
 */

public interface MvpPresenter<V extends MvpView> {

    V getView();

    void onAttach(V mvpView);

    void onDetach();

    void onStart();

    void onStop();
}
