package me.aribon.labywhere.ui.screen.splash;

import me.aribon.labywhere.ui.base.BaseMvpPresenter;
import me.aribon.labywhere.ui.base.BaseMvpView;

/**
 * Created by anthony.ribon
 * On 13/08/2017
 */

public interface SplashContract {

    interface View extends BaseMvpView {

        // TODO: 13/08/2017 start and show animation

        // TODO: 13/08/2017 stop or hide animation

        // TODO: 13/08/2017 show progress if needed

        // TODO: 13/08/2017 hide progress if needed

        // TODO: 13/08/2017 navigateToNextScreen
    }

    interface Presenter extends BaseMvpPresenter<View> {

        // TODO: 13/08/2017 1/ check connectivity

        // TODO: 13/08/2017 2/ load required data

        // TODO: 13/08/2017 3/ start & show animation if needed

        // TODO: 13/08/2017 4/ stop or hide animation

        // TODO: 13/08/2017 5/ navigate to next
    }
}
