package me.aribon.labywhere.ui.screen.register;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthRegisterFragment extends AppBaseSupportFragment<AuthRegisterPresenter>
    implements RegisterContract.View {

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

    public String getEmail() { return etAuthRegisterEmail.getText().toString(); }

    public String getPassword() { return etAuthRegisterPassword.getText().toString(); }

    public String getFirstname() {
        return etAuthRegisterFirstname.getText().toString();
    }

    public String getLastname() { return etAuthRegisterLastname.getText().toString(); }

    public void setEmail(String email) {
        etAuthRegisterEmail.setText(email);
    }

    public void setPassword(String password) {
        etAuthRegisterPassword.setText(password);
    }

    public void setFirstname(String firstname) {
        etAuthRegisterFirstname.setText(firstname);
    }

    public void setLastname(String lastname) {
        etAuthRegisterLastname.setText(lastname);
    }

    public void setEmailError(String msg) {
        etAuthRegisterEmail.setError(msg);
    }

    public void setPasswordError(String msg) {
        etAuthRegisterPassword.setError(msg);
    }

    public void setFirstnameError(String msg) {
        etAuthRegisterFirstname.setError(msg);
    }

    public void setLastnameError(String msg) {
        etAuthRegisterLastname.setError(msg);
    }

    protected AuthRegisterPresenter initPresenter() {
        return new AuthRegisterPresenter();
    }
}
