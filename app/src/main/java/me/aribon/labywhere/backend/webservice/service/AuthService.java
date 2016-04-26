package me.aribon.labywhere.backend.webservice.service;

import android.util.Log;

import java.io.IOException;

import me.aribon.labywhere.backend.webservice.api.AuthApi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 25/04/2016
 *
 * @author Anthony
 */
public class AuthService {

    public static final String TAG = AuthService.class.getSimpleName();

    private static final String ABOUT_SERVER_URL = "http://dev.aribon.me/api/labywhere/v1/";
    private static AuthService instance;
    private AuthApi authApi = null;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(ABOUT_SERVER_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

    private AuthService() {
    }

    public static AuthApi createService() {
        return createService(null);
    }

    public static AuthApi createService(final String authToken) {
        if (authToken != null) {
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    // Request customization: add request headers

                    Log.d(TAG, "loadAccount: " + "Bearer " + authToken);
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", "Bearer " + authToken)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(AuthApi.class);
    }

//    private void createService() {
//        okHttpClient.interceptors().add(new Interceptor() {
//            @Override
//            public Response intercept(Interceptor.Chain chain) throws IOException {
//                Request original = chain.request();
//
//                // Request customization: add request headers
//                Request.Builder requestBuilder = original.newBuilder()
//                        .header("Authorization", "auth-value") // <-- this is the important line
//                        .method(original.method(), original.body());
//
//                Request request = requestBuilder.build();
//                return chain.proceed(request);
//            }
//        });
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(ABOUT_SERVER_URL)
//                .build();
//
//        authApi = retrofit.create(AuthApi.class);
//    }

    public static synchronized void initializeInstance() {
        if (instance == null)
            instance = new AuthService();
    }

    public static synchronized AuthService getInstance() {
        if (instance == null) {
            throw new IllegalStateException(AuthService.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return instance;
    }

    public AuthApi getApi() {
        return authApi;
    }

    public void addAuthorization(String token) {

    }
}
