package me.aribon.labywhere.backend.network.storage;

import me.aribon.labywhere.backend.network.NetworkManager;
import me.aribon.labywhere.backend.network.response.UserListResponse;
import me.aribon.labywhere.backend.network.response.UserResponse;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by aribon on 16/05/2016.
 */
public class UserNetworkStorage {

    private static final String TAG = UserNetworkStorage.class.getSimpleName();

    public static Observable<UserListResponse> getAllUsers(String token) {
        return NetworkManager.createService(me.aribon.labywhere.backend.network.service.UserService.class, token).getAllUsers()
                .subscribeOn(Schedulers.io());
    }

    public static Observable<UserResponse> getUser(int id, String token) {
        return NetworkManager.createService(me.aribon.labywhere.backend.network.service.UserService.class, token).getUser(id)
                .subscribeOn(Schedulers.io());
    }
}
