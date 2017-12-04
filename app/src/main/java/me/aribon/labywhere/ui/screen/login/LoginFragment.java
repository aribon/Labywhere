package me.aribon.labywhere.ui.screen.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.transition.Transition;
import android.transition.Transition.TransitionListener;
import android.transition.TransitionInflater;
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

    int LOGIN_ALPHA_ANIMATION_TIME = 500;

    LoginContact.Presenter presenter;

    @Bind(R.id.auth_login_form_container)
    View formContainer;
    @Bind(R.id.auth_login_edit_email)
    EditText etLoginEmail;
    @Bind(R.id.auth_login_edit_password)
    EditText etLoginPassword;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_auth_login;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSharedElementEnterTransition(
                TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move)
                        .addListener(
                                new TransitionListener() {
                                    @Override
                                    public void onTransitionStart(Transition transition) {
                                        hideForm();
                                        showForm(true);
                                    }

                                    @Override
                                    public void onTransitionEnd(Transition transition) {
                                        //showForm(true);
                                    }

                                    @Override
                                    public void onTransitionCancel(Transition transition) {
                                        showForm(false);
                                    }

                                    @Override
                                    public void onTransitionPause(Transition transition) {

                                    }

                                    @Override
                                    public void onTransitionResume(Transition transition) {

                                    }
                                }
                        )
        );
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
        presenter.onValidateClick(
                etLoginEmail.getText().toString(),
                etLoginPassword.getText().toString()
        );
    }

    private void showForm(boolean withAnim) {
        if (withAnim) {
            formContainer.animate()
                    .alpha(1.0f)
                    .setDuration(LOGIN_ALPHA_ANIMATION_TIME)
                    .start();
        } else {
            formContainer.setAlpha(1.0f);
        }
    }

    private void hideForm() {
        formContainer.setAlpha(0.0f);
    }
}
