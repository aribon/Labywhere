package me.aribon.labywhere.backend.provider.network.service;

import java.util.Map;

import me.aribon.labywhere.backend.provider.network.response.AuthResponse;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public interface AuthService {

    @FormUrlEncoded
    @POST("login")
    Observable<AuthResponse> login(@FieldMap Map<String, String> credentials);

    @FormUrlEncoded
    @POST("register")
    Observable<AuthResponse> registration(@FieldMap Map<String, String> credentials);
}
