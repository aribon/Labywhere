package me.aribon.labywhere.ui.base;

import android.content.Context;
import me.aribon.labywhere.ui.base.lib.MvpFragment;

/**
 * Created by anthony.ribon
 * On 22/07/2017
 */

public abstract class BaseFragment extends MvpFragment
    implements BaseMvpView {

  BaseActivity activity;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    activity = (BaseActivity) context;
  }

  @Override
  public void onDetach() {
    activity = null;
    super.onDetach();
  }

  public BaseActivity getParentActivity() {
    return activity;
  }
}
