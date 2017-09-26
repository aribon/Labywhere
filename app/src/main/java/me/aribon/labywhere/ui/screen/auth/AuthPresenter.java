package me.aribon.labywhere.ui.screen.auth;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import me.aribon.labywhere.R;
import me.aribon.labywhere.ui.base.BaseActivity;
import me.aribon.labywhere.ui.base.BasePresenter;
import me.aribon.labywhere.ui.screen.auth.AuthContract.View;
import me.aribon.labywhere.ui.screen.login.LoginFragment;
import me.aribon.labywhere.utils.ResUtils;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class AuthPresenter extends BasePresenter<AuthContract.View>
  implements AuthContract.Presenter {

  private BaseActivity activity;

  private Fragment lastFragment;
  private LoginFragment loginFragment;

  public AuthPresenter(BaseActivity activity, View view) {
    super(view);
    this.activity = activity;
    loginFragment = new LoginFragment();
  }

  @Override
  public void onResume() {
    super.onResume();
    resetButton();
  }

  @Override
  public void onLoginClick(android.view.View view) {
    switchToLoginFragment(view);
  }

  @Override
  public void onRegisterClick(android.view.View view) {
    switchToRegisterFragment(view);
  }

  private void switchToLoginFragment(android.view.View view) {
    lastFragment = loginFragment;
    activity.getSupportFragmentManager()
        .beginTransaction()
        .addSharedElement(view, ViewCompat.getTransitionName(view))
        .replace(R.id.auth_fragment_container, loginFragment)
        .addToBackStack(null)
        .commit();
  }

  private void switchToRegisterFragment(android.view.View view) {
    getView().showToastMessage("Not available");
  }

  private void resetButton() {
    if (lastFragment instanceof LoginFragment) {
      getView().hideRegisterButton(false);
      getView().showRegisterButton(true);
    }

    lastFragment = null;
  }
}
