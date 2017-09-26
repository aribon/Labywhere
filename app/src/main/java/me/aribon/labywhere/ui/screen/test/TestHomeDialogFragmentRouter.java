package me.aribon.labywhere.ui.screen.test;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import me.aribon.labywhere.ui.base.BaseActivity;

/**
 * Created by anthony.ribon
 * On 25/09/2017
 */

public class TestHomeDialogFragmentRouter {

  private static final String TAG = TestHomeDialogFragment.class.getSimpleName();

  public static void show(BaseActivity activity) {
    TestHomeDialogFragment dialog = TestHomeDialogFragment.newInstance();
    dialog.show(activity.getSupportFragmentManager(), TAG);
  }

  public static void showIfNeeded(BaseActivity activity) {
    if (!isShowing(activity)) {
      DialogFragment dialog = TestHomeDialogFragment.newInstance();
      dialog.show(activity.getSupportFragmentManager(), TAG);
    }
  }

  public static boolean isShowing(BaseActivity activity) {
    Fragment currentDialog = activity.getSupportFragmentManager()
        .findFragmentByTag(TAG);
    return currentDialog != null
        && currentDialog.isVisible();
  }
}
