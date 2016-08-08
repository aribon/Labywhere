package me.aribon.labywhere.backend;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import me.aribon.basemvp.view.BaseActivity;

/**
 * Created by aribon from Insign Mobility
 * on 08/08/2016
 */
public class GoogleManager implements GoogleApiClient.OnConnectionFailedListener {

    private static final String TAG = GoogleManager.class.getSimpleName();

    public static final int RC_SIGN_IN = 9001;

    BaseActivity activity;

    GoogleApiClient mGoogleApiClient;

    OnGoogleManagerListener listener;

    public GoogleManager(BaseActivity activity, OnGoogleManagerListener listener) {
        this.activity = activity;
        this.listener = listener;
        initializeGoogleClient();
    }

    private void initializeGoogleClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(activity)
                .enableAutoManage(activity, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    public void login() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        activity.startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    public void logout() {
        if (mGoogleApiClient.isConnected()) {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                        }
                    });
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
        handleSignInResult(result);
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            //TODO success authentication
            if (acct != null) {
                GoogleUser googleUser = parseGoogleData(acct);
                listener.onGoogleLoginSuccess(googleUser);
            } else {
                listener.onGoogleLoginFailed();
            }

        } else {
            listener.onGoogleLoginFailed();
            // Signed out, show unauthenticated UI.
            //TODO error authentication
        }
    }

    private GoogleUser parseGoogleData(@NonNull GoogleSignInAccount acc) {

        GoogleUser googleUser = new GoogleUser();

        if (acc.getPhotoUrl() != null)
            googleUser.urlPicture = acc.getPhotoUrl().toString();
        googleUser.firstname = acc.getDisplayName();
        googleUser.lastname = acc.getFamilyName();
        googleUser.email = acc.getEmail();

        return googleUser;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public class GoogleUser {
        String id;
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
            return "GoogleUser{" +
                    "firstname='" + firstname + '\'' +
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

    public interface OnGoogleManagerListener {
        void onGoogleLoginSuccess(GoogleUser googleUser);
        void onGoogleLoginFailed();
    }

    public static class OnGoogleManagerListenerAdapter implements OnGoogleManagerListener {

        @Override
        public void onGoogleLoginSuccess(GoogleUser googleUser) {

        }

        @Override
        public void onGoogleLoginFailed() {

        }
    }
}
