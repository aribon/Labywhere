package me.aribon.basemvp.view;

import me.aribon.basemvp.presenter.BasePresenter;

/**
 * Created on 19/03/2016
 *
 * @author Anthony
 */
public interface BaseView<P extends BasePresenter> {

    P getPresenter();

    void setPresenter(P presenter);
}
