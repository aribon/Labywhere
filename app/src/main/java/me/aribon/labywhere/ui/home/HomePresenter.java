package me.aribon.labywhere.ui.home;

import android.util.Log;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import me.aribon.basemvp.presenter.BasePresenter;
import me.aribon.labywhere.backend.cache.UserCacheStorage;
import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.backend.preferences.AuthPreferences;
import me.aribon.labywhere.backend.webservice.response.UserListResponse;
import me.aribon.labywhere.backend.webservice.service.UserService;
import rx.Observer;
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
        getAllUsers(AuthPreferences.getAuthToken());
    }

    private void getAllUsers(String token) {
        subscription = UserService.getAllUsers(token, new Observer<UserListResponse>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "getAllUsers onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "getAllUsers onError: " + e.getMessage());
            }

            @Override
            public void onNext(UserListResponse userListResponse) {

                if (userListResponse.isError()) {
                    //TODO set error
                } else {
                    Log.d(TAG, "onNext: " + userListResponse.getUsers().toString());
                    //TODO Save on a db storage
                    saveUsersOnDB(userListResponse.getUsers());
                    //TODO Save on a cache storage
                    saveUsersOnCache(userListResponse.getUsers());
                }
            }
        });
    }


    //TODO DB TEST
    private void saveUsersOnDB(final List<User> users) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(users);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.d(TAG, "saveOnDB onSuccess");
//                if (!UserCacheStorage.getInstance().isExpired()) {
//                    User user = loadUserFromCache(12);
//                    Log.d(TAG, "loadUserFromCache : " + user.toString());
//                } else
                    loadUserFromDB(12);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                //TODO set error
                Log.d(TAG, "saveOnDB onError: " + error.getMessage());
            }
        });
    }

    private void loadAllUserFromDB() {
        Log.d(TAG, "loadUsersFromDB");
        RealmResults<User> usersResults = realm.where(User.class).findAllAsync();
        usersResults.addChangeListener(onLoadUsersCompleted);
    }

    private void loadUserFromDB(int id) {
        Log.d(TAG, "loadUsersFromDB");
        RealmResults<User> usersResults = realm.where(User.class).equalTo(User.KEY_ID, id).findAllAsync();
        usersResults.addChangeListener(onLoadUsersCompleted);
    }

    private RealmChangeListener onLoadUsersCompleted = new RealmChangeListener<RealmResults<User>>() {
        @Override
        public void onChange(RealmResults<User> usersResults) {
            Log.d(TAG, "onLoadUsersCompleted onComplete");
            for (User user : usersResults) {
                Log.d(TAG, "loadUserFromDB : " + user.toString());
            }
        }
    };

    //TODO CACHE TEST
    private void saveUsersOnCache(final List<User> users) {
        Log.d(TAG, "saveUsersOnCache");
        for (User user : users) {
            UserCacheStorage.getInstance().put(String.valueOf(user.getId()), user);
        }
    }

    private User loadAllUserFromCache() {
        Log.d(TAG, "loadUserFromCache");
        return UserCacheStorage.getInstance().getAll();
    }

    private User loadUserFromCache(final int id) {
        Log.d(TAG, "loadUserFromCache");
        return UserCacheStorage.getInstance().get(String.valueOf(id));
    }

    public void cancelRequest() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
    }
}
