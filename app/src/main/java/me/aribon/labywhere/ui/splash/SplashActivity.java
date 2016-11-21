package me.aribon.labywhere.ui.splash;

import android.os.Bundle;

import me.aribon.labywhere.LabywhereBaseActivity;
import me.aribon.labywhere.R;

public class SplashActivity extends LabywhereBaseActivity<SplashPresenter> {

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
