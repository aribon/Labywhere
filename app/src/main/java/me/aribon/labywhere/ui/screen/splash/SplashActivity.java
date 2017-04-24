package me.aribon.labywhere.ui.screen.splash;

import android.os.Bundle;

import me.aribon.labywhere.base.AppBaseActivity;
import me.aribon.labywhere.R;

public class SplashActivity extends AppBaseActivity<SplashPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected SplashPresenter initPresenter() {
        return new SplashPresenter();
    }
}
