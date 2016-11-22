package me.aribon.labywhere.backend.utils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * Created by aribon from Insign Mobility
 * on 22/11/2016
 */
public class ListParameterizedType implements ParameterizedType {

    private Type type;

    public ListParameterizedType(Type type) {
        this.type = type;
    }

    @Override
    public Type[] getActualTypeArguments() {
        return new Type[] {type};
    }

    @Override
    public Type getRawType() {
        return ArrayList.class;
    }

    @Override
    public Type getOwnerType() {
        return null;
    }

    // implement equals method too! (as per javadoc)
}
