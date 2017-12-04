package me.aribon.labywhere.ui.base.lib;

/**
 * Created by anthony.ribon
 * On 15/08/2017
 */

public interface AndroidLifecycle {

    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
