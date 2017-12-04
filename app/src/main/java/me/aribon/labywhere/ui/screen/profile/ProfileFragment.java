package me.aribon.labywhere.ui.screen.profile;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.aribon.labywhere.R;
import me.aribon.labywhere.ui.base.BaseFragment;
import me.aribon.labywhere.ui.screen.profile.edit.EditProfileActivity;

public class ProfileFragment extends BaseFragment
    implements ProfileContract.View {

    ProfileContract.Presenter presenter;

    @Bind(R.id.profile_user_name_text)
    TextView tvUserName;
    @Bind(R.id.profile_user_infos_text)
    TextView tvUserInfos;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_profile;
    }

    @Override
    public void findView(View view) {
        super.findView(view);
        ButterKnife.bind(this, view);
    }

    @Override
    public void initializePresenter() {
        super.initializePresenter();
        presenter = new ProfilePresenter(getParentActivity(), this);
    }

    @Override
    public void initializeData() {
        super.initializeData();
        //TODO init profile data
    }

    @Override
    public void initializeView() {
        super.initializeView();
        presenter.onStart();
    }

    @OnClick(R.id.profile_edit_field)
    public void onEditClick() {
        presenter.onEditProfileClick();
    }

    @OnClick(R.id.profile_account_parameters_field)
    public void onAccountParametersClick() {
        presenter.onAccountParametersClick();
    }

    @OnClick(R.id.profile_app_options_field)
    public void onAppOptionsClick() {
        presenter.onAppOptionsClick();
    }

    @Override
    public void setUserNameField(@NonNull String userName) {
        tvUserName.setText(userName);
    }

    @Override
    public void setUserInfoField(@NonNull String userInfos) {
        tvUserInfos.setText(userInfos);
    }

    @Override
    public void navigateToEditProfile() {
        Intent intent = new Intent(getParentActivity(), EditProfileActivity.class);
        getParentActivity().startActivity(intent);
    }
}
