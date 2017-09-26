package me.aribon.labywhere.ui.screen.test;

import me.aribon.labywhere.backend.interactor.InteractorResponse;
import me.aribon.labywhere.backend.interactor.UserInteractor;
import me.aribon.labywhere.backend.model.User;
import me.aribon.labywhere.backend.utils.AutoPurgeSubscriber;
import me.aribon.labywhere.ui.base.BasePresenter;
import me.aribon.labywhere.ui.screen.test.TestHomeDialogFragmentContract.View;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by anthony.ribon
 * On 25/09/2017
 */

class TestHomeDialogFragmentPresenter
    extends BasePresenter<TestHomeDialogFragmentContract.View>
    implements TestHomeDialogFragmentContract.Presenter {

  public TestHomeDialogFragmentPresenter(View mvpView) {
    super(mvpView);
  }

  public void loadData() {

    int userIdThatIwant = 12;

    subscribeTo(
        UserInteractor.getInstance().retrieve(userIdThatIwant)
            .observeOn(AndroidSchedulers.mainThread()),
        new AutoPurgeSubscriber<InteractorResponse<User>>() {
          @Override
          public void onNext(InteractorResponse<User> response) {
            super.onNext(response);
            if (response != null) {
              User user = response.getObject();
              getView().showDataResult(user.toString());
            }
          }
        }
    );
  }
}
