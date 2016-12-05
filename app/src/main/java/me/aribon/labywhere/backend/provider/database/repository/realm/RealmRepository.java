package me.aribon.labywhere.backend.provider.database.repository.realm;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmResults;
import me.aribon.labywhere.backend.provider.database.repository.Repository;
import me.aribon.labywhere.backend.provider.database.specification.RealmSpecification;
import me.aribon.labywhere.backend.provider.database.specification.Specification;

/**
 * Created by aribon from Insign Mobility
 * on 24/11/2016
 */
public class RealmRepository<T extends RealmModel> implements Repository<T> {

    private RealmConfiguration realmConfig;

    public RealmRepository(RealmConfiguration realmConfig) {
        this.realmConfig = realmConfig;
    }

    @Override
    public void insert(T item) {
        final Realm realm = Realm.getInstance(realmConfig);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(item);
            }
        });

        realm.close();
    }

    @Override
    public void insert(Iterable<T> items) {
        final Realm realm = Realm.getInstance(realmConfig);

        realm.executeTransaction(
                new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealm(items);
                    }
                }
        );

        realm.close();
    }

    @Override
    public void update(T item) {
        final Realm realm = Realm.getInstance(realmConfig);

        realm.executeTransaction(
                new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(item);
                    }
                }
        );

        realm.close();
    }

    @Override
    public void delete(T item) {
        final Realm realm = Realm.getInstance(realmConfig);

        realm.executeTransaction(
                new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        RealmObject.deleteFromRealm(item);
                    }
                }
        );

        realm.close();
    }

    @Override
    public void delete(Specification specification) {
        final RealmSpecification realmSpecification = (RealmSpecification) specification;

        final Realm realm = Realm.getInstance(realmConfig);
        final RealmResults<T> realmResults = realmSpecification.toRealmResults(realm);

        realmResults.deleteAllFromRealm();
    }

    @Override
    public List<T> query(Specification specification) {
        final RealmSpecification realmSpecification = (RealmSpecification) specification;

        final Realm realm = Realm.getInstance(realmConfig);
        final RealmResults<T> realmResults = realmSpecification.toRealmResults(realm);

        final List<T> values = new ArrayList<>();

        values.addAll(realm.copyFromRealm(realmResults));

        realm.close();

        return values;
    }
}
