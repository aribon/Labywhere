package me.aribon.labywhere.backend.webservice.api;

import me.aribon.labywhere.backend.webservice.response.UserListResponse;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by aribon on 16/05/2016.
 */
public interface UserApi {

    @GET("users")
    Observable<UserListResponse> getAllUsers();
}
