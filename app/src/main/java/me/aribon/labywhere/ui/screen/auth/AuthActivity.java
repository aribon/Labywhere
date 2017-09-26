package me.aribon.labywhere.ui.screen.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import butterknife.ButterKnife;

import me.aribon.labywhere.R;
import me.aribon.labywhere.ui.base.BaseActivity;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class AuthActivity extends BaseActivity {

    private static final String TAG = AuthActivity.class.getSimpleName();

    private static final boolean TRANSITION_WITH_ANIMATION = true;

//    @Bind(R.id.auth_button_container) View authButtonContainer;
//    @Bind(R.id.auth_fragment_container) View authFragmentContainer;
//    @Bind(R.id.btn_signin_login) View btnAuthLogin;
//    @Bind(R.id.btn_signup_login) View btnAuthRegister;

    private static AuthActivity activity;

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
    public int getLayoutRessource() {
        return R.layout.activity_auth;
    }

    @Override
    public void findView() {
        super.findView();
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;

        AuthFragment authFragment = new AuthFragment();
        FragmentTransaction transaction = getSupportFragmentManager()
            .beginTransaction();

        transaction.add(R.id.auth_fragment_container, authFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

//    @Override
//    public void onBackPressed() {
//        if (authFragmentContainer.getVisibility() == View.VISIBLE)
//            switchToAuthButtonContainer();
//        else
//            super.onBackPressed();
//    }

//    @OnClick(R.id.btn_signin_login)
//    public void signInClick() {
//        switchToLoginFragment();
//
//    }
//
//    @OnClick(R.id.btn_signup_login)
//    public void signUpClick() {
//        switchToRegisterFragment();
//    }

//    private void switchToLoginFragment() {
//        hideButtonContainer();
//        showLoginFragment();
//        showFragmentContainer();
//    }
//
//    private void switchToRegisterFragment() {
//        hideButtonContainer();
//        showRegisterFragment();
//        showFragmentContainer();
//    }

//    private void switchToAuthButtonContainer() {
//        hideFragmentContainer();
//        removeAuthFragment();
//        showButtonContainer();
//    }

//    private void showLoginFragment() {
//        FragmentTransaction transaction = getSupportFragmentManager()
//                .beginTransaction();
//
//        if (TRANSITION_WITH_ANIMATION)
//            transaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_down);
//
//        if (hasAuthFragment())
//            transaction.replace(R.id.auth_fragment_container, fragmentLogin);
//        else
//            transaction.add(R.id.auth_fragment_container, fragmentLogin);
//
//        transaction.addToBackStack(null)
//                .commit();
//    }
//
//    private void showRegisterFragment() {
//        FragmentTransaction transaction = getSupportFragmentManager()
//                .beginTransaction();
//
//        if (TRANSITION_WITH_ANIMATION)
//            transaction.setCustomAnimations(R.anim.slide_in_up, R.anim.slide_out_down);
//
//        if (hasAuthFragment())
//            transaction.replace(R.id.auth_fragment_container, fragmentRegister);
//        else
//            transaction.add(R.id.auth_fragment_container, fragmentRegister);
//
//        transaction.addToBackStack(null)
//                .commit();
//    }

//    private boolean hasAuthFragment() {
//        return getSupportFragmentManager() != null
//            && getSupportFragmentManager().findFragmentById(R.id.auth_fragment_container) != null;
//    }

//    private void removeAuthFragment() {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .remove(getSupportFragmentManager().findFragmentById(R.id.auth_fragment_container))
//                .commit();
//    }

//    private void showFragmentContainer() {
//        if (TRANSITION_WITH_ANIMATION)
//            authFragmentContainer.animate().alpha(1.0f);
//        else
//            authFragmentContainer.setVisibility(View.VISIBLE);
//    }

//    private void hideFragmentContainer() {
//        if (TRANSITION_WITH_ANIMATION)
//            authFragmentContainer.animate().alpha(0.0f);
//        else
//            authFragmentContainer.setVisibility(View.GONE);
//    }
//
//    private void showButtonContainer() {
//        authButtonContainer.setVisibility(View.VISIBLE);
//    }

//    private void hideButtonContainer() {
//        authButtonContainer.setVisibility(View.INVISIBLE);
//    }
}
