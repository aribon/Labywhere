package me.aribon.labywhere.backend.manager;

import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.backend.network.storage.UserNetworkStorage;
import me.aribon.labywhere.backend.preferences.AccountPreferences;
import me.aribon.labywhere.backend.preferences.AuthPreferences;
import rx.Observable;

/**
 * Created by aribon from Insign Mobility
 * on 21/11/2016
 */
public class ProfileManager {

    private static final String TAG = ProfileManager.class.getSimpleName();

    private static ProfileManager instance;

    public static ProfileManager getInstance() {
        if (instance == null)
            instance = new ProfileManager();
        return instance;
    }

    private ProfileManager() {

    }

    public Observable<User> loadAccount() {
        return UserNetworkStorage.getInstance(AuthPreferences.getAuthToken()).getAccount();
    }

    public boolean hasAccount() {
        return AccountPreferences.getAccount() != null;
    }

    public User getAccount() {
        return AccountPreferences.getAccount();
    }
}
