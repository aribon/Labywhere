package me.aribon.labywhere.backend.storage.cache;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import me.aribon.labywhere.LabywhereApplication;
import me.aribon.labywhere.backend.model.Data;
import me.aribon.labywhere.backend.storage.AbsStorage;
import me.aribon.labywhere.backend.utils.ListParameterizedType;
import me.aribon.labywhere.utils.FileUtils;
import rx.Observable;

/**
 * Created on 19/05/2016
 *
 * @author Anthony
 */
abstract class AbsCacheStorage<V extends Data> extends AbsStorage<V> {

//    static final long EXPIRATION_TIME_MILLIS = /*60 * 10 * 1000*/ 10 * 1000;

    public static final String TAG = AbsCacheStorage.class.getSimpleName();

    private File cacheDir;

    AbsCacheStorage() {
        this.cacheDir = LabywhereApplication.getContext().getCacheDir();
    }

    abstract protected Type getDataType();

    abstract protected String getFileName();

    @Override
    public void post(@NonNull V value) {

    }

    public Observable<V> get(int id) {
        V value = null;

        File valueFile = buildFile();
        Gson gson = new Gson();

        String fileContent = FileUtils.readFileContent(valueFile);
        Type listType = new ListParameterizedType(getDataType());
        List<V> valueList = gson.fromJson(fileContent, listType);

        if (valueList != null) {
            for (int i = 0; i < valueList.size(); i++) {
                V tmpValue = valueList.get(i);
                if (tmpValue != null && id == tmpValue.getId()) {
                    value = tmpValue;
                    break;
                }
            }
        }

        return Observable.just(value);
    }

    @Override
    public Observable<List<V>> getAll() {
        File valueFile = buildFile();
        String fileContent = FileUtils.readFileContent(valueFile);
        Gson gson = new Gson();
        Type listType = new ListParameterizedType(getDataType());
        List<V> valueList = gson.fromJson(fileContent, listType);
        return Observable.just(valueList);
    }

    @Override
    public void put(@NonNull V value) {

        File valueFile = buildFile();
        Gson gson = new Gson();

        String fileContent = FileUtils.readFileContent(valueFile);
        Type listType = new ListParameterizedType(getDataType());
        List<V> valueList = gson.fromJson(fileContent, listType);

        if (valueList != null) {

            for (int i = 0; i < valueList.size(); i++) {
                V tmpValue = valueList.get(i);

                if (tmpValue != null && value.getId() == tmpValue.getId()) {
                    valueList.remove(i);
                    valueList.add(i, value);
                    break;
                }

                if (i == valueList.size() - 1) {
                    valueList.add(value);
                }
            }
        } else {
            valueList = new ArrayList<>();
            valueList .add(value);
        }

        String valuesJson = gson.toJson(valueList, listType);
        FileUtils.writeToFile(valueFile, valuesJson);
//        setLastCacheUpdateTimeMillis();
    }

    @Override
    public void delete(int id) {
        File valueFile = buildFile();
        Gson gson = new Gson();

        String fileContent = FileUtils.readFileContent(valueFile);
        Type listType = new ListParameterizedType(getDataType());
        List<V> values = gson.fromJson(fileContent, listType);

        if (values != null) {

            for (int i = 0; i < values.size(); i++) {
                V value = values.get(i);

                if (value != null && id == value.getId()) {
                    values.remove(i);
                    break;
                }
            }

            String valuesJson = gson.toJson(values, listType);
            FileUtils.writeToFile(valueFile, valuesJson);
        } else {
            valueFile.delete();
        }
    }

    private File buildFile() {
        return FileUtils.buildFile(cacheDir, File.separator, getFileName());
    }

//    public abstract boolean isCached(int id);
//
//    public abstract boolean isExpired();
//
//    public abstract boolean isExpired(int id);

//    /**
//     * Set in millis, the last time the cache was accessed.
//     */
//    void setLastCacheUpdateTimeMillis() {
//        long currentMillis = System.currentTimeMillis();
//        SettingsPreferences.setLastCacheUpdate(currentMillis);
//    }
//
//    /**
//     * Get in millis, the last time the cache was accessed.
//     */
//    long getLastCacheUpdateTimeMillis() {
//        return SettingsPreferences.getLastCacheUpdate();
//    }
}
