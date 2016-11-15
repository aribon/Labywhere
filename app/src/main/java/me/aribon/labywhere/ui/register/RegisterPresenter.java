package me.aribon.labywhere.ui.register;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import me.aribon.basemvp.presenter.BasePresenter;
import me.aribon.labywhere.backend.manager.FacebookManager;
import me.aribon.labywhere.backend.manager.GoogleManager;
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

    GoogleManager googleManager;
    FacebookManager facebookManager;

    @Override
    public void onCreate() {
        super.onCreate();

        googleManager = new GoogleManager(mView, onGoogleManagerListenerAdapter);
        facebookManager = new FacebookManager(mView, onFacebookManagerListenerAdapter);
    }

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
        subscription = AuthService.register(body).subscribe(new Observer<AuthResponse>() {
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

    public void facebookRegisterClick() {
        facebookManager.login();
    }

    public void googleRegisterClick() {
        googleManager.login();
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

    @Override
    public void onDestroy() {
        googleManager.logout();
        facebookManager.logout();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GoogleManager.RC_SIGN_IN) {
            googleManager.onActivityResult(requestCode, resultCode, data);
        } else {
            facebookManager.onActivityResult(requestCode, resultCode, data);
        }

    }

    FacebookManager.OnFacebookManagerListenerAdapter onFacebookManagerListenerAdapter = new FacebookManager.OnFacebookManagerListenerAdapter() {
        @Override
        public void onFacebookLoginSuccess(FacebookManager.FacebookUser facebookUser) {
            Log.d(TAG, "onSuccess: " + facebookUser.toString());
        }
    };

    GoogleManager.OnGoogleManagerListenerAdapter onGoogleManagerListenerAdapter = new GoogleManager.OnGoogleManagerListenerAdapter() {
        @Override
        public void onGoogleLoginSuccess(GoogleManager.GoogleUser googleUser) {
            Log.d(TAG, "onSuccess: " + googleUser.toString());
        }

        @Override
        public void onGoogleLoginSuccess(String token, GoogleManager.GoogleUser googleUser) {
            super.onGoogleLoginSuccess(token, googleUser);
            Log.d(TAG, "onSuccess: " + "apiToken:" + token + ", user:" + googleUser.toString());
        }

        @Override
        public void onGoogleLoginFailed(String message) {
            super.onGoogleLoginFailed(message);
            Log.e(TAG, "onGoogleLoginFailed: " + message);
        }

        @Override
        public void onGoogleLoginFailed() {
            super.onGoogleLoginFailed();
            Log.e(TAG, "onGoogleLoginFailed");
        }
    };
}
