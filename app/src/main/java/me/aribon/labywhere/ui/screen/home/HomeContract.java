package me.aribon.labywhere.ui.screen.home;

import me.aribon.labywhere.ui.base.BaseMvpPresenter;
import me.aribon.labywhere.ui.base.BaseMvpView;

/**
 * Created by anthony.ribon
 * On 25/09/2017
 */

public interface HomeContract {

  interface View extends BaseMvpView {

  }

  interface Presenter extends BaseMvpPresenter<View> {

    void startClick();
  }
}
