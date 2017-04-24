package me.aribon.labywhere.ui.screen.auth;

import android.os.Handler;

import me.aribon.labywhere.base.AppBaseActivity;

/**
 * Created on 23/04/2017
 *
 * @author Anthony
 */
public class AuthRouter {

    private void startAuthActivityWithDelay(AppBaseActivity activity, long delay) {
        Handler handler = new Handler();
//        handler.postDelayed(this::startAuthActivity, delay);
    }

    private void startAuthActivity(AppBaseActivity activity) {
        AuthActivity.startActivity(activity);
    }
}
