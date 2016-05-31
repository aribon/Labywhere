package me.aribon.labywhere.ui.login;

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
public class LoginPresenter extends BasePresenter<LoginActivity> {

    public static final String TAG = LoginPresenter.class.getSimpleName();

    private Subscription subscription;

    @Override
    public void onResume() {
        super.onResume();
    }

    public void prepareLogin() {

        Log.d(TAG, "login");

        String email = mView.getEmail();
        String password = mView.getPassword();

        if (TextUtils.isEmpty(email)) {
            //mView.setEmailError();
            Log.e(TAG, "onNext: Email empty");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            //mView.setPasswordError();
            Log.e(TAG, "onNext: Password empty");
            return;
        }

        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);

        login(credentials);
    }

    private void login(Map<String, String> credentials) {
        subscription = AuthService.login(credentials).subscribe(new Observer<AuthResponse>() {
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
        subscription = AuthService.getAccount(token).subscribe(new Observer<UserResponse>() {
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
