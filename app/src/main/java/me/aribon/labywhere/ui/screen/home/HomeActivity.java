package me.aribon.labywhere.ui.screen.home;

import butterknife.ButterKnife;
import butterknife.OnClick;
import me.aribon.labywhere.R;
import me.aribon.labywhere.ui.base.BaseActivity;
import me.aribon.labywhere.ui.screen.test.TestHomeDialogFragment;
import me.aribon.labywhere.ui.screen.test.TestHomeDialogFragmentRouter;

public class HomeActivity extends BaseActivity
    implements HomeContract.View {

  HomeContract.Presenter presenter;

  @Override
  public int getLayoutRessource() {
    return R.layout.activity_home;
  }

  @Override
  public void findView() {
    super.findView();
    ButterKnife.bind(this);
  }

  @Override
  public void initializePresenter() {
    super.initializePresenter();
    presenter = new HomePresenter(this);
  }

  @OnClick(R.id.home_load_btn)
  public void onStartClick() {
    presenter.startClick();
  }

  public void showTestHomeDialogFragment() {
    TestHomeDialogFragmentRouter.show(this);
  }
}
