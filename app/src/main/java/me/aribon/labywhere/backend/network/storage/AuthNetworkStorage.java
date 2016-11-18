package me.aribon.labywhere.backend.network.storage;

import java.util.Map;

import me.aribon.labywhere.backend.network.NetworkManager;
import me.aribon.labywhere.backend.network.response.AuthResponse;
import me.aribon.labywhere.backend.network.response.UserResponse;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created on 25/04/2016
 *
 * @author Anthony
 */
public class AuthNetworkStorage {

    public static final String TAG = AuthNetworkStorage.class.getSimpleName();

    public static Observable<UserResponse> getAccount(String token) {
        return NetworkManager.createService(me.aribon.labywhere.backend.network.service.AuthService.class, token).getAccount()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<AuthResponse> login(Map<String, String> credentials) {
        return NetworkManager.createService(me.aribon.labywhere.backend.network.service.AuthService.class).login(credentials)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<AuthResponse> register(Map<String, String> body) {
        return NetworkManager.createService(me.aribon.labywhere.backend.network.service.AuthService.class).registration(body)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
