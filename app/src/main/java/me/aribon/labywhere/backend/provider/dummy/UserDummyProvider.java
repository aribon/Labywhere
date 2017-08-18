package me.aribon.labywhere.backend.provider.dummy;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import me.aribon.labywhere.backend.model.User;
import rx.Observable;

/**
 * Created by anthony.ribon
 * On 26/06/2017
 */

public class UserDummyProvider extends AbsDummyProvider<User> {

  private static final String TAG = UserDummyProvider.class.getSimpleName();

  private static UserDummyProvider instance;

  private Context context;

  public static UserDummyProvider getInstance(Context context) {
    if (instance == null)
        instance = new UserDummyProvider();
    instance.setContext(context);
    return instance;
  }

  private UserDummyProvider() {
  }

  public void setContext(Context context) {
    this.context = context;
  }

  @Override
  public void post(@NonNull User value) {

  }

  @Override
  public Observable<User> get(int id) {
    User user = null;

    Gson gson = new Gson();

    String rawContent = loadDummyFromAsset(context);
    Type listType = new TypeToken<ArrayList<User>>(){}.getType();
    List<User> users = gson.fromJson(rawContent, listType);

    if (users != null) {
      for (int i = 0; i < users.size(); i++) {
        User tmpUser = users.get(i);
        if (tmpUser != null && id == tmpUser.getId()) {
          user = tmpUser;
          break;
        }
      }
    }

    return Observable.just(user);
  }

  @Override
  public Observable<List<User>> getAll() {

    Gson gson = new Gson();

    String rawContent = loadDummyFromAsset(context);
    Type listType = new TypeToken<ArrayList<User>>(){}.getType();
    List<User> users = gson.fromJson(rawContent, listType);

    return Observable.just(users);
  }

  @Override
  public void put(@NonNull User value) {

  }

  @Override
  public void delete(@NonNull User value) {

  }

  @Override
  String getFileName() {
    return "users.json";
  }
}
