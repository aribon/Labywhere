package me.aribon.labywhere.ui.auth;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.aribon.basemvp.view.BaseActivity;
import me.aribon.labywhere.R;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class AuthActivity extends BaseActivity<AuthPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_signin_login)
    public void signinClick() {
        mPresenter.startSignInActivity();
    }

    @OnClick(R.id.btn_signup_login)
    public void signupClick() {
        mPresenter.startSignUpActivity();
    }

    @Override
    protected AuthPresenter initPresenter() {
        return new AuthPresenter();
    }
}
