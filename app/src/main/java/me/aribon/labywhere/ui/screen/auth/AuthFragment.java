package me.aribon.labywhere.ui.screen.auth;

import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.aribon.labywhere.R;
import me.aribon.labywhere.ui.base.BaseFragment;

/**
 * Created by anthony.ribon
 * On 19/07/2017
 */

public class AuthFragment extends BaseFragment
  implements AuthContract.View {

  AuthContract.Presenter presenter;

  @Override
  public int getLayoutResource() {
    return R.layout.fragment_auth;
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
  public void onLoginClick() {
    presenter.onLoginClick();
  }

  @OnClick(R.id.auth_btn_register)
  public void onRegisterClick() {
    presenter.onRegisterClick();
  }
}
