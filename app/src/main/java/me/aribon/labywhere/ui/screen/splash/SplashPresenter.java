package me.aribon.labywhere.ui.screen.splash;

import android.content.Intent;
import android.os.Handler;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import me.aribon.labywhere.backend.utils.AutoPurgeSubscriber;
import me.aribon.labywhere.business.interactor.AccountInteractor;
import me.aribon.labywhere.ui.base.BaseActivity;
import me.aribon.labywhere.ui.base.BasePresenter;
import me.aribon.labywhere.ui.screen.auth.AuthActivity;
import me.aribon.labywhere.ui.screen.home.HomeActivity;
import me.aribon.labywhere.ui.screen.splash.SplashContract.View;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class SplashPresenter extends BasePresenter<SplashContract.View>
        implements SplashContract.Presenter {

    public static final String TAG = SplashPresenter.class.getSimpleName();

    private static final int SPLASH_MIN_TIME_DISPLAY = 2000; //in milliseconds

    private BaseActivity activity;

    public SplashPresenter(BaseActivity activity, View view) {
        super(view);
        this.activity = activity;
    }

    @Override
    public void onStart() {
        super.onStart();
        tryToLogin();
    }


    public void tryToLogin() {
        AccountInteractor.getInstance()
                .checkToken()
                .subscribe(
                        new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                launchLogin();
                            }

                            @Override
                            public void onError(Throwable e) {
                                navigateToAuth();
                            }
                        });
    }

    private void launchLogin() {
        AccountInteractor.getInstance()
                .doLoginByToken()
                .subscribe(
                        new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                launchRefreshDataService();
                                navigateToHome();
                            }

                            @Override
                            public void onError(Throwable e) {
                                navigateToAuth();
                            }
                        });
    }

    private void launchRefreshDataService() {
        // TODO: 15/08/2017 call data refresh service
    }

    private void navigateToAuth() {
        // TODO: 15/08/2017 call AuthRouter.start();
        startAuthActivityWithDelay(); // TODO: 15/08/2017 remove this line
    }

    private void navigateToHome() {
        // TODO: 15/08/2017 call HomeRouter.start();
        startHomeActivityWithDelay(); // TODO: 15/08/2017 remove this line
    }

    private void startHomeActivityWithDelay() {
        Handler handler = new Handler();
        handler.postDelayed(this::startHomeActivity, SPLASH_MIN_TIME_DISPLAY);
    }

    private void startHomeActivity() {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    private void startAuthActivityWithDelay() {
        Handler handler = new Handler();
        handler.postDelayed(this::startAuthActivity, SPLASH_MIN_TIME_DISPLAY);
    }

    private void startAuthActivity() {
        Intent intent = new Intent(activity, AuthActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

}
