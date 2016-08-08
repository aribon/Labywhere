package me.aribon.basemvp.presenter;

import android.content.Intent;

import me.aribon.basemvp.view.BaseView;

/**
 * Created on 19/03/2016
 *
 * @author Anthony
 */
public class BasePresenter<V extends BaseView> {

    public V mView;

    public void onCreate() {

    }

    public void onAttachView(V view) {
        this.mView = view;
    }

    public void onResume() {

    }

    public void onPause() {

    }

    public void onDetachView() {
        this.mView = null;
    }

    public void onDestroy() {
        onDetachView();
    }

    public void onBackPressed() {
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
