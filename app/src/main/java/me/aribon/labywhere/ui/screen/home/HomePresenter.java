package me.aribon.labywhere.ui.screen.home;

import android.util.Log;

import me.aribon.labywhere.base.AppBasePresenter;
import me.aribon.labywhere.backend.interactor.InteractorResponse;
import me.aribon.labywhere.backend.interactor.UserInteractor;
import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.backend.utils.AutoPurgeSubscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class HomePresenter extends AppBasePresenter<HomeActivity> {

    private static final String TAG = HomePresenter.class.getSimpleName();

    @Override
    public void onResume() {
        super.onResume();
    }

    public void loadData() {

        int userIdThatIwant = 12;

        subscribeTo(
                UserInteractor.getInstance().retrieve(userIdThatIwant)
                        .observeOn(AndroidSchedulers.mainThread()),
                new AutoPurgeSubscriber<InteractorResponse<User>>() {

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
                    public void onNext(InteractorResponse<User> response) {
                        super.onNext(response);
                        if (response != null) {
                            User user = response.getObject();
                            Log.i(TAG, "loadData -> onNext: " + user.toString());
                            getView().setResultText(user.toString());
                        }
                    }
                }
        );
    }

}
