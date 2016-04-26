package me.aribon.labywhere.ui.auth;

import android.content.Intent;

import me.aribon.basemvp.presenter.BasePresenter;
import me.aribon.labywhere.ui.login.SignInActivity;
import me.aribon.labywhere.ui.registration.SignUpActivity;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class AuthPresenter extends BasePresenter<AuthActivity> {

    public void startSignInActivity() {
        Intent intent = new Intent(mView, SignInActivity.class);
        mView.startActivity(intent);
    }

    public void startSignUpActivity() {
        Intent intent = new Intent(mView, SignUpActivity.class);
        mView.startActivity(intent);
    }
}
