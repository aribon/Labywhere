package me.aribon.labywhere.backend.interactor;

import android.support.annotation.NonNull;

import java.util.List;

import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.backend.preferences.AccountPreferences;
import me.aribon.labywhere.backend.preferences.AuthPreferences;
import me.aribon.labywhere.backend.provider.cache.UserCacheProvider;
import me.aribon.labywhere.backend.provider.database.UserDatabaseProvider;
import me.aribon.labywhere.backend.provider.network.UserNetworkProvider;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by aribon
 * on 18/11/2016
 */
public class UserInteractor extends AbsInteractor<User> {

    private static final String TAG = UserInteractor.class.getSimpleName();

    private static UserInteractor instance;

    public static UserInteractor getInstance() {
        if (instance == null)
            instance= new UserInteractor();
        return instance;
    }

    private UserInteractor() {

    }

    @Override
    public void create(@NonNull User value) {

    }

    @Override
    public Observable<InteractorResponse<User>> retrieve(int id) {
        if (AccountPreferences.getAccount() == null) {
            return Observable.error(new Exception("No Account logged, please log you and try again"));
        }

        Observable<InteractorResponse<User>> cacheObservable = /*Observable.empty();*/
                UserCacheProvider.getInstance().get(id)
                        .compose(logSource("CACHE"))
                        .compose(clearCacheDataIfStale(id))
                        .flatMap(
                            new Func1<User, Observable<InteractorResponse<User>>>() {
                                @Override
                                public Observable<InteractorResponse<User>> call(User user) {
                                    return Observable.just(InteractorResponse.createCacheResponse(user));
                                }
                            }
                        );

        Observable<InteractorResponse<User>> databaseObservable =
                UserDatabaseProvider.getInstance().get(id)
                .compose(logSource("DATABASE"))
                .flatMap(
                        new Func1<User, Observable<InteractorResponse<User>>>() {
                            @Override
                            public Observable<InteractorResponse<User>> call(User user) {
                                return Observable.just(InteractorResponse.createDatabaseResponse(user));
                            }
                        }
                );

        Observable<InteractorResponse<User>> networkObservable =
                UserNetworkProvider.getInstance(AuthPreferences.getAuthToken()).get(id)
                        .compose(logSource("NETWORK"))
                        .doOnNext((user) -> {
                            if (user != null) {
                                UserDatabaseProvider.getInstance().put(user);
                                UserCacheProvider.getInstance().put(user);
                            }
                        })
                        .flatMap(
                                new Func1<User, Observable<InteractorResponse<User>>>() {
                                    @Override
                                    public Observable<InteractorResponse<User>> call(User user) {
                                        return Observable.just(InteractorResponse.createNetworkResponse(user));
                                    }
                                }
                        );

        return Observable
                .concat(cacheObservable, databaseObservable, networkObservable)
                .takeFirst(UserInteractor.this::ifStale);
    }

    @Override
    public Observable<InteractorResponse<List<User>>> retrieveAll() {
        return null;
    }

    @Override
    public void update(@NonNull User value) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    Observable.Transformer<User, User> clearCacheDataIfStale(int id) {
        return userObservable -> userObservable.doOnNext(user -> {
            if (user != null && !user.isUpToDate()) {
                UserCacheProvider.getInstance().delete(user);
            }
        });
    }
}
