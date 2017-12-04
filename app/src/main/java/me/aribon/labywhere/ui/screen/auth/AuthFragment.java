package me.aribon.labywhere.ui.screen.auth;

import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.aribon.labywhere.R;
import me.aribon.labywhere.ui.base.BaseFragment;
import me.aribon.labywhere.utils.ResUtils;

/**
 * Created by anthony.ribon
 * On 19/07/2017
 */

public class AuthFragment extends BaseFragment
        implements AuthContract.View {

    int AUTH_ALPHA_ANIMATION_TIME = 500;

    AuthContract.Presenter presenter;

    @Bind(R.id.auth_btn_login)
    Button loginBtn;
    @Bind(R.id.auth_btn_register)
    Button registerBtn;

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
    public void initializeView() {
        super.initializeView();
        presenter.onStart();
    }

    @Override
    public void findView(View view) {
        super.findView(view);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.auth_btn_login)
    public void onLoginClick(View view) {
        loginBtn.setTransitionName(ResUtils.getString(R.string.transition_button_to_bottom));
        registerBtn.setTransitionName(null);
        presenter.onLoginClick(view);
        hideButton(registerBtn, true);
    }

    @OnClick(R.id.auth_btn_register)
    public void onRegisterClick(View view) {
        registerBtn.setTransitionName(ResUtils.getString(R.string.transition_button_to_bottom));
        loginBtn.setTransitionName(null);
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
