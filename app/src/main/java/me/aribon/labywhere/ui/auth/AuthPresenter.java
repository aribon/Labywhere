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
        Intent intent = new Intent(mView, LoginActivity.class);
        mView.startActivity(intent);
    }

    public void startSignUpActivity() {
        Intent intent = new Intent(mView, RegisterActivity.class);
        mView.startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (resultCode == Activity.RESULT_CANCELED) {
//            mView.finish();
//        }
    }
}
