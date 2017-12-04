package me.aribon.labywhere.ui.screen.profile.edit;

import me.aribon.labywhere.ui.base.BaseMvpPresenter;
import me.aribon.labywhere.ui.base.BaseMvpView;

/**
 * Created by Shibo on 03/12/2017.
 */

public interface EditProfileContract {

    interface View extends BaseMvpView {

        void setFirstnameField();

        void setLastnameField();

        void setAgeField();

        void setCityField();

        void setCountryField();
    }

    interface Presenter extends BaseMvpPresenter {

        void onValidateClick(String firstname, String lastname, String city, String country);
    }
}
