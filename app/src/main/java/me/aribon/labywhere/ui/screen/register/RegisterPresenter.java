package me.aribon.labywhere.ui.screen.register;

import android.text.TextUtils;
import android.util.Log;

import java.util.Map;

import me.aribon.labywhere.backend.manager.ProfileManager;
import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.backend.provider.preferences.AccountPreferences;
import me.aribon.labywhere.backend.provider.preferences.AuthPreferences;
import me.aribon.labywhere.backend.provider.network.AuthNetworkProvider;
import me.aribon.labywhere.backend.provider.network.response.AuthResponse;
import me.aribon.labywhere.backend.utils.AutoPurgeSubscriber;
import me.aribon.labywhere.ui.base.BaseActivity;
import me.aribon.labywhere.ui.base.BasePresenter;
import me.aribon.labywhere.ui.screen.register.RegisterContract.View;

/**
 * Created on 24/04/2016
 *
 * @author Anthony
 */
public class RegisterPresenter extends BasePresenter<RegisterContract.View>
    implements RegisterContract.Presenter {

    public static final String TAG = RegisterPresenter.class.getSimpleName();

    private BaseActivity activity;

    public RegisterPresenter(BaseActivity activity,View mvpView) {
        super(mvpView);
        this.activity = activity;
    }

    private boolean checkRegister(String email, String password, String firstname, String lastname) {

        Log.d(TAG, "login");

        if (TextUtils.isEmpty(email)) {
            getView().setEmailError(""); //TODO
            Log.e(TAG, "onNext: email empty");
            return true;
        }

        if (TextUtils.isEmpty(password)) {
            getView().setPasswordError(""); //TODO
            Log.e(TAG, "onNext: password empty");
            return true;
        }

        if (TextUtils.isEmpty(firstname)) {
            getView().setFirstnameError(""); //TODO
            Log.e(TAG, "onNext: firstname empty");
            return true;
        }

        if (TextUtils.isEmpty(lastname)) {
            getView().setLastnameError(""); //TODO
            Log.e(TAG, "onNext: lastname empty");
            return true;
        }

        return false;
    }

    public void register(Map<String, String> body) {

        subscribeTo(
                AuthNetworkProvider.getInstance().register(body),
                new AutoPurgeSubscriber<AuthResponse>() {
                    @Override
                    public void onNext(AuthResponse authResponse) {
                        super.onNext(authResponse);
                        if (authResponse.isError()) {
                            //TODO set error
                        } else {
                            //login(credentials);
                        }
                    }
                }
        );
    }

    private void login(Map<String, String> credentials) {
        subscribeTo(
                AuthNetworkProvider.getInstance().login(credentials),
                new AutoPurgeSubscriber<AuthResponse>() {
                    @Override
                    public void onNext(AuthResponse authResponse) {
                        super.onNext(authResponse);
                        if (authResponse.isError()) {
                            //TODO set error
                        } else {
                            AuthPreferences.setAuthToken(authResponse.getToken()); //Save token in preference
                            loadAccount(); //Load user data
                        }
                    }
                }
        );
    }

    private void loadAccount() {

        subscribeTo(
                ProfileManager.getInstance().loadAccount(),
                new AutoPurgeSubscriber<User>() {

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "loadAccount onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "loadAccount onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(User user) {
                        saveAccount(user);
                        Log.d(TAG, "loadAccount onNext: " + user.toString());
                        Log.d(TAG, "loadAccount onNext: " + user.getProfile().toString());
//                        AuthRouter.startHomeActivity(getActivity());
                    }
                }
        );
    }

    private void saveAccount(User user) {
        AccountPreferences.setAccount(user);
    }

    @Override
    public void onValidateClick(String email, String password, String firstname, String lastname) {
        if (checkRegister(email, password, firstname, lastname)) {
            getView().showToastMessage("Coming soon !");
            //todo register here !
        }
    }
}
