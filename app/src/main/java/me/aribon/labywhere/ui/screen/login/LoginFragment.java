package me.aribon.labywhere.ui.screen.login;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.aribon.labywhere.R;
import me.aribon.labywhere.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment
    implements LoginContact.View {

    LoginContact.Presenter presenter;

    @Bind(R.id.auth_login_edit_email) EditText etLoginEmail;
    @Bind(R.id.auth_login_edit_password) EditText etLoginPassword;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_auth_login;
    }

    @Override
    public void findView(View view) {
        super.findView(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void initializePresenter() {
        super.initializePresenter();
        presenter = new LoginPresenter(getParentActivity(), this);
    }

    @Override
    public String getEmailValue() {
        return etLoginEmail.getText().toString();
    }

    @Override
    public String getPasswordValue() {
        return etLoginPassword.getText().toString();
    }

    @Override
    public void setEmailField(String email) {
        etLoginEmail.setText(email);
    }

    @Override
    public void setPasswordField(String password) {
        etLoginPassword.setText(password);
    }

    @Override
    public void setEmailError(String msg) {
        etLoginEmail.setError(msg);
    }

    @Override
    public void setPasswordError(String msg) {
        etLoginPassword.setError(msg);
    }

    @OnClick(R.id.auth_login_valid_btn)
    public void onValidateClick() {
        presenter.onValidateClick();
    }
}
