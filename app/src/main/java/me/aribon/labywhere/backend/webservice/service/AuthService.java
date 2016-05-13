package me.aribon.labywhere.backend.webservice.service;

import java.util.Map;

import me.aribon.labywhere.backend.webservice.WebServiceManager;
import me.aribon.labywhere.backend.webservice.api.AuthApi;
import me.aribon.labywhere.backend.webservice.response.AuthResponse;
import me.aribon.labywhere.backend.webservice.response.UserResponse;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created on 25/04/2016
 *
 * @author Anthony
 */
public class AuthService {

    public static final String TAG = AuthService.class.getSimpleName();

    public static Subscription getAccount(String token, Observer<UserResponse> observer) {
        return WebServiceManager.createService(AuthApi.class, token).getAccount()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static Subscription login(Map<String, String> credentials, Observer<AuthResponse> observer) {
        return WebServiceManager.createService(AuthApi.class).login(credentials)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public static Subscription register(Map<String, String> body, Observer<AuthResponse> observer) {
        return WebServiceManager.createService(AuthApi.class).registration(body)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
