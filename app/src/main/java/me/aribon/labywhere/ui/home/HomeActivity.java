package me.aribon.labywhere.ui.home;

import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.aribon.basemvp.view.BaseActivity;
import me.aribon.labywhere.R;

public class HomeActivity extends BaseActivity<HomePresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.home_load_btn)
    public void loadClick() {
        getPresenter().loadData();
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }
}
