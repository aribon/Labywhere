package me.aribon.labywhere.ui.screen.splash;

import android.content.Intent;
import android.os.Handler;
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
    public void onResume() {
        super.onResume();
        tryToLogin();
    }


    public void tryToLogin() {
        subscribeTo(
            AccountInteractor.getInstance().checkToken(),
            new AutoPurgeSubscriber<Boolean>() {
                @Override
                public void onNext(Boolean isValid) {
                    super.onNext(isValid);
                    if (isValid) {
                        launchLogin();
                    } else {
                        navigateToAuth();
                    }
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    // TODO: 15/08/2017 show error and close app
                }
            }
        );
    }

    private void launchLogin() {
        subscribeTo(
                AccountInteractor.getInstance().doLoginByToken(),
                new AutoPurgeSubscriber<Boolean>() {
                    @Override
                    public void onNext(Boolean success) {
                        super.onNext(success);
                        if (success) {
                            launchRefreshDataService();
                            navigateToHome();
                        } else {
                            navigateToAuth();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        // TODO: 15/08/2017 show error and navigate to Auth
                        navigateToAuth();
                    }
                }
        );
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
