package me.aribon.labywhere.backend.webservice;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class WebServiceManager {

    public static final String TAG = WebServiceManager.class.getSimpleName();

    private static final String API_BASE_URL = "http://dev.aribon.me/api/labywhere/v1/";
    private static final String API_TOKEN_TYPE = "Bearer ";

    private static WebServiceManager instance;

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static synchronized void initializeInstance() {
        if (instance == null)
            instance = new WebServiceManager();
    }

    public static synchronized WebServiceManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException(WebServiceManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return instance;
    }

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(Class<S> serviceClass, String token) {
        if (token != null) {
            final String fullToken = API_TOKEN_TYPE + token;
            Log.d(TAG, "createService: token -> " + fullToken);
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", fullToken)
                            .header("Accept", "application/json")
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        OkHttpClient client = httpClient
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }
}
