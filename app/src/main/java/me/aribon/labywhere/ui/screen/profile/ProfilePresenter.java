package me.aribon.labywhere.ui.screen.profile;

import me.aribon.labywhere.ui.base.BaseActivity;
import me.aribon.labywhere.ui.base.BasePresenter;

/**
 * Created by Shibo on 03/12/2017.
 */

public class ProfilePresenter extends BasePresenter<ProfileContract.View>
        implements ProfileContract.Presenter {

    private BaseActivity activity;

    public ProfilePresenter(BaseActivity activity, ProfileContract.View mvpView) {
        super(mvpView);
        this.activity = activity;
    }

    @Override
    public void onEditProfileClick() {
        getView().navigateToEditProfile();
    }

    @Override
    public void onAccountParametersClick() {
        getView().showToastMessage("Coming soon");
    }

    @Override
    public void onAppOptionsClick() {
        getView().showToastMessage("Coming soon");
    }
}
