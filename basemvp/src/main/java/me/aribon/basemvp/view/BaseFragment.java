package me.aribon.basemvp.view;

import android.os.Bundle;
import android.app.Fragment;

import me.aribon.basemvp.presenter.BasePresenter;

/**
 * Created on 19/03/2016
 *
 * @author Anthony
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView<P> {

    protected P mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        mPresenter.onCreate();
        mPresenter.onAttachView(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public P getPresenter() {
        return mPresenter;
    }

    @Override
    public void setPresenter(P presenter) {
        this.mPresenter.onDetachView();
        this.mPresenter = presenter;
        this.mPresenter.onAttachView(this);
    }

    protected abstract P initPresenter();
}
