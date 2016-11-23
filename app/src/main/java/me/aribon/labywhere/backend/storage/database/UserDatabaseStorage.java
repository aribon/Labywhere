package me.aribon.labywhere.backend.storage.database;

import android.support.annotation.NonNull;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import me.aribon.labywhere.backend.model.User;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by aribon from Insign Mobility
 * on 23/11/2016
 */
public class UserDatabaseStorage extends AbsDatabaseStorage<User> {

    private static final String TAG = UserDatabaseStorage.class.getSimpleName();

    private static UserDatabaseStorage instance;

    public static UserDatabaseStorage getInstance() {
        if (instance == null)
            instance = new UserDatabaseStorage();
        return instance;
    }

    private UserDatabaseStorage() {
        super();
    }

    @Override
    public void post(@NonNull User value) {
        realm.executeTransaction(realm -> realm.copyToRealm(value));
    }

    @Override
    public Observable<User> get(int id) {

        return Observable.create(
                new Observable.OnSubscribe<User>() {
                    @Override
                    public void call(Subscriber<? super User> subscriber) {
                        final Realm realm = Realm.getInstance(realmConfig);
                        final User user = realm
                                .where(User.class)
                                .equalTo(User.KEY_ID, id)
                                .findFirst();

                        if (user != null && user.isValid() && user.isLoaded()) {
                            subscriber.onNext(realm.copyFromRealm(user));
                        } else {
                            subscriber.onNext(null);
                        }
                        subscriber.onCompleted();
                        realm.close();
                    }
                }
        ).subscribeOn(Schedulers.computation());

//        return realm.where(User.class)
//                .equalTo(User.KEY_ID, id)
//                .findFirstAsync()
//                .asObservable()
//                .filter(realmObject -> realmObject.isValid() && realmObject.isLoaded())
//                .cast(User.class);

//        return Observable.defer(
//                new Func0<Observable<User>>() {
//                    @Override
//                    public Observable<User> call() {
//                        realm.beginTransaction();
//
//                        User user = realm.where(User.class)
//                                .equalTo(User.KEY_ID, id)
//                                .findFirst();
//
//                        realm.commitTransaction();
//
//                        return Observable.just(user);
//                    }
//                }
//        ).subscribeOn(Schedulers.computation());
    }

    @Override
    public Observable<List<User>> getAll() {

        return Observable.defer(
                new Func0<Observable<List<User>>>() {
                    @Override
                    public Observable<List<User>> call() {
                        realm.beginTransaction();

                        RealmResults<User> userList = realm.where(User.class)
                                .findAll();

                        realm.commitTransaction();

                        return Observable.just(userList);
                    }
                }
        ).subscribeOn(Schedulers.computation());
    }

    @Override
    public void put(@NonNull User value) {
        final Realm realm = Realm.getInstance(realmConfig);
        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(value));
    }

    @Override
    public void delete(int id) {

        realm.executeTransaction(
                realm1 -> {
                    User user = realm1.where(User.class)
                            .equalTo(User.KEY_ID, id)
                            .findFirst();

                    if (user != null) {
                        if (user.getProfile() != null) {
                            user.getProfile().deleteFromRealm();
                        }
                        user.deleteFromRealm();
                    }
                });
    }
}
