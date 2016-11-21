package me.aribon.labywhere.backend.cache;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import me.aribon.labywhere.LabywhereApplication;
import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.utils.FileUtils;
import rx.Observable;

import static android.R.attr.key;

/**
 * Created by aribon on 26/05/2016.
 */
public class UserCacheStorage extends AbsCacheStorage<User> {

    private static final String TAG = UserCacheStorage.class.getSimpleName();

    private static final String DEFAULT_FILE_NAME = "user";

    private static UserCacheStorage instance = null;

    private File cacheDir;

    public static UserCacheStorage getInstance() {
        if (instance == null)
            instance = new UserCacheStorage();
        return instance;
    }

    private UserCacheStorage() {
        cacheDir = LabywhereApplication.getContext().getCacheDir();
    }

    @Override
    public Observable<User> get(int id) {
        File userFile = buildFile(String.valueOf(id));
        String fileContent = FileUtils.readFileContent(userFile);
        Gson gson = new Gson();
        User user = gson.fromJson(fileContent, User.class);
        return Observable.just(user);
    }

    @Override
    public Observable<List<User>> getAll() {
        File userFile = buildFile();
        String fileContent = FileUtils.readFileContent(userFile);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        List<User> users = gson.fromJson(fileContent, listType);
        return Observable.just(users);
    }

    @Override
    public void put(User value) {
        //TODO implement
    }

    @Override
    public void put(int id, @NonNull User user) {
        File userFile = buildFile(String.valueOf(id));
        if (!isCached(id)) {
            Gson gson = new Gson();
            String userJson = gson.toJson(user, User.class);
//                Log.d(TAG, "put: " + userJson);
            FileUtils.writeToFile(userFile, userJson);
            setLastCacheUpdateTimeMillis();
        }
    }

    @Override
    public boolean isCached(int id) {
        File userFile = buildFile(String.valueOf(id));
        return FileUtils.exists(userFile);
    }

    @Override
    public boolean isExpired() {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME_MILLIS);

        if (expired) {
            deleteAll();
            return true;
        }

        return false;
    }

    @Override
    public boolean isExpired(int id) {
        long currentTime = System.currentTimeMillis();
        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();

        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME_MILLIS);

        if (expired) {
            delete(key);
            return true;
        }

        return false;
    }

    @Override
    public void delete(int id) {
        File userFile = buildFile(String.valueOf(id));
        userFile.delete();
    }

    public boolean deleteAll() {
        File userFile = buildFile();
        return userFile.delete();
    }

    private File buildFile(@NonNull String key) {
        return FileUtils.buildFile(cacheDir, File.separator, DEFAULT_FILE_NAME, key);
    }

    private File buildFile() {
        return FileUtils.buildFile(cacheDir, File.separator, DEFAULT_FILE_NAME);
    }
}
