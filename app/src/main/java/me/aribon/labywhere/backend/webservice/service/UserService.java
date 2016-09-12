package me.aribon.labywhere.backend.webservice.service;

import me.aribon.labywhere.backend.webservice.WebserviceManager;
import me.aribon.labywhere.backend.webservice.api.UserApi;
import me.aribon.labywhere.backend.webservice.response.UserListResponse;
import me.aribon.labywhere.backend.webservice.response.UserResponse;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by aribon on 16/05/2016.
 */
public class UserService {

    private static final String TAG = UserService.class.getSimpleName();

    public static Observable<UserListResponse> getAllUsers(String token) {
        return WebserviceManager.createService(UserApi.class, token).getAllUsers()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<UserResponse> getUser(int id, String token) {
        return WebserviceManager.createService(UserApi.class, token).getUser(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
