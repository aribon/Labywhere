package me.aribon.labywhere.backend.network.storage;

import java.util.List;

import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.backend.network.response.UserListResponse;
import me.aribon.labywhere.backend.network.response.UserResponse;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by aribon on 16/05/2016.
 */
public class UserNetworkStorage extends AbsNetworkStorage<User> {

    private static final String TAG = UserNetworkStorage.class.getSimpleName();

    private static UserNetworkStorage instance;

    public static UserNetworkStorage getInstance(String token) {
        if (instance == null)
            instance = new UserNetworkStorage(token);
        return instance;
    }

    private String token;

    private UserNetworkStorage(String token) {
        this.token = token;
    }

    public Observable<User> getAccount() {
        return createService(me.aribon.labywhere.backend.network.service.AuthService.class, token).getAccount()
                .subscribeOn(Schedulers.io())
                .flatMap(
                        new Func1<UserResponse, Observable<User>>() {
                            @Override
                            public Observable<User> call(UserResponse userResponse) {
                                return Observable.just(userResponse.getUser());
                            }
                        }
                );
    }

    @Override
    public Observable<User> get(int id) {
        return createService(me.aribon.labywhere.backend.network.service.UserService.class, token).getUser(id)
                .subscribeOn(Schedulers.io())
                .flatMap(
                        new Func1<UserResponse, Observable<User>>() {
                            @Override
                            public Observable<User> call(UserResponse userResponse) {
                                return Observable.just(userResponse.getUser());
                            }
                        }
                );
    }

    @Override
    public Observable<List<User>> getAll() {
        return createService(me.aribon.labywhere.backend.network.service.UserService.class, token).getAllUsers()
                .subscribeOn(Schedulers.io())
                .flatMap(
                        new Func1<UserListResponse, Observable<List<User>>>() {
                            @Override
                            public Observable<List<User>> call(UserListResponse userListResponse) {
                                return Observable.just(userListResponse.getUsers());
                            }
                        }
                );
    }

    @Override
    public void put(User value) {

    }

    @Override
    public void delete(int key) {

    }
}
