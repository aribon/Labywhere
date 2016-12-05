package me.aribon.labywhere.backend.provider.database;

import android.support.annotation.NonNull;

import java.util.List;

import io.realm.RealmConfiguration;
import me.aribon.labywhere.backend.model.Profile;
import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.backend.provider.database.helper.RealmDatabaseHelper;
import me.aribon.labywhere.backend.provider.database.repository.realm.RealmRepository;
import me.aribon.labywhere.backend.provider.database.specification.UserAllRealmSpecification;
import me.aribon.labywhere.backend.provider.database.specification.UserByIdRealmSpecification;
import rx.Observable;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by aribon from Insign Mobility
 * on 23/11/2016
 */
public class UserDatabaseProvider extends AbsDatabaseProvider<User> {

    private static final String TAG = UserDatabaseProvider.class.getSimpleName();

    private static UserDatabaseProvider instance;

    public static UserDatabaseProvider getInstance() {
        if (instance == null)
            instance = new UserDatabaseProvider();
        return instance;
    }

    private RealmConfiguration realmConfiguration = new RealmDatabaseHelper().getConfig(RealmDatabaseHelper.RealmConfigType.DEFAULT);

    private UserDatabaseProvider() {
        super();
    }

    @Override
    public void post(@NonNull User value) {
//        realm.executeTransaction(realm -> realm.copyToRealm(value));
        new RealmRepository<User>(realmConfiguration).insert(value);
    }

    @Override
    public Observable<User> get(int id) {

        return Observable.defer(
                new Func0<Observable<User>>() {
                    @Override
                    public Observable<User> call() {
//                        realm.beginTransaction();
//
//                        RealmResults<User> userList = realm.where(User.class)
//                                .findAll();
//
//                        realm.commitTransaction();

//                        return Observable.just(userList);
                        User user = new RealmRepository<User>(realmConfiguration).query(new UserByIdRealmSpecification(id)).get(0);
                        return Observable.just(user);
                    }
                }
        ).subscribeOn(Schedulers.computation());

//        return Observable.create(
//                new Observable.OnSubscribe<User>() {
//                    @Override
//                    public void call(Subscriber<? super User> subscriber) {
////                        final Realm realm = Realm.getInstance(realmConfig);
////                        final User user = realm
////                                .where(User.class)
////                                .equalTo(User.KEY_ID, id)
////                                .findFirst();
////
//                        User user = new RealmRepository<User>(realmConfig).query(new UserByIdRealmSpecification(id)).get(0);
////                        if (user != null && user.isValid() && user.isLoaded()) {
////                            subscriber.onNext(user);
////                        } else {
////                            subscriber.onNext(null);
////                        }
//                        subscriber.onNext(user);
//                        subscriber.onCompleted();
//                    }
//                }
//        ).subscribeOn(Schedulers.computation());

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
//                        realm.beginTransaction();
//
//                        RealmResults<User> userList = realm.where(User.class)
//                                .findAll();
//
//                        realm.commitTransaction();

//                        return Observable.just(userList);
                        List<User> users = new RealmRepository<User>(realmConfiguration).query(new UserAllRealmSpecification());
                        return Observable.just(users);
                    }
                }
        ).subscribeOn(Schedulers.computation());
    }

    @Override
    public void put(@NonNull User value) {
//        final Realm realm = Realm.getInstance(realmConfig);
//        realm.executeTransaction(realm1 -> realm1.copyToRealmOrUpdate(value));
        new RealmRepository<User>(realmConfiguration).update(value);
    }

    @Override
    public void delete(@NonNull User value) {

//        realm.executeTransaction(
//                realm1 -> {
//                    User user = realm1.where(User.class)
//                            .equalTo(User.KEY_ID, id)
//                            .findFirst();
//
//                    if (user != null) {
//                        if (user.getProfile() != null) {
//                            user.getProfile().deleteFromRealm();
//                        }
//                        user.deleteFromRealm();
//                    }
//                });
        new RealmRepository<Profile>(realmConfiguration).delete(value.getProfile());
        new RealmRepository<User>(realmConfiguration).delete(value);
    }
}
