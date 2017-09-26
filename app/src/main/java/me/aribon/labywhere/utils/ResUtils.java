package me.aribon.labywhere.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.content.res.AppCompatResources;
import me.aribon.labywhere.ui.base.BaseApplication;

/**
 * Created by anthony.ribon
 * On 16/09/2017
 */

public class ResUtils {

  public static String getString(@StringRes int id) {
    return BaseApplication.getInstance().getString(id);
  }

  public static String getString(@StringRes int id, Object... formatArgs) {
    return BaseApplication.getInstance().getString(id, formatArgs);
  }

  public static Drawable getDrawable(@DrawableRes int id) {
    return AppCompatResources.getDrawable(BaseApplication.getInstance(), id);
  }

  public static int getColor(@ColorRes int id) {
    return ContextCompat.getColor(BaseApplication.getInstance(), id);
  }

  public static Context getAppContext() {
    return BaseApplication.getInstance().getApplicationContext();
  }
}

