package me.aribon.labywhere.ui.auth;

import android.content.Intent;

import me.aribon.labywhere.LabywhereBasePresenter;
import me.aribon.labywhere.ui.login.LoginActivity;
import me.aribon.labywhere.ui.register.RegisterActivity;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class AuthPresenter extends LabywhereBasePresenter<AuthActivity> {

    public void startSignInActivity() {
        Intent intent = new Intent(getView(), LoginActivity.class);
        getView().startActivity(intent);
    }

    public void startSignUpActivity() {
        Intent intent = new Intent(getView(), RegisterActivity.class);
        getView().startActivity(intent);
    }
}
