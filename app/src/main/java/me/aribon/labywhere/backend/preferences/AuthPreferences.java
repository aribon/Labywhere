package me.aribon.labywhere.backend.preferences;

/**
 * Created on 25/04/2016
 *
 * @author Anthony
 */
public class AuthPreferences {

    public static final String TAG = AuthPreferences.class.getSimpleName();

    private static final String KEY_AUTH_TOKEN = "auth_token";

    public static void setAuthToken(String authToken) {
        PreferencesManager.getInstance().getSharedPreferences().edit()
                .putString(KEY_AUTH_TOKEN, authToken)
                .commit();
    }

    public static String getAuthToken() {
        return PreferencesManager.getInstance().getSharedPreferences().getString(KEY_AUTH_TOKEN, null);
    }
}
