package me.aribon.labywhere.ui.screen.splash;

import me.aribon.labywhere.R;
import me.aribon.labywhere.ui.base.BaseActivity;

public class SplashActivity extends BaseActivity
    implements SplashContract.View {

    SplashContract.Presenter presenter;

    @Override
    public int getLayoutRessource() {
        return R.layout.activity_splash;
    }

    @Override
    public void initializePresenter() {
        super.initializePresenter();
        presenter = new SplashPresenter(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }
}
