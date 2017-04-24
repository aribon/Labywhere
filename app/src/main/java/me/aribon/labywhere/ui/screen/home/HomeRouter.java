package me.aribon.labywhere.ui.screen.home;

import android.content.Intent;

import me.aribon.labywhere.base.AppBaseActivity;
import me.aribon.labywhere.ui.screen.auth.AuthActivity;

/**
 * Created on 23/04/2017
 *
 * @author Anthony
 */
public class HomeRouter {

    public static void startHomeActivity(AppBaseActivity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        activity.startActivity(intent);
        AuthActivity.stopActivity();
//        getView().setResult(Activity.RESULT_CANCELED);
        activity.finish();
    }
}
