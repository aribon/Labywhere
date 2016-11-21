package me.aribon.labywhere.backend.network.storage;

import java.util.List;
import java.util.Map;

import me.aribon.labywhere.backend.network.response.AuthResponse;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created on 25/04/2016
 *
 * @author Anthony
 */
public class AuthNetworkStorage extends AbsNetworkStorage<AuthResponse> {

    public static final String TAG = AuthNetworkStorage.class.getSimpleName();

    private static AuthNetworkStorage instance;

    public static AuthNetworkStorage getInstance() {
        if (instance == null)
            instance = new AuthNetworkStorage();
        return instance;
    }

    private AuthNetworkStorage() {
    }

    public Observable<AuthResponse> login(Map<String, String> credentials) {
        return createService(me.aribon.labywhere.backend.network.service.AuthService.class).login(credentials)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<AuthResponse> register(Map<String, String> body) {
        return createService(me.aribon.labywhere.backend.network.service.AuthService.class).registration(body)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<AuthResponse> get(int id) {
        return null;
    }

    @Override
    public Observable<List<AuthResponse>> getAll() {
        return null;
    }

    @Override
    public void put(AuthResponse value) {

    }

    @Override
    public void delete(int key) {

    }
}
