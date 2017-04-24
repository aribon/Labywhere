package me.aribon.labywhere.base;

import me.aribon.basemvp.view.BaseActivity;


/**
 * Created by aribon from Insign Mobility
 * on 15/11/2016
 */
abstract public class AppBaseActivity<P extends AppBasePresenter> extends BaseActivity<P> {
    //TODO implement specific process
    public AppBaseActivity getActivity() {
        return AppBaseActivity.this;
    }
}
