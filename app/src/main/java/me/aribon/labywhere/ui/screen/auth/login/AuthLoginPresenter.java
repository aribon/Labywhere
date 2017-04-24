package me.aribon.labywhere.ui.screen.auth.login;

import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import me.aribon.labywhere.backend.manager.ProfileManager;
import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.backend.preferences.AccountPreferences;
import me.aribon.labywhere.backend.preferences.AuthPreferences;
import me.aribon.labywhere.backend.provider.network.AuthNetworkProvider;
import me.aribon.labywhere.backend.provider.network.response.AuthResponse;
import me.aribon.labywhere.backend.utils.AutoPurgeSubscriber;
import me.aribon.labywhere.base.AppBasePresenter;
import me.aribon.labywhere.ui.module.LoginModule;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
class AuthLoginPresenter extends AppBasePresenter<AuthLoginFragment> implements LoginModule.Presenter {

    public static final String TAG = AuthLoginPresenter.class.getSimpleName();

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
//        getActivity().setResult(Activity.RESULT_CANCELED);
        super.onDestroy();
    }

    @Override
    public void checkLogin() {

        Log.d(TAG, "login");

        String email = getView().getEmail();
        String password = getView().getPassword();

        if (TextUtils.isEmpty(email)) {
            //getView().setEmailError();
            Log.e(TAG, "onNext: Email empty");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            //getView().setPasswordError();
            Log.e(TAG, "onNext: Password empty");
            return;
        }

        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);

        login(credentials);
    }

    @Override
    public void login(Map<String, String> credentials) {
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
        subscribeTo(
                ProfileManager.getInstance().loadAccount()
                        .observeOn(AndroidSchedulers.mainThread()),
                new AutoPurgeSubscriber<User>() {
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        super.onNext(user);
                        saveAccount(user);
                        Log.d(TAG, "loadAccount onNext: " + user.toString());
                        Log.d(TAG, "loadAccount onNext: " + user.getProfile().toString());
//                        AuthRouter.startHomeActivity(getActivity());
                    }
                }
        );
    }

    private void saveAccount(User user) {
        AccountPreferences.setAccount(user);
    }
}
