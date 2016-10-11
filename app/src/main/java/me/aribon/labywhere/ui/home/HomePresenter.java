package me.aribon.labywhere.ui.home;

import android.util.Log;

import io.realm.Realm;
import me.aribon.basemvp.presenter.BasePresenter;
import me.aribon.labywhere.backend.SubscriptionCollector;
import me.aribon.labywhere.backend.UserDataProvider;
import me.aribon.labywhere.backend.model.User;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class HomePresenter extends BasePresenter<HomeActivity> {

    private static final String TAG = HomePresenter.class.getSimpleName();

    private final Realm realm = Realm.getDefaultInstance();
    private Subscription subscription = null;

    @Override
    public void onResume() {
        super.onResume();
//        getAllUsers(AuthPreferences.getAuthToken());
    }

    public void loadData() {
//        if (!UserCacheStorage.getInstance().isExpired()) {
//            User user = loadUserFromCache(12);
//            Log.d(TAG, "loadUserFromCache : " + user.toString());
//        } else
//            loadUserFromDB(12);

        int userIdThatIwant = 12;

        subscription = UserDataProvider.getInstance().getUser(userIdThatIwant)
        .subscribe(new Subscriber<User>() {
            @Override
            public void onCompleted() {
                SubscriptionCollector.getInstance().update();
            }

            @Override
            public void onError(Throwable e) {
                SubscriptionCollector.getInstance().update();
            }

            @Override
            public void onNext(User user) {
                Log.d(TAG, "result: " + user.toString());
            }
        });
        SubscriptionCollector.getInstance().addSubscription(subscription);
    }

//    private void getAllUsers(String token) {
//        subscription =  UserService.getAllUsers(token)
//                .subscribe(new Observer<UserListResponse>() {
//                    @Override
//                    public void onCompleted() {
//                        Log.d(TAG, "getAllUsers onCompleted");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(TAG, "getAllUsers onError: " + e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(UserListResponse userListResponse) {
//
//                        if (userListResponse.isError()) {
//                            //TODO set error
//                        } else {
//                            Log.d(TAG, "onNext: " + userListResponse.getUsers().toString());
//                            //TODO Save on a cache storage
//                            saveUsersOnCache(userListResponse.getUsers());
//                            //TODO Save on a db storage
//                            saveUsersOnDB(userListResponse.getUsers());
//                        }
//                    }
//                });
//    }
//
//
//    //TODO DB TEST
//    private void saveUsersOnDB(final List<User> users) {
//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.copyToRealmOrUpdate(users);
//            }
//        }, new Realm.Transaction.OnSuccess() {
//            @Override
//            public void onSuccess() {
//                Log.d(TAG, "saveOnDB onSuccess");
//                //TODO set code
//            }
//        }, new Realm.Transaction.OnError() {
//            @Override
//            public void onError(Throwable error) {
//                //TODO set error
//                Log.d(TAG, "saveOnDB onError: " + error.getMessage());
//            }
//        });
//    }
//
//    private void loadAllUserFromDB() {
//        Log.d(TAG, "loadUsersFromDB");
//        RealmResults<User> usersResults = realm.where(User.class).findAllAsync();
//        usersResults.addChangeListener(onLoadUsersCompleted);
//    }
//
//    private void loadUserFromDB(int id) {
//        Log.d(TAG, "loadUsersFromDB");
//        RealmResults<User> usersResults = realm.where(User.class).equalTo(User.KEY_ID, id).findAllAsync();
//        usersResults.addChangeListener(onLoadUsersCompleted);
//    }
//
//    private RealmChangeListener onLoadUsersCompleted = new RealmChangeListener<RealmResults<User>>() {
//        @Override
//        public void onChange(RealmResults<User> usersResults) {
//            Log.d(TAG, "onLoadUsersCompleted onComplete");
//            for (User user : usersResults) {
//                Log.d(TAG, "loadUserFromDB : " + user.toString());
//            }
//        }
//    };

//    //TODO CACHE TEST
//    private void saveUsersOnCache(final List<User> users) {
//        Log.d(TAG, "saveUsersOnCache");
//        for (User user : users) {
//            UserCacheStorage.getInstance().put(String.valueOf(user.getId()), user);
//        }
//    }
//
//    private User loadAllUserFromCache() {
//        Log.d(TAG, "loadUserFromCache");
//        return UserCacheStorage.getInstance().getAll();
//    }
//
//    private User loadUserFromCache(final int id) {
//        Log.d(TAG, "loadUserFromCache");
//        return UserCacheStorage.getInstance().get(String.valueOf(id));
//    }
//
//    public void cancelRequest() {
//        if (subscription != null && !subscription.isUnsubscribed())
//            subscription.unsubscribe();
//    }

    /*****************************/
    /*****************************/
    /************TEST*************/
    /*****************************/
    /*****************************/

    // Simple logging to let us know what each source is returning
//    Observable.Transformer<User, User> logSource(final String source) {
//        return userObservable -> userObservable.doOnNext(
//                user -> {
//                    if (user == null) {
//                        Log.e(TAG, "logSource: " + source + " does not have any data.");
//                    } else if (!user.isUpToDate()) {
//                        Log.e(TAG, "logSource: " + source + " has stale data.");
//                    } else {
//                        Log.e(TAG, "logSource: " + source + " has the data you are looking for!");
//                    }
//                });
//    }
//
//    Observable.Transformer<User, User> clearDataIfStale(int id) {
//        return userObservable -> userObservable.doOnNext(user -> {
//            if (user != null && !user.isUpToDate()) {
//                UserCacheStorage.getInstance().delete(String.valueOf(id));
//            }
//        });
//    }

//    //Observable from Cache
//    private Observable<User> getUserFromCache(int id) {
//        return Observable
//                .just(UserCacheStorage.getInstance().get(String.valueOf(id)))
//                .compose(logSource("CACHE"))
//                .compose(clearDataIfStale(id));
//    }
//
//    //Observable from Network
//    private Observable<User> getUserFromNetwork(int id) {
//        return UserService.getUser(id, AuthPreferences.getAuthToken()).flatMap(
//                userResponse -> Observable.just(userResponse.getUser()))
//                .compose(logSource("NETWORK"));
//    }
//
//    //Observable from Network with save on Cache
//    private Observable<User> getUserFromNetworkWithSave(int id) {
//        return UserService.getUser(id, AuthPreferences.getAuthToken()).flatMap(
//                userResponse -> Observable.just(userResponse.getUser()))
//                .doOnNext(this::saveUserOnCache)
//                .compose(logSource("NETWORK"));
//    }
//
//    //Save on Cache
//    private void saveUserOnCache(final User user) {
//        Log.d(TAG, "saveUsersOnCache");
//        if (user != null)
//            UserCacheStorage.getInstance().put(String.valueOf(user.getId()), user);
//    }

//    //Final observable
//    @NonNull
//    private Observable<User> getUserFromSources(int id) {
//        return Observable
//                .concat(getUserFromCache(id), getUserFromNetworkWithSave(id))
//                .takeFirst(user -> user != null && user.isUpToDate());
//    }

}
