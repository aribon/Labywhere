package me.aribon.labywhere.backend.manager;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.Arrays;

import me.aribon.basemvp.view.BaseActivity;

/**
 * Created by aribon from Insign Mobility
 * on 08/08/2016
 */
public class FacebookManager {

    private static final String TAG = FacebookManager.class.getSimpleName();

    BaseActivity activity;
    CallbackManager callbackManager;

    OnFacebookManagerListener listener;

//    public FacebookManager(BaseActivity activity) {
//        this.activity = activity;
//        initializeLoginManager();
//    }

    public FacebookManager(BaseActivity activity, OnFacebookManagerListener listener) {
        this.activity = activity;
        this.listener = listener;
        initializeLoginManager();
    }

    private void initializeLoginManager() {
        this.callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, defaultFacebookCallback);
    }

//    public void setOnFacebookLoginListener(OnFacebookManagerListener listener) {
//        this.listener = listener;
//    }

    FacebookCallback<LoginResult> defaultFacebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            //TODO get data by request type (user_info, friends, etc...)
            getFacebookUser(loginResult);
        }

        @Override
        public void onCancel() {
            Log.d(TAG, "onCancel: ");
        }

        @Override
        public void onError(FacebookException error) {
            Log.e(TAG, "onError: " + error.getLocalizedMessage());
            listener.onFacebookLoginFailed();
        }
    };

    public void login() {
        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile"));
    }

    public void logout() {
        LoginManager.getInstance().logOut();
    }

    private void getFacebookUser(final LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Log.i("LoginActivity", response.toString());
                FacebookUser facebookUser = parseFacebookData(object);
                facebookUser.token = loginResult.getAccessToken().getToken();
                listener.onFacebookLoginSuccess(facebookUser);

            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email, gender, birthday, location");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private FacebookUser parseFacebookData(JSONObject object) {

        try {
            FacebookUser facebookUser = new FacebookUser();

            if (object.has("id"))
                facebookUser.id = object.getString("id");
            if (object.has("picture"))
                facebookUser.urlPicture = "https://graph.facebook.com/" + facebookUser.id + "/picture?width=200&height=150";
            if (object.has("first_name"))
                facebookUser.firstname = object.getString("first_name");
            if (object.has("last_name"))
                facebookUser.lastname = object.getString("last_name");
            if (object.has("email"))
                facebookUser.email = object.getString("email");
            if (object.has("gender"))
                facebookUser.gender = object.getString("gender");
            if (object.has("birthday"))
                facebookUser.birthdate = object.getString("birthday");

            return facebookUser;
        } catch (JSONException e) {
            e.printStackTrace();
            return new FacebookUser();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    public class FacebookUser {
        String id;
        String token;
        String firstname;
        String lastname;
        String gender;
        String birthdate;
        String email;
        String city;
        String country;
        String urlPicture;

        @Override
        public String toString() {
            return "FacebookUser{" +
                    "id='" + id + '\'' +
                    ", token='" + token + '\'' +
                    ", firstname='" + firstname + '\'' +
                    ", lastname='" + lastname + '\'' +
                    ", gender='" + gender + '\'' +
                    ", birthdate='" + birthdate + '\'' +
                    ", email='" + email + '\'' +
                    ", urlPicture='" + urlPicture + '\'' +
                    ", city='" + city + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }
    }

    public interface OnFacebookManagerListener {
        void onFacebookLoginSuccess(String token, FacebookUser facebookUser);
        void onFacebookLoginSuccess(FacebookUser facebookUser);
        void onFacebookLoginFailed(String message);
        void onFacebookLoginFailed();
    }

    public static class OnFacebookManagerListenerAdapter implements OnFacebookManagerListener {

        @Override
        public void onFacebookLoginSuccess(String token, FacebookUser facebookUser) {

        }

        @Override
        public void onFacebookLoginSuccess(FacebookUser facebookUser) {

        }

        @Override
        public void onFacebookLoginFailed(String message) {

        }

        @Override
        public void onFacebookLoginFailed() {

        }
    }
}
