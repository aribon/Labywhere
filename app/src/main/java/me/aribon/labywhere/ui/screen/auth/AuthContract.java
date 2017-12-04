package me.aribon.labywhere.ui.screen.auth;

import me.aribon.labywhere.ui.base.BaseMvpPresenter;
import me.aribon.labywhere.ui.base.BaseMvpView;

/**
 * Created by anthony.ribon
 * On 15/08/2017
 */

public interface AuthContract {

    interface View extends BaseMvpView {

        void showLoginButton(boolean withAnim);

        void showRegisterButton(boolean withAnim);

        void hideLoginButton(boolean withAnim);

        void hideRegisterButton(boolean withAnim);
    }

    interface Presenter extends BaseMvpPresenter<View> {

        void onLoginClick(android.view.View view);

        void onRegisterClick(android.view.View view);
    }
}
