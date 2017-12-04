package me.aribon.labywhere.ui.screen.profile;

import android.support.annotation.NonNull;

import me.aribon.labywhere.ui.base.BaseMvpPresenter;
import me.aribon.labywhere.ui.base.BaseMvpView;

/**
 * Created by anthony.ribon
 * On 13/08/2017
 */
public interface ProfileContract {

    interface View extends BaseMvpView {

        void setUserNameField(@NonNull String userName);

        void setUserInfoField(@NonNull String userInfos);

        void navigateToEditProfile();
    }

    interface Presenter extends BaseMvpPresenter<View> {

        void onEditProfileClick();

        void onAccountParametersClick();

        void onAppOptionsClick();

    }
}