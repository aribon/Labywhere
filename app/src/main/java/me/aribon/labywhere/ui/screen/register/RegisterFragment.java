package me.aribon.labywhere.ui.screen.register;

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
public class RegisterFragment extends BaseFragment
    implements RegisterContract.View {

    int REGISTER_ALPHA_ANIMATION_TIME = 500;

  RegisterContract.Presenter presenter;

    @Bind(R.id.auth_register_form_container) View formContainer;
    @Bind(R.id.auth_register_edit_email) EditText etRegisterEmail;
    @Bind(R.id.auth_register_edit_password) EditText etRegisterPassword;
    @Bind(R.id.auth_register_edit_firstname) EditText etRegisterFirstname;
    @Bind(R.id.auth_register_edit_lastname) EditText etRegisterLastname;

    public RegisterFragment() {
        // Required empty public constructor
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
    public int getLayoutResource() {
        return R.layout.fragment_auth_register;
    }

    @Override
    public void findView(View view) {
        super.findView(view);
        ButterKnife.bind(this, view);
    }

  @Override
  public void initializePresenter() {
    super.initializePresenter();
    presenter = new RegisterPresenter(getParentActivity(), this);
  }

  @Override
  public void setEmailField(String email) {
    etRegisterEmail.setText(email);
  }

  @Override
  public void setPasswordField(String password) {
    etRegisterPassword.setText(password);
  }

    @Override
    public void setFirstnameField(String firstname) {
      etRegisterFirstname.setText(firstname);
    }

    @Override
    public void setLastnameField(String lastname) {
      etRegisterLastname.setText(lastname);
    }

  @Override
  public void setEmailError(String msg) {
    etRegisterEmail.setError(msg);
  }

  @Override
  public void setPasswordError(String msg) {
    etRegisterPassword.setError(msg);
  }

    @Override
    public void setFirstnameError(String msg) {
      etRegisterFirstname.setError(msg);
    }

    @Override
    public void setLastnameError(String msg) {
      etRegisterLastname.setError(msg);
    }

  @OnClick(R.id.auth_register_valid_btn)
  public void onValidateClick() {
    presenter.onValidateClick(
        etRegisterEmail.getText().toString(),
        etRegisterPassword.getText().toString(),
        etRegisterFirstname.getText().toString(),
        etRegisterLastname.getText().toString()
    );
  }

    private void showForm(boolean withAnim) {
        if (withAnim) {
            formContainer.animate()
                .alpha(1.0f)
                .setDuration(REGISTER_ALPHA_ANIMATION_TIME)
                .start();
        } else {
            formContainer.setAlpha(1.0f);
        }
    }

    private void hideForm() {
        formContainer.setAlpha(0.0f);
    }
}
