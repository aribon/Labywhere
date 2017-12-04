package me.aribon.labywhere.ui.screen.login;

import android.text.TextUtils;
import android.util.Log;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.backend.provider.preferences.AccountPreferences;
import me.aribon.labywhere.business.interactor.AccountInteractor;
import me.aribon.labywhere.ui.base.BaseActivity;
import me.aribon.labywhere.ui.base.BasePresenter;
import me.aribon.labywhere.ui.model.UserParcelable;
import me.aribon.labywhere.ui.screen.home.HomeRouter;
import me.aribon.labywhere.ui.screen.login.LoginContact.View;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class LoginPresenter extends BasePresenter<LoginContact.View>
        implements LoginContact.Presenter {

    public static final String TAG = LoginPresenter.class.getSimpleName();

    private BaseActivity activity;

    public LoginPresenter(BaseActivity activity, View view) {
        super(view);
        this.activity = activity;
    }

    @Override
    public void onValidateClick(String email, String password) {
        if (checkLogin(email, password)) {
            //todo here login
            getView().showToastMessage("Coming soon");
        }
    }

    private boolean checkLogin(String email, String password) {

        Log.d(TAG, "login");

        getView().showLoading();

        if (TextUtils.isEmpty(email)) {
            //getView().setEmailError();
            getView().hideLoading();
            Log.e(TAG, "onNext: Email empty");
            return true;
        }

        if (TextUtils.isEmpty(password)) {
            //getView().setPasswordError();
            getView().hideLoading();
            Log.e(TAG, "onNext: Password empty");
            return true;
        }

        UserParcelable userParcelable = new UserParcelable();
        userParcelable.setEmail(email);
        userParcelable.setPassword(password);

        login(userParcelable);

        return false;
    }

    private void login(UserParcelable userParcelable) {
        AccountInteractor.getInstance()
                .doLoginByCredentials(userParcelable)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                navigateToHome();
                            }

                            @Override
                            public void onError(Throwable e) {
                                getView().showToastMessage("[DEV] Login failed ! Please try again\nError : "
                                        + e.getMessage());
                            }
                        }
                );
    }

    private void loadAccount() {
        /*subscribeTo(
                ProfileManager.getInstance().loadAccount()
                        .observeOn(AndroidSchedulers.mainThread()),
                new AutoPurgeSubscriber<User>() {
                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        super.onNext(user);
                        saveAccount(user);
                        Log.d(TAG, "loadAccount onNext: " + user.toString());
                        Log.d(TAG, "loadAccount onNext: " + user.getProfile().toString());
//                        AuthRouter.startHomeActivity(getActivity());
                    }
                }
        );*/
    }

    private void navigateToHome() {
        HomeRouter.startHomeActivity(activity);
    }

    private void saveAccount(User user) {
        AccountPreferences.setAccount(user);
    }
}
