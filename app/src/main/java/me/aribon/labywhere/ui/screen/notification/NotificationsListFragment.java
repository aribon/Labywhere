package me.aribon.labywhere.ui.screen.notification;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.aribon.labywhere.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsListFragment extends Fragment {


    public NotificationsListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifications_list, container, false);
    }

}
