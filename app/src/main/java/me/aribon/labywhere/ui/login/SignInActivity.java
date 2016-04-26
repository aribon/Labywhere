package me.aribon.labywhere.ui.login;

import android.os.Bundle;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.aribon.basemvp.view.BaseActivity;
import me.aribon.labywhere.R;

public class SignInActivity extends BaseActivity<SignInPresenter> {

    public static final String TAG = SignInActivity.class.getSimpleName();

    @Bind(R.id.edit_signin_username) EditText editSignInEmail;
    @Bind(R.id.edit_signin_password) EditText editSignInPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_signin_login)
    public void signinClick() {
        mPresenter.login();
    }

    public String getEmail() { return editSignInEmail.getText().toString(); }

    public String getPassword() { return editSignInPassword.getText().toString(); }

    public void setEmailError(String msg) {
        editSignInEmail.setError(msg);
    }

    public void setPasswordError(String msg) {
        editSignInPassword.setError(msg);
    }

    @Override
    protected SignInPresenter initPresenter() {
        return new SignInPresenter();
    }
}