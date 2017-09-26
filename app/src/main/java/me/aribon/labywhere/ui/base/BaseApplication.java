package me.aribon.labywhere.ui.base;

import android.app.Application;

/**
 * Created by anthony.ribon
 * On 16/09/2017
 */

public class BaseApplication extends Application {

  private static BaseApplication instance;

  public static BaseApplication getInstance() {
    return instance;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
  }
}
