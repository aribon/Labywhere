package me.aribon.labywhere.backend.webservice.service;

import java.util.Map;

import me.aribon.labywhere.backend.webservice.WebServiceManager;
import me.aribon.labywhere.backend.webservice.api.AuthApi;
import me.aribon.labywhere.backend.webservice.response.AuthResponse;
import me.aribon.labywhere.backend.webservice.response.UserResponse;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created on 25/04/2016
 *
 * @author Anthony
 */
public class AuthService {

    public static final String TAG = AuthService.class.getSimpleName();

    public static Observable<UserResponse> getAccount(String token) {
        return WebServiceManager.createService(AuthApi.class, token).getAccount()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<AuthResponse> login(Map<String, String> credentials) {
        return WebServiceManager.createService(AuthApi.class).login(credentials)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<AuthResponse> register(Map<String, String> body) {
        return WebServiceManager.createService(AuthApi.class).registration(body)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
