package me.aribon.labywhere.ui.splash;

import android.os.Handler;

import me.aribon.basemvp.presenter.BasePresenter;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class SplashPresenter extends BasePresenter<SplashActivity> {

    public static final String TAG = SplashPresenter.class.getSimpleName();

    private static final int SPLASH_MIN_TIME_DISPLAY = 2000; //in milliseconds

    @Override
    public void onResume() {
        super.onResume();
        startLoading();
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //TODO start loading
                mView.startAuthActivity();
            }
        }, SPLASH_MIN_TIME_DISPLAY);
    }

}