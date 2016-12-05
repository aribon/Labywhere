package me.aribon.labywhere.backend.provider.database.specification;

import io.realm.Realm;
import io.realm.RealmResults;
import me.aribon.labywhere.backend.model.User;

/**
 * Created by aribon from Insign Mobility
 * on 25/11/2016
 */
public class UserByIdRealmSpecification implements RealmSpecification<User> {

    private final int id;

    public UserByIdRealmSpecification(final int id) {
        this.id = id;
    }

    @Override
    public RealmResults<User> toRealmResults(Realm realm) {
        return realm.where(User.class)
                .equalTo(User.KEY_ID, id)
                .findAll();
    }
}
