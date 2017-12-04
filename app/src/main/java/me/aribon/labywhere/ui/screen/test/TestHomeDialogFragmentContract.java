package me.aribon.labywhere.ui.screen.test;

import me.aribon.labywhere.ui.base.BaseMvpPresenter;
import me.aribon.labywhere.ui.base.BaseMvpView;

/**
 * Created by anthony.ribon
 * On 25/09/2017
 */

interface TestHomeDialogFragmentContract {

    interface View extends BaseMvpView {

        void showDataResult(String data);
    }

    interface Presenter extends BaseMvpPresenter<View> {

        void loadData();
    }
}
