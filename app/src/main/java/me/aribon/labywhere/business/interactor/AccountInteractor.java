package me.aribon.labywhere.business.interactor;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import me.aribon.labywhere.LabywhereApplication;
import me.aribon.labywhere.backend.manager.ProfileManager;
import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.backend.provider.dummy.AccountDummyProvider;
import me.aribon.labywhere.backend.provider.preferences.AccountPreferences;
import me.aribon.labywhere.business.mapper.UserMapper;
import me.aribon.labywhere.ui.model.UserParcelable;
import me.aribon.labywhere.utils.exception.NoTokenException;
import me.aribon.labywhere.utils.exception.NoUserException;
import me.aribon.labywhere.utils.exception.WrongCoupleLoginPassword;

/**
 * Created by anthony.ribon
 * On 15/08/2017
 */

public class AccountInteractor {

    private static final AccountInteractor ourInstance = new AccountInteractor();

    public static AccountInteractor getInstance() {
        return ourInstance;
    }

    private AccountInteractor() {
    }

    public Completable checkToken() {

        String token = AccountPreferences.getUserToken();

        // TODO: 15/08/2017 check token with server request ?

        if (token != null) {
            return Completable.complete();
        } else {
            return Completable.error(new NoTokenException());
        }
    }

    public Completable doLoginByToken() {

        String token = AccountPreferences.getUserToken();

        // TODO: 15/08/2017 check token with server request ?

        // TODO: 15/08/2017 call repository account

        return Completable.complete();
    }

    public Completable doLoginByCredentials(UserParcelable userParcelable) {
        if (userParcelable != null) {
            return AccountDummyProvider
                    .getInstance(LabywhereApplication.getContext())
                    .login(userParcelable.getEmail(), userParcelable.getPassword())
                    .subscribeOn(Schedulers.computation())
                    .doOnNext(new Consumer<User>() {
                        @Override
                        public void accept(User user) throws Exception {
                            ProfileManager.getInstance().setUser(new UserMapper().toView(user));
                            //todo save token -> AuthPreferences.setAuthToken(authResponse.getToken());
                        }
                    }).flatMapCompletable(
                            new Function<User, CompletableSource>() {
                                @Override
                                public CompletableSource apply(User user) throws Exception {
                                    if (user != null) {
                                        ProfileManager.getInstance().setUser(new UserMapper().toView(user));
                                        return Completable.complete();
                                    } else {
                                        return Completable.error(new WrongCoupleLoginPassword());
                                    }
                                }
                            }
                    );
        } else {
            return Completable.error(new NoUserException());
        }
    }

}
