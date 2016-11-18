package me.aribon.labywhere.backend.interactor;

import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.backend.preferences.AccountPreferences;
import me.aribon.labywhere.backend.provider.UserDataProvider;
import rx.Observable;

/**
 * Created by aribon from Insign Mobility
 * on 18/11/2016
 */
public class UserInteractor extends AbsInteractor {

    private static UserInteractor instance;

    public static UserInteractor getInstance() {
        if (instance == null)
            instance= new UserInteractor();
        return instance;
    }

    private UserInteractor() {
    }

    public Observable<User> retrieveUser(int id) {
        if (AccountPreferences.getAccount() == null) {
            return Observable.error(new Exception("No Account logged, please log you and try again"));
        }

        return UserDataProvider.getInstance().getUser(id);
    }

    public Observable<User> retrieveAccount() {
        return UserDataProvider.getInstance().getAccount();
    }
}
