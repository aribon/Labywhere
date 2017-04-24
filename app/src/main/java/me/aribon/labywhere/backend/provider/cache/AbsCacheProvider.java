package me.aribon.labywhere.backend.provider.cache;

import android.support.annotation.NonNull;

import java.io.File;

import me.aribon.labywhere.base.LabywhereApplication;
import me.aribon.labywhere.backend.model.Data;
import me.aribon.labywhere.backend.provider.AbsStorage;
import me.aribon.labywhere.utils.FileUtils;

/**
 * Created on 19/05/2016
 *
 * @author Anthony
 */
abstract class AbsCacheProvider<V extends Data> extends AbsStorage<V> {

    public static final String TAG = AbsCacheProvider.class.getSimpleName();

    private File cacheDir;

    AbsCacheProvider() {
        this.cacheDir = LabywhereApplication.getContext().getCacheDir();
    }

    abstract protected String getFileName();

    @Override
    public void post(@NonNull V value) {

    }

//    public Observable<V> get(int id) {
//        V value = null;
//
//        File valueFile = buildFile();
//        Gson gson = new Gson();
//
//        String fileContent = FileUtils.readFileContent(valueFile);
//        Type listType = new ListParameterizedType(getDataType());
//        List<V> valueList = gson.fromJson(fileContent, listType);
//
//        if (valueList != null) {
//            for (int i = 0; i < valueList.size(); i++) {
//                V tmpValue = valueList.get(i);
//                if (tmpValue != null && id == tmpValue.getId()) {
//                    value = tmpValue;
//                    break;
//                }
//            }
//        }
//
//        return Observable.just(value);
//    }
//
//    @Override
//    public Observable<List<V>> getAll() {
//        File valueFile = buildFile();
//        String fileContent = FileUtils.readFileContent(valueFile);
//        Gson gson = new Gson();
//        Type listType = new ListParameterizedType(getDataType());
//        List<V> valueList = gson.fromJson(fileContent, listType);
//        return Observable.just(valueList);
//    }
//
//    @Override
//    public void put(@NonNull V value) {
//
//        File valueFile = buildFile();
//        Gson gson = new Gson();
//
//        String fileContent = FileUtils.readFileContent(valueFile);
//        Type listType = new ListParameterizedType(getDataType());
//        List<V> valueList = gson.fromJson(fileContent, listType);
//
//        if (valueList != null) {
//
//            for (int i = 0; i < valueList.size(); i++) {
//                V tmpValue = valueList.get(i);
//
//                if (tmpValue != null && value.getId() == tmpValue.getId()) {
//                    valueList.remove(i);
//                    valueList.add(i, value);
//                    break;
//                }
//
//                if (i == valueList.size() - 1) {
//                    valueList.add(value);
//                }
//            }
//        } else {
//            valueList = new ArrayList<>();
//            valueList .add(value);
//        }
//
//        String valuesJson = gson.toJson(valueList, listType);
//        FileUtils.writeToFile(valueFile, valuesJson);
////        setLastCacheUpdateTimeMillis();
//    }
//
//    @Override
//    public void delete(int id) {
//        File valueFile = buildFile();
//        Gson gson = new Gson();
//
//        String fileContent = FileUtils.readFileContent(valueFile);
//        Type listType = new ListParameterizedType(getDataType());
//        List<V> values = gson.fromJson(fileContent, listType);
//
//        if (values != null) {
//
//            for (int i = 0; i < values.size(); i++) {
//                V value = values.get(i);
//
//                if (value != null && id == value.getId()) {
//                    values.remove(i);
//                    break;
//                }
//            }
//
//            String valuesJson = gson.toJson(values, listType);
//            FileUtils.writeToFile(valueFile, valuesJson);
//        } else {
//            valueFile.delete();
//        }
//    }

    protected File buildFile() {
        return FileUtils.buildFile(cacheDir, File.separator, getFileName());
    }

//    public abstract boolean isCached(int id);
//
//    public abstract boolean isExpired();
//
//    public abstract boolean isExpired(int id);

}
