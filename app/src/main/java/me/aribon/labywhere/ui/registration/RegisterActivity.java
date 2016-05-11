package me.aribon.labywhere.ui.registration;

import android.os.Bundle;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.aribon.basemvp.view.BaseActivity;
import me.aribon.labywhere.R;

public class RegisterActivity extends BaseActivity<RegisterPresenter> {

    public static final String TAG = RegisterActivity.class.getSimpleName();

    @Bind(R.id.edit_signup_email) EditText editSignupEmail;
    @Bind(R.id.edit_signup_password) EditText editSignupPassword;
    @Bind(R.id.edit_signup_firstname) EditText editSignupFirstname;
    @Bind(R.id.edit_signup_lastname) EditText editSignupLastname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_signup_login)
    public void signinClick() {
        mPresenter.prepareRegister();
    }

    public String getEmail() { return editSignupEmail.getText().toString(); }

    public String getPassword() { return editSignupPassword.getText().toString(); }

    public String getFirstnmee() { return editSignupFirstname.getText().toString(); }

    public String getLastname() { return editSignupLastname.getText().toString(); }

    public void setEmailError(String msg) {
        editSignupEmail.setError(msg);
    }

    public void setPasswordError(String msg) {
        editSignupPassword.setError(msg);
    }

    public void setFirstnameError(String msg) {
        editSignupFirstname.setError(msg);
    }

    public void setLastnameError(String msg) {
        editSignupLastname.setError(msg);
    }

    @Override
    protected RegisterPresenter initPresenter() {
        return new RegisterPresenter();
    }
}
