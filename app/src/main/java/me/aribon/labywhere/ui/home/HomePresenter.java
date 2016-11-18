package me.aribon.labywhere.ui.home;

import android.util.Log;

import me.aribon.labywhere.LabywhereBasePresenter;
import me.aribon.labywhere.backend.interactor.UserInteractor;
import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.backend.utils.AutoPurgeSubscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class HomePresenter extends LabywhereBasePresenter<HomeActivity> {

    private static final String TAG = HomePresenter.class.getSimpleName();

    @Override
    public void onResume() {
        super.onResume();
    }

    public void loadData() {

        int userIdThatIwant = 12;

        subscribeTo(
                UserInteractor.getInstance().retrieveUser(userIdThatIwant)
                        .observeOn(AndroidSchedulers.mainThread()),
                new AutoPurgeSubscriber<User>() {

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        Log.d(TAG, "loadData -> onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Log.e(TAG, "loadData -> onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        super.onNext(user);
                        Log.i(TAG, "loadData -> onNext: " + user.toString());
                        mView.setResultText(user.toString());
                    }
                }
        );
    }

}
