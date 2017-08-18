package me.aribon.labywhere.business.mapper;

/**
 * Created by anthony.ribon
 * On 16/08/2017
 */

public interface Mapper<V, M> {

  V toView(M entity);

  M toModel(V entity);

}
