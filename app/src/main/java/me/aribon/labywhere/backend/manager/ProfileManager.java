package me.aribon.labywhere.backend.manager;

import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.backend.provider.preferences.AccountPreferences;
import me.aribon.labywhere.backend.provider.preferences.AuthPreferences;
import me.aribon.labywhere.backend.provider.network.UserNetworkProvider;
import me.aribon.labywhere.ui.model.UserParcelable;
import rx.Observable;

/**
 * Created by aribon from Insign Mobility
 * on 21/11/2016
 */
public class ProfileManager {

    private static final String TAG = ProfileManager.class.getSimpleName();

    private static ProfileManager instance;

    private UserParcelable userParcelable;

    public static ProfileManager getInstance() {
        if (instance == null)
            instance = new ProfileManager();
        return instance;
    }

    private ProfileManager() {

    }

    public Observable<User> loadAccount() {
        String token = AuthPreferences.getAuthToken();
        return UserNetworkProvider.getInstance(token).getAccount();
    }

    public void setUser(UserParcelable userParcelable) {
        this.userParcelable = userParcelable;
    }

    public UserParcelable getUser() {
        return userParcelable;
    }

    public boolean hasAccount() {
        return AccountPreferences.getAccount() != null;
    }

    public User getAccount() {
        return AccountPreferences.getAccount();
    }
}
