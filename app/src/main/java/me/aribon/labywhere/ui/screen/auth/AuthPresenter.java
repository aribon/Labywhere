package me.aribon.labywhere.ui.screen.auth;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;

import me.aribon.labywhere.R;
import me.aribon.labywhere.ui.base.BaseActivity;
import me.aribon.labywhere.ui.base.BasePresenter;
import me.aribon.labywhere.ui.screen.auth.AuthContract.View;
import me.aribon.labywhere.ui.screen.login.LoginFragment;
import me.aribon.labywhere.ui.screen.register.RegisterFragment;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class AuthPresenter extends BasePresenter<AuthContract.View>
        implements AuthContract.Presenter {

    private BaseActivity activity;

    private Fragment lastFragment;
    private LoginFragment loginFragment;
    private RegisterFragment registerFragment;

    public AuthPresenter(BaseActivity activity, View view) {
        super(view);
        this.activity = activity;
        loginFragment = new LoginFragment();
        registerFragment = new RegisterFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        resetButton();
    }

    @Override
    public void onLoginClick(android.view.View view) {
        switchToLoginFragment(view);
    }

    @Override
    public void onRegisterClick(android.view.View view) {
        switchToRegisterFragment(view);
    }

    private void switchToLoginFragment(android.view.View view) {
        lastFragment = loginFragment;
        activity.getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(view, ViewCompat.getTransitionName(view))
                .replace(R.id.auth_fragment_container, loginFragment)
                .addToBackStack(null)
                .commit();
    }

    private void switchToRegisterFragment(android.view.View view) {
        lastFragment = registerFragment;
        activity.getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(view, ViewCompat.getTransitionName(view))
                .replace(R.id.auth_fragment_container, registerFragment)
                .addToBackStack(null)
                .commit();
    }

    private void resetButton() {
        if (lastFragment instanceof LoginFragment) {
            getView().hideRegisterButton(false);
            getView().showRegisterButton(true);
        }

        if (lastFragment instanceof RegisterFragment) {
            getView().hideLoginButton(false);
            getView().showLoginButton(true);
        }

        lastFragment = null;
    }
}
