package me.aribon.labywhere.ui.base.lib;

import android.support.annotation.StringRes;

/**
 * Created by anthony.ribon
 * On 22/07/2017
 */

public interface MvpView {

    //Activity getViewActivity();

    //P getPresenter();

    void showLoading();

    void hideLoading();

    //void onError(@StringRes int resId);

    //void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    void showToastMessage(String message);

    void showToastMessage(@StringRes int resId);

    void showKeyboard();

    void hideKeyboard();
}
