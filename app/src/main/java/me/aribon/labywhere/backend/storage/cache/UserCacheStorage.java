package me.aribon.labywhere.backend.storage.cache;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.utils.FileUtils;
import rx.Observable;

/**
 * Created by aribon on 26/05/2016.
 */
public class UserCacheStorage extends AbsCacheStorage<User> {

    private static final String TAG = UserCacheStorage.class.getSimpleName();

    private static final long EXPIRATION_TIME_MILLIS = /*60 * 10 * 1000*/ 10 * 1000;

    private static final String USER_FILE_NAME = "user";

    private static UserCacheStorage instance = null;

    private static long lastUpdate;

    public static UserCacheStorage getInstance() {
        if (instance == null)
            instance = new UserCacheStorage();
        return instance;
    }

    private UserCacheStorage() {
    }

    @Override
    protected String getFileName() {
        return USER_FILE_NAME;
    }

        @Override
    public Observable<User> get(int id) {
        if (isExpired()) {
            deleteAll();
            return Observable.empty();
        }

        User user = null;

        File userFile = buildFile();
        Gson gson = new Gson();

        String fileContent = FileUtils.readFileContent(userFile);
        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        List<User> users = gson.fromJson(fileContent, listType);

        if (users != null) {
            for (int i = 0; i < users.size(); i++) {
                User tmpUser = users.get(i);
                if (tmpUser != null && id == tmpUser.getId())
                    user = tmpUser;
            }
        }

        return Observable.just(user);
    }

    @Override
    public Observable<List<User>> getAll() {
        if (isExpired()) {
            deleteAll();
            return Observable.empty();
        }

        File userFile = buildFile();
        String fileContent = FileUtils.readFileContent(userFile);
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        List<User> users = gson.fromJson(fileContent, listType);
        return Observable.just(users);
    }

    @Override
    public void put(@NonNull User user) {

        File userFile = buildFile();
        Gson gson = new Gson();

        String fileContent = FileUtils.readFileContent(userFile);
        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        List<User> users = gson.fromJson(fileContent, listType);

        if (users != null) {

            for (int i = 0; i < users.size(); i++) {
                User tmpUser = users.get(i);

                if (tmpUser != null && user.getId() == tmpUser.getId()) {
                    users.add(i, user);
                    break;
                }

                if (i == users.size() - 1) {
                    users.add(user);
                }
            }
        } else {
            users = new ArrayList<>();
            users .add(user);
        }

        String usersJson = gson.toJson(users, listType);
        FileUtils.writeToFile(userFile, usersJson);

        setLastCacheUpdateTimeMillis();
    }

//    public boolean isCached() {
//        File userFile = buildFile();
//        return FileUtils.exists(userFile);
//    }
//
//    @Override
//    public boolean isCached(int id) {
//        File userFile = buildFile(String.valueOf(id));
//        return FileUtils.exists(userFile);
//    }
//
//    @Override
//    public boolean isExpired() {
//        long currentTime = System.currentTimeMillis();
//        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();
//
//        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME_MILLIS);
//
//        if (expired) {
//            deleteAll();
//            return true;
//        }
//
//        return false;
//    }
//
//    @Override
//    public boolean isExpired(int id) {
//        long currentTime = System.currentTimeMillis();
//        long lastUpdateTime = this.getLastCacheUpdateTimeMillis();
//
//        boolean expired = ((currentTime - lastUpdateTime) > EXPIRATION_TIME_MILLIS);
//
//        if (expired) {
//            delete(key);
//            return true;
//        }
//
//        return false;
//    }

    @Override
    public void delete(int id) {
        File userFile = buildFile();
        Gson gson = new Gson();

        String fileContent = FileUtils.readFileContent(userFile);
        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        List<User> users = gson.fromJson(fileContent, listType);

        if (users != null) {

            for (int i = 0; i < users.size(); i++) {
                User tmpUser = users.get(i);

                if (tmpUser != null && id == tmpUser.getId()) {
                    users.remove(i);
                    break;
                }
            }

            String usersJson = gson.toJson(users, listType);
            FileUtils.writeToFile(userFile, usersJson);
        } else {
            userFile.delete();
        }
    }

    public void deleteAll() {
        File userFile = buildFile();
        userFile.delete();
    }

//    private File buildFile(@NonNull String key) {
//        return FileUtils.buildFile(cacheDir, File.separator, DEFAULT_FILE_NAME, key);
//    }

    /**
     * Set in millis, the last time the cache was accessed.
     */
    public static void setLastCacheUpdateTimeMillis() {
        lastUpdate = System.currentTimeMillis();
    }

//    /**
//     * Get in millis, the last time the cache was accessed.
//     */
//    static long getLastCacheUpdateTimeMillis() {
//        return lastUpdate;
//    }

    public static boolean isExpired() {
        return System.currentTimeMillis() - lastUpdate > EXPIRATION_TIME_MILLIS;
    }
}
