package me.aribon.labywhere.backend.storage.network.storage;

import java.util.Map;

import me.aribon.labywhere.backend.storage.network.response.AuthResponse;
import me.aribon.labywhere.backend.storage.network.service.AuthService;
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
        return createService(AuthService.class).login(credentials)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<AuthResponse> register(Map<String, String> body) {
        return createService(AuthService.class).registration(body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
