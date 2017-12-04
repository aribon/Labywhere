package me.aribon.labywhere.backend.provider.dummy;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.utils.exception.WrongCoupleLoginPassword;

/**
 * Created by anthony.ribon
 * On 16/08/2017
 */

public class AccountDummyProvider extends AbsDummyProvider<User> {

  private static final String EMAIL = "bobby.lamalice@gmail.com";
  private static final String PASSWORD = "denis";

  private static AccountDummyProvider instance;

  private Context context;

  public static AccountDummyProvider getInstance(Context context) {
    if (instance == null)
        instance = new AccountDummyProvider();
    instance.setContext(context);
    return instance;
  }

  private AccountDummyProvider() {
  }

  public void setContext(Context context) {
    this.context = context;
  }

  @Override
  public void post(@NonNull User value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Single<User> get(int id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Flowable<List<User>> getAll() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void put(@NonNull User value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void delete(@NonNull User value) {
    throw new UnsupportedOperationException();
  }

  public Flowable<User> login(@NonNull String email, @NonNull String password) {
    if (email.equals(EMAIL) && password.equals(PASSWORD)) {
      return Flowable.defer(() -> {
        Gson gson = new Gson();
        String rawContent = AccountDummyProvider.this.loadDummyFromAsset(context);
        return Flowable.just(gson.fromJson(rawContent, User.class));
      });
    } else {
      return Flowable.error(new WrongCoupleLoginPassword());
    }
  }

  @Override
  String getFileName() {
    return "account.json";
  }
}
