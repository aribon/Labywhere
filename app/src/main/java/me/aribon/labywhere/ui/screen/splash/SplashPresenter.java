package me.aribon.labywhere.ui.screen.splash;

import android.content.Intent;
import android.os.Handler;

import java.util.Map;

import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.backend.provider.preferences.AccountPreferences;
import me.aribon.labywhere.backend.provider.preferences.AuthPreferences;
import me.aribon.labywhere.backend.provider.network.AuthNetworkProvider;
import me.aribon.labywhere.backend.provider.network.response.AuthResponse;
import me.aribon.labywhere.backend.utils.AutoPurgeSubscriber;
import me.aribon.labywhere.base.AppBasePresenter;
import me.aribon.labywhere.ui.screen.auth.AuthActivity;
import me.aribon.labywhere.ui.screen.home.HomeActivity;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class SplashPresenter extends AppBasePresenter<SplashActivity> {

    public static final String TAG = SplashPresenter.class.getSimpleName();

    private static final int SPLASH_MIN_TIME_DISPLAY = 2000; //in milliseconds

    @Override
    public void onResume() {
        super.onResume();
        loadAccount();
//        loadAccountIfPossible();
    }

    public void prepareLogin() {

    }

    private void loadAccountIfPossible() {
//        if (ProfileManager.getInstance().hasAccount()) {
//            prepareLogin();
//        } else {
            startAuthActivityWithDelay();
//        }
    }

    private void login(Map<String, String> credentials) {
        subscribeTo(
                AuthNetworkProvider.getInstance().login(credentials),
                new AutoPurgeSubscriber<AuthResponse>() {
                    @Override
                    public void onNext(AuthResponse authResponse) {
                        if (authResponse.isError()) {
                            //TODO set error
                        } else {
                            AuthPreferences.setAuthToken(authResponse.getToken()); //Save token in preference
                            loadAccount(); //Load user data
                        }
                    }
                }
        );
    }

    private void loadAccount() {
//        subscribeTo(
//                ProfileManager.getInstance().loadAccount(),
//                new AutoPurgeSubscriber<User>() {
//                    @Override
//                    public void onNext(User user) {
//                        super.onNext(user);
//                        Log.d(TAG, "onNext: Logged");
//                        saveAccount(user);
                        startHomeActivityWithDelay();
//                    }
//                }
//        );
    }

    private void saveAccount(User user) {
        AccountPreferences.setAccount(user);
    }

    private void startHomeActivityWithDelay() {
        Handler handler = new Handler();
        handler.postDelayed(this::startHomeActivity, SPLASH_MIN_TIME_DISPLAY);
    }

    private void startHomeActivity() {
        Intent intent = new Intent(getView(), HomeActivity.class);
        getView().startActivity(intent);
        getView().finish();
    }

    private void startAuthActivityWithDelay() {
        Handler handler = new Handler();
        handler.postDelayed(this::startAuthActivity, SPLASH_MIN_TIME_DISPLAY);
    }

    private void startAuthActivity() {
        Intent intent = new Intent(getView(), AuthActivity.class);
        getView().startActivity(intent);
        getView().finish();
    }

}
