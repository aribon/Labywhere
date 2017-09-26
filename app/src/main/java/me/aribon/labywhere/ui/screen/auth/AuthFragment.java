package me.aribon.labywhere.ui.screen.auth;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.SharedElementCallback;
import android.transition.Transition;
import android.transition.Transition.TransitionListener;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.Button;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.List;
import me.aribon.labywhere.R;
import me.aribon.labywhere.ui.base.BaseFragment;

/**
 * Created by anthony.ribon
 * On 19/07/2017
 */

public class AuthFragment extends BaseFragment
  implements AuthContract.View {

  int AUTH_ALPHA_ANIMATION_TIME = 500;

  AuthContract.Presenter presenter;

  @Bind(R.id.auth_btn_login) Button loginBtn;
  @Bind(R.id.auth_btn_register) Button registerBtn;

  @Override
  public int getLayoutResource() {
    return R.layout.fragment_auth;
  }

  @Override
  public void onResume() {
    super.onResume();
    presenter.onResume();
  }

  @Override
  public void initializePresenter() {
    super.initializePresenter();
    presenter = new AuthPresenter(getParentActivity(), this);
  }

  @Override
  public void findView(View view) {
    super.findView(view);
    ButterKnife.bind(this, view);
  }

  @OnClick(R.id.auth_btn_login)
  public void onLoginClick(View view) {
    presenter.onLoginClick(view);
    hideButton(registerBtn, true);
  }

  @OnClick(R.id.auth_btn_register)
  public void onRegisterClick(View view) {
    presenter.onRegisterClick(view);
    hideButton(loginBtn, true);
  }

  @Override
  public void showLoginButton(boolean withAnim) {
    showButton(loginBtn, withAnim);
  }

  @Override
  public void showRegisterButton(boolean withAnim) {
    showButton(registerBtn, withAnim);
  }

  @Override
  public void hideLoginButton(boolean withAnim) {
    hideButton(loginBtn, withAnim);
  }

  @Override
  public void hideRegisterButton(boolean withAnim) {
    hideButton(registerBtn, withAnim);
  }

  private void showButton(View view, boolean withAnim) {
    if (withAnim) {
      view.animate()
          .alpha(1.0f)
          .setDuration(AUTH_ALPHA_ANIMATION_TIME)
          .start();
    } else {
      view.setAlpha(1.0f);
    }
  }

  private void hideButton(View view, boolean withAnim) {
    if (withAnim) {
      view.animate()
          .alpha(0.0f)
          .setDuration(AUTH_ALPHA_ANIMATION_TIME)
          .start();
    } else {
      view.setAlpha(0.0f);
    }
  }
}
