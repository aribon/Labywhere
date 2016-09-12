package me.aribon.labywhere.backend;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import me.aribon.basemvp.view.BaseActivity;
import me.aribon.labywhere.R;

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
        String serverClientId = activity.getString(R.string.google_server_client_id);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken(serverClientId)
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
//        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {

            GoogleSignInAccount account = result.getSignInAccount();

            if (account != null) {
//                String token = account.getIdToken();
                GoogleUser googleUser = parseGoogleData(account);
                listener.onGoogleLoginSuccess(googleUser);
            } else {
                listener.onGoogleLoginFailed("account is empty");
            }

        } else {
            listener.onGoogleLoginFailed("No result: " + result.getStatus());
        }
    }

    private GoogleUser parseGoogleData(@NonNull GoogleSignInAccount account) {

        GoogleUser googleUser = new GoogleUser();

        googleUser.id = account.getId();
        googleUser.token = account.getIdToken();
        googleUser.firstname = account.getDisplayName();
        googleUser.lastname = account.getFamilyName();
        googleUser.email = account.getEmail();
        if (account.getPhotoUrl() != null)
            googleUser.urlPicture = account.getPhotoUrl().toString();

        return googleUser;
    }

//    private String getToken() {
//        String token;
//        try {
//            token = GoogleAuthUtil.getToken(activity, accountName, scope);
//            return token;
//        } catch (GooglePlayServicesAvailabilityException playEx) {
//            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(
//                    playEx.getConnectionStatusCode(),
//                    activity,
//                    RC_SIGN_IN);
//            // Use the dialog to present to the user.
//        } catch (UserRecoverableAutException recoverableException) {
//            Intent recoveryIntent = recoverableException.getIntent();
//            // Use the intent in a custom dialog or just startActivityForResult.
//        } catch (GoogleAuthException authEx) {
//            // This is likely unrecoverable.
//            Log.e(TAG, "Unrecoverable authentication exception: " + authEx.getMesssage(), authEx);
//        } catch (IOException ioEx) {
//            Log.i(TAG, "transient error encountered: " + ioEx.getMessage());
//            doExponentialBackoff();
//        }
//    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public class GoogleUser {
        String id;
        String token;
        String firstname;
        String lastname;
//        String gender;
//        String birthdate;
        String email;
        String city;
        String country;
        String urlPicture;

        @Override
        public String toString() {
            return "GoogleUser{" +
                    "id='" + id + '\'' +
                    ", token='" + token + '\'' +
                    ", firstname='" + firstname + '\'' +
                    ", lastname='" + lastname + '\'' +
//                    ", gender='" + gender + '\'' +
//                    ", birthdate='" + birthdate + '\'' +
                    ", email='" + email + '\'' +
                    ", urlPicture='" + urlPicture + '\'' +
//                    ", city='" + city + '\'' +
//                    ", country='" + country + '\'' +
                    '}';
        }
    }

    public interface OnGoogleManagerListener {
        void onGoogleLoginSuccess(String token, GoogleUser googleUser);
        void onGoogleLoginSuccess(GoogleUser googleUser);
        void onGoogleLoginFailed(String message);
        void onGoogleLoginFailed();
    }

    public static class OnGoogleManagerListenerAdapter implements OnGoogleManagerListener {

        @Override
        public void onGoogleLoginSuccess(String token, GoogleUser googleUser) {

        }

        @Override
        public void onGoogleLoginSuccess(GoogleUser googleUser) {

        }

        @Override
        public void onGoogleLoginFailed(String message) {

        }

        @Override
        public void onGoogleLoginFailed() {

        }
    }
}
