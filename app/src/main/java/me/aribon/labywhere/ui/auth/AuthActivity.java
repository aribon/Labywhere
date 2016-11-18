package me.aribon.labywhere.ui.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.aribon.basemvp.view.BaseActivity;
import me.aribon.labywhere.R;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class AuthActivity extends BaseActivity<AuthPresenter> {

    private static final String TAG = AuthActivity.class.getSimpleName();

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        activity = this;
    }

    @OnClick(R.id.btn_signin_login)
    public void signInClick() {
        mPresenter.startSignInActivity();
    }

    @OnClick(R.id.btn_signup_login)
    public void signUpClick() {
        mPresenter.startSignUpActivity();
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
