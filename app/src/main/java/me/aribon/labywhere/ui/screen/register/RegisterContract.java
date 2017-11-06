package me.aribon.labywhere.ui.screen.register;

import me.aribon.labywhere.ui.base.BaseMvpPresenter;
import me.aribon.labywhere.ui.base.BaseMvpView;

/**
 * Created by anthony.ribon
 * On 15/08/2017
 */

public interface RegisterContract {

  interface View extends BaseMvpView {

    void setEmailField(String email);
    void setPasswordField(String password);
    void setFirstnameField(String firstname);
    void setLastnameField(String lastname);

    void setEmailError(String msg);
    void setPasswordError(String msg);
    void setFirstnameError(String msg);
    void setLastnameError(String msg);
  }

  interface Presenter extends BaseMvpPresenter<View> {

    void onValidateClick(String email, String password, String firstname, String lastname);
  }
}
