package me.aribon.labywhere.ui.screen.login;

import me.aribon.labywhere.ui.base.BaseMvpPresenter;
import me.aribon.labywhere.ui.base.BaseMvpView;

/**
 * Created by anthony.ribon
 * On 22/07/2017
 */
public interface LoginContact {

    interface View extends BaseMvpView {

        void setEmailField(String email);

        void setPasswordField(String password);

        void setEmailError(String msg);

        void setPasswordError(String msg);
    }

    interface Presenter extends BaseMvpPresenter<View> {

        void onValidateClick(String email, String password);
    }
}