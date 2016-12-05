package me.aribon.labywhere.backend.provider.network;

import java.util.Map;

import me.aribon.labywhere.backend.provider.network.response.AuthResponse;
import me.aribon.labywhere.backend.provider.network.service.AuthService;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created on 25/04/2016
 *
 * @author Anthony
 */
public class AuthNetworkProvider extends AbsNetworkProvider<AuthResponse> {

    public static final String TAG = AuthNetworkProvider.class.getSimpleName();

    private static AuthNetworkProvider instance;

    public static AuthNetworkProvider getInstance() {
        if (instance == null)
            instance = new AuthNetworkProvider();
        return instance;
    }

    private AuthNetworkProvider() {
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
