package me.aribon.labywhere.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import me.aribon.basemvp.view.BaseActivity;
import me.aribon.labywhere.R;
import me.aribon.labywhere.ui.auth.AuthActivity;

public class SplashActivity extends BaseActivity<SplashPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected SplashPresenter initPresenter() {
        return new SplashPresenter();
    }

    public void startAuthActivity() {
        Intent intent = new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
