package me.aribon.labywhere.backend.storage.database.specification;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by aribon from Insign Mobility
 * on 24/11/2016
 */
public interface RealmSpecification<T extends RealmModel> extends Specification {
    RealmResults<T> toRealmResults(Realm realm);
}
