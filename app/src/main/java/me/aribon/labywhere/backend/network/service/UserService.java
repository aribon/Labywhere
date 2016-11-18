package me.aribon.labywhere.backend.network.service;

import me.aribon.labywhere.backend.network.response.UserListResponse;
import me.aribon.labywhere.backend.network.response.UserResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by aribon on 16/05/2016.
 */
public interface UserService {

    @GET("users")
    Observable<UserListResponse> getAllUsers();

    @GET("user/{id}")
    Observable<UserResponse> getUser(@Path("id")int id);
}
