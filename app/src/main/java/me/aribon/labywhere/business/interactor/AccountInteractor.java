package me.aribon.labywhere.business.interactor;

import android.support.annotation.NonNull;
import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.backend.provider.dummy.AccountDummyProvider;
import me.aribon.labywhere.backend.provider.preferences.AccountPreferences;
import me.aribon.labywhere.LabywhereApplication;
import me.aribon.labywhere.business.mapper.UserMapper;
import me.aribon.labywhere.ui.model.UserParcelable;
import me.aribon.labywhere.utils.exception.NoUserException;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

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

  public Observable<Boolean> checkToken() {

    String token = AccountPreferences.getUserToken();

    // TODO: 15/08/2017 check token with server request ?

    return Observable.defer(() -> Observable.just(token != null));
  }

  public Observable<Boolean> doLoginByToken() {

    String token = AccountPreferences.getUserToken();

    // TODO: 15/08/2017 check token with server request ?

    // TODO: 15/08/2017 call repository account

    return Observable.defer(() -> Observable.just(true));
  }

  public Observable<UserParcelable> doLoginByCredentials(UserParcelable userParcelable) {
    if (userParcelable != null) {
      return AccountDummyProvider
          .getInstance(LabywhereApplication.getContext())
          .login(userParcelable.getEmail(), userParcelable.getPassword())
          .subscribeOn(Schedulers.computation())
          .doOnNext(user -> {
            //todo save token -> AuthPreferences.setAuthToken(authResponse.getToken());
          })
          .flatMap(new Func1<User, Observable<UserParcelable>>() {
            @Override
            public Observable<UserParcelable> call(User user) {
              return Observable.just(new UserMapper().toView(user));
            }
          });
    } else {
      return Observable.error(new NoUserException());
    }
  }

}
