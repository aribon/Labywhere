package me.aribon.labywhere.ui.screen.home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import butterknife.ButterKnife;
import me.aribon.labywhere.R;
import me.aribon.labywhere.ui.base.BaseActivity;
import me.aribon.labywhere.ui.screen.friend.ContactsListFragment;
import me.aribon.labywhere.ui.screen.map.MapFragment;
import me.aribon.labywhere.ui.screen.message.MessagesListFragment;
import me.aribon.labywhere.ui.screen.notification.NotificationsListFragment;
import me.aribon.labywhere.ui.screen.profile.ProfileFragment;

public class HomeActivity extends BaseActivity
        implements HomeContract.View {

    HomeContract.Presenter presenter;

    ViewPager viewPager;
    TabLayout tabLayout;

    HomeViewPagerAdapter adapter;

    @Override
    public int getLayoutResource() {
        return R.layout.activity_home;
    }

    @Override
    public void findView() {
        super.findView();
        ButterKnife.bind(this);

        viewPager = (ViewPager) findViewById(R.id.home_pager);
        tabLayout = (TabLayout) findViewById(R.id.home_tabs);
    }

    @Override
    public void initializePresenter() {
        super.initializePresenter();
        presenter = new HomePresenter(this);
    }

    @Override
    public void initializeView() {
        super.initializeView();
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(2);
    }

    private void setupViewPager(ViewPager viewPager) {
        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ProfileFragment(), "Profile");
        adapter.addFragment(new ContactsListFragment(), "Contacts");
        adapter.addFragment(new MapFragment(), "Map");
        adapter.addFragment(new MessagesListFragment(), "Messages");
        adapter.addFragment(new NotificationsListFragment(), "Notifications");
        viewPager.setAdapter(adapter);
    }

  /*@OnClick(R.id.home_load_btn)
  public void onStartClick() {
    presenter.startClick();
  }*/

  /*public void showTestHomeDialogFragment() {
    TestHomeDialogFragmentRouter.show(this);
  }*/
}
