package me.aribon.labywhere.ui.screen.home;

import me.aribon.labywhere.ui.base.BasePresenter;
import me.aribon.labywhere.ui.screen.home.HomeContract.View;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
class HomePresenter extends BasePresenter<HomeContract.View>
    implements HomeContract.Presenter {

  public HomePresenter(View mvpView) {
    super(mvpView);
  }

  public void startClick() {
    //getView().showTestHomeDialogFragment();
  }
}
