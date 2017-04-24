package me.aribon.labywhere.ui.screen.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.aribon.labywhere.R;
import me.aribon.labywhere.base.AppBaseActivity;
import me.aribon.labywhere.ui.screen.auth.login.AuthLoginFragment;
import me.aribon.labywhere.ui.screen.auth.register.AuthRegisterFragment;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class AuthActivity extends AppBaseActivity<AuthPresenter> {

    private static final String TAG = AuthActivity.class.getSimpleName();

    @Bind(R.id.auth_button_container) View authButtonContainer;
    @Bind(R.id.auth_fragment_container) View authFragmentContainer;

    private static AuthActivity activity;

    private AuthLoginFragment fragmentLogin = new AuthLoginFragment();
    private AuthRegisterFragment fragmentRegister = new AuthRegisterFragment();

    public static void startActivity(Context context) {
        if (activity == null) {
            Intent intent = new Intent(context, AuthActivity.class);
            context.startActivity(intent);
        }
    }

    public static void stopActivity() {
        if (activity != null) {
            activity.finish();
            activity = null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        activity = this;
    }

    @OnClick(R.id.btn_signin_login)
    public void signInClick() {
        switchToLoginFragment();

    }

    @OnClick(R.id.btn_signup_login)
    public void signUpClick() {
        switchToRgisterFragment();
    }

    private void switchToLoginFragment() {
        hideButtonContaine();
        showLoginFragment();
        showFragmentContainer();
    }

    private void switchToRgisterFragment() {
        hideButtonContaine();
        showRegisterFragment();
        showFragmentContainer();
    }

    private void switchToAuthButtonContainer() {
        hideFragmentContainer();
        removeAuthFragment();
        showButtonContainer();
    }

    private void showLoginFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.auth_fragment_container, fragmentLogin)
                .addToBackStack(null)
                .commit();
    }

    private void showRegisterFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.auth_fragment_container, fragmentRegister)
                .addToBackStack(null)
                .commit();
    }

    private void removeAuthFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .remove(getSupportFragmentManager().findFragmentById(R.id.auth_fragment_container))
                .commit();
    }

    private void showFragmentContainer() {
        authFragmentContainer.setVisibility(View.VISIBLE);
    }

    private void hideFragmentContainer() {
        authFragmentContainer.setVisibility(View.GONE);
    }

    private void showButtonContainer() {
        authButtonContainer.setVisibility(View.VISIBLE);
    }

    private void hideButtonContaine() {
        authButtonContainer.setVisibility(View.GONE);
    }

    @Override
    protected AuthPresenter initPresenter() {
        return new AuthPresenter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ");
    }
}
