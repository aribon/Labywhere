package me.aribon.labywhere.ui.registration;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import me.aribon.basemvp.presenter.BasePresenter;
import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.backend.preferences.AuthPreferences;
import me.aribon.labywhere.backend.preferences.UserPreferences;
import me.aribon.labywhere.backend.webservice.response.AuthResponse;
import me.aribon.labywhere.backend.webservice.response.UserResponse;
import me.aribon.labywhere.backend.webservice.service.AuthService;
import me.aribon.labywhere.ui.home.HomeActivity;
import rx.Observer;
import rx.Subscription;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class RegisterPresenter extends BasePresenter<RegisterActivity> {

    public static final String TAG = RegisterPresenter.class.getSimpleName();

    private Subscription subscription;

    private Map<String, String> credentials; //TODO ???????

    @Override
    public void onResume() {
        super.onResume();
    }

    public void prepareRegister() {

        Log.d(TAG, "login");

        String email = mView.getEmail();
        String password = mView.getPassword();
        String firstname = mView.getFirstnmee();
        String lastname = mView.getLastname();

        if (TextUtils.isEmpty(email)) {
            mView.setEmailError(""); //TODO
            Log.e(TAG, "onNext: email empty");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            mView.setPasswordError(""); //TODO
            Log.e(TAG, "onNext: password empty");
            return;
        }

        if (TextUtils.isEmpty(firstname)) {
            mView.setFirstnameError(""); //TODO
            Log.e(TAG, "onNext: firstname empty");
            return;
        }

        if (TextUtils.isEmpty(lastname)) {
            mView.setLastnameError(""); //TODO
            Log.e(TAG, "onNext: lastname empty");
            return;
        }

        credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);

        Map<String, String> body = new HashMap<>();
        body.putAll(credentials);
        body.put("firstname", firstname);
        body.put("lastname", lastname);

        register(body);
    }

    private void register(Map<String, String> body) {
        subscription = AuthService.register(body, new Observer<AuthResponse>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "startLogin onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "startLogin onError: " + e.getMessage());
            }

            @Override
            public void onNext(AuthResponse authResponse) {

                if (authResponse.isError()) {
                    //TODO set error
                } else {
                    login(credentials);
                }
            }
        });
    }

    private void login(Map<String, String> credentials) {
        subscription = AuthService.login(credentials, new Observer<AuthResponse>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "startLogin onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "startLogin onError: " + e.getMessage());
            }

            @Override
            public void onNext(AuthResponse authResponse) {

                if (authResponse.isError()) {
                    //TODO set error
                } else {
                    AuthPreferences.setAuthToken(authResponse.getToken()); //Save token in preference
                    loadAccount(authResponse.getToken()); //Load user data
                }
            }
        });
    }

    private void loadAccount(String token) {
        subscription = AuthService.getAccount(token, new Observer<UserResponse>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "loadAccount onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "loadAccount onError: " + e.getMessage());
            }

            @Override
            public void onNext(UserResponse userResponse) {

                if (userResponse.isError()) {
                    //TODO set error
                } else {
                    saveAccount(userResponse.getUser());
                    Log.d(TAG, "loadAccount onNext: " + userResponse.getUser().toString());
                    Log.d(TAG, "loadAccount onNext: " + userResponse.getUser().getProfile().toString());
                    startHomeActivity();
                }
            }
        });
    }

    private void saveAccount(User user) {
        UserPreferences.setUser(user);
    }

    private void startHomeActivity() {
        Intent intent = new Intent(mView, HomeActivity.class);
        mView.startActivity(intent);
        mView.finish();
    }
}
