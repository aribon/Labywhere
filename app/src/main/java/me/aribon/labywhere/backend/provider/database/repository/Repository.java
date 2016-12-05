package me.aribon.labywhere.backend.provider.database.repository;

import java.util.List;

import me.aribon.labywhere.backend.provider.database.specification.Specification;

/**
 * Created by aribon from Insign Mobility
 * on 24/11/2016
 */
public interface Repository<T> {

    void insert(T item);

    void insert(Iterable<T> items);

    void update(T item);

    void delete(T item);

    void delete(Specification specification);

    List<T> query(Specification specification);
}
