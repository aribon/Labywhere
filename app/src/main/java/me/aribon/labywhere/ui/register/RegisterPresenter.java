package me.aribon.labywhere.ui.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
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

    CallbackManager callbackManager;

    @Override
    public void onCreate() {
        super.onCreate();
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG, "Facebook user_id: " + loginResult.getAccessToken().getUserId());
                Log.d(TAG, "Facebook token: " + loginResult.getAccessToken().getToken());
                getFacebookUser(loginResult);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
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
        LoginManager.getInstance().logInWithReadPermissions(mView, Arrays.asList("public_profile"));
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

    private void getFacebookUser(LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.i("LoginActivity", response.toString());
                // Get facebook data from login
//                Bundle bFacebookData = getFacebookData(object);
                parseFacebookData(object);

            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email, gender, birthday, location");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private User parseFacebookData(JSONObject object) {

        try {
            User user = new User();
            String id = object.getString("id");

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            if (object.has("first_name"))
                user.getProfile().setFirstname(object.getString("first_name"));
            if (object.has("last_name"))
                user.getProfile().setLastname(object.getString("last_name"));
            if (object.has("email"))
                user.setEmail(object.getString("email"));
            if (object.has("gender"))
                user.getProfile().setGender(object.getString("gender"));
            if (object.has("birthday"))
                user.getProfile().setBirthdate(object.getString("birthday"));
            if (object.has("location"))
                user.getProfile().setCity(object.getJSONObject("location").getString("name"));

            Log.d(TAG, "parseFacebookData: " + user.toString());
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void startHomeActivity() {
        Intent intent = new Intent(mView, HomeActivity.class);
        mView.startActivity(intent);
        mView.finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
