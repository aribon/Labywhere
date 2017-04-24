package me.aribon.labywhere.ui.screen.auth.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.aribon.labywhere.R;
import me.aribon.labywhere.base.AppBaseSupportFragment;
import me.aribon.labywhere.ui.module.LoginModule;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthLoginFragment extends AppBaseSupportFragment<AuthLoginPresenter> implements LoginModule.View {

    @Bind(R.id.auth_login_edit_email) EditText etAuthLoginEmail;
    @Bind(R.id.auth_login_edit_password) EditText etAuthLoginPassword;

    public AuthLoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_auth_login, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public String getEmail() {
        return etAuthLoginEmail.getText().toString();
    }

    @Override
    public String getPassword() {
        return etAuthLoginPassword.getText().toString();
    }

    @Override
    public void setEmail(String email) {
        etAuthLoginEmail.setText(email);
    }

    @Override
    public void setPassword(String password) {
        etAuthLoginPassword.setText(password);
    }

    @Override
    public void setEmailError(String msg) {
        etAuthLoginEmail.setError(msg);
    }

    @Override
    public void setPasswordError(String msg) {
        etAuthLoginPassword.setError(msg);
    }

    @Override
    protected AuthLoginPresenter initPresenter() {
        return new AuthLoginPresenter();
    }
}
