package me.aribon.labywhere.backend.webservice.api;

import me.aribon.labywhere.backend.webservice.response.UserListResponse;
import me.aribon.labywhere.backend.webservice.response.UserResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by aribon on 16/05/2016.
 */
public interface UserApi {

    @GET("users")
    Observable<UserListResponse> getAllUsers();

    @GET("user/{id}")
    Observable<UserResponse> getUser(@Path("id")int id);
}
