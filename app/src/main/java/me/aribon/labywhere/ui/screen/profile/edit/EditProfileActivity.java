package me.aribon.labywhere.ui.screen.profile.edit;

import butterknife.ButterKnife;
import me.aribon.labywhere.R;
import me.aribon.labywhere.ui.base.BaseActivity;

public class EditProfileActivity extends BaseActivity
        implements EditProfileContract.View {

    @Override
    public int getLayoutResource() {
        return R.layout.activity_update_profile;
    }

    @Override
    public void findView() {
        super.findView();
        ButterKnife.bind(this);
    }

    @Override
    public void setFirstnameField() {

    }

    @Override
    public void setLastnameField() {

    }

    @Override
    public void setAgeField() {

    }

    @Override
    public void setCityField() {

    }

    @Override
    public void setCountryField() {

    }
}
