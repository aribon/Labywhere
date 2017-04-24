package me.aribon.labywhere.ui.screen.auth.register;


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
import me.aribon.labywhere.ui.module.RegisterModule;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthRegisterFragment extends AppBaseSupportFragment<AuthRegisterPresenter> implements RegisterModule.View {

    @Bind(R.id.auth_register_edit_email) EditText etAuthRegisterEmail;
    @Bind(R.id.auth_register_edit_password) EditText etAuthRegisterPassword;
    @Bind(R.id.auth_register_edit_firstname) EditText etAuthRegisterFirstname;
    @Bind(R.id.auth_register_edit_lastname) EditText etAuthRegisterLastname;

    public AuthRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_auth_register, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public String getEmail() { return etAuthRegisterEmail.getText().toString(); }

    @Override
    public String getPassword() { return etAuthRegisterPassword.getText().toString(); }

    @Override
    public String getFirstname() {
        return etAuthRegisterFirstname.getText().toString();
    }

    @Override
    public String getLastname() { return etAuthRegisterLastname.getText().toString(); }

    @Override
    public void setEmail(String email) {
        etAuthRegisterEmail.setText(email);
    }

    @Override
    public void setPassword(String password) {
        etAuthRegisterPassword.setText(password);
    }

    @Override
    public void setFirstname(String firstname) {
        etAuthRegisterFirstname.setText(firstname);
    }

    @Override
    public void setLastname(String lastname) {
        etAuthRegisterLastname.setText(lastname);
    }

    @Override
    public void setEmailError(String msg) {
        etAuthRegisterEmail.setError(msg);
    }

    @Override
    public void setPasswordError(String msg) {
        etAuthRegisterPassword.setError(msg);
    }

    @Override
    public void setFirstnameError(String msg) {
        etAuthRegisterFirstname.setError(msg);
    }

    @Override
    public void setLastnameError(String msg) {
        etAuthRegisterLastname.setError(msg);
    }

    @Override
    protected AuthRegisterPresenter initPresenter() {
        return new AuthRegisterPresenter();
    }
}
