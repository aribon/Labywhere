package me.aribon.labywhere.backend.provider.network;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.backend.provider.AbsStorage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public abstract class AbsNetworkProvider<V> extends AbsStorage<V> {

    public static final String TAG = AbsNetworkProvider.class.getSimpleName();

    private static final String API_BASE_URL = "http://dev.aribon.me/api/labywhere/v1/";
    private static final String API_TOKEN_TYPE = "Bearer ";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

    public final <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public final <S> S createService(Class<S> serviceClass, String token) {
        if (token != null) {
            final String fullToken = API_TOKEN_TYPE + token;
            Log.d(TAG, "createService: token -> " + fullToken);
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", fullToken)
                        .header("Accept", "application/json")
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            });
        }

        OkHttpClient client = httpClient
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

    @Override
    public void post(@NonNull V value) {

    }

    @Override
    public Single<User> get(int id) {
        return null;
    }

    @Override
    public Flowable<List<User>> getAll() {
        return null;
    }

    @Override
    public void put(@NonNull V value) {

    }

    @Override
    public void delete(@NonNull V value) {

    }
}
