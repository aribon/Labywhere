package me.aribon.labywhere.ui.screen.auth;

import me.aribon.labywhere.ui.base.BaseMvpPresenter;
import me.aribon.labywhere.ui.base.BaseMvpView;

/**
 * Created by anthony.ribon
 * On 15/08/2017
 */

public interface AuthContract {

  interface View extends BaseMvpView {

  }

  interface Presenter extends BaseMvpPresenter<View> {

    void onLoginClick();

    void onRegisterClick();
  }
}
