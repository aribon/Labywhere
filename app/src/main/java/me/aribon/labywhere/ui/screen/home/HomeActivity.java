package me.aribon.labywhere.ui.screen.home;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.aribon.labywhere.base.AppBaseActivity;
import me.aribon.labywhere.R;

public class HomeActivity extends AppBaseActivity<HomePresenter> {

    @Bind(R.id.textViewResult) TextView result;

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

    public void setResultText(String text) {
        result.setText(text);
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }
}
