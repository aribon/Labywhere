package me.aribon.labywhere.ui.screen.auth;

import me.aribon.labywhere.R;
import me.aribon.labywhere.ui.base.BaseActivity;
import me.aribon.labywhere.ui.base.BasePresenter;
import me.aribon.labywhere.ui.screen.auth.AuthContract.View;
import me.aribon.labywhere.ui.screen.login.LoginFragment;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class AuthPresenter extends BasePresenter<AuthContract.View>
  implements AuthContract.Presenter {

  private BaseActivity activity;

  private LoginFragment loginFragment;

  public AuthPresenter(BaseActivity activity, View view) {
    super(view);
    this.activity = activity;
    loginFragment = new LoginFragment();
  }

  @Override
  public void onLoginClick() {
    switchToLoginFragment();
  }

  @Override
  public void onRegisterClick() {
    switchToRegisterFragment();
  }

  private void switchToLoginFragment() {
    activity.getSupportFragmentManager()
        .beginTransaction()
        .replace(R.id.auth_fragment_container, loginFragment)
        .addToBackStack(null)
        .commit();
  }

  private void switchToRegisterFragment() {
    getView().showToastMessage("Not available");
  }
}
