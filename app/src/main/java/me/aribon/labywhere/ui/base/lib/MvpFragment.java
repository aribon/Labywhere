package me.aribon.labywhere.ui.base.lib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by anthony.ribon
 * On 22/07/2017
 */

public abstract class MvpFragment extends Fragment implements MvpView {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializePresenter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResource(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findView(view);
        initializeData();
        initializeView();
    }

    public abstract int getLayoutResource();

    public void findView(View view) {

    }

    public void initializePresenter() {

    }

    public void initializeData() {

    }

    public void initializeView() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void showMessage(@StringRes int resId) {

    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showToastMessage(@StringRes int resId) {
        Toast.makeText(getContext(), getString(resId), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showKeyboard() {

    }

    @Override
    public void hideKeyboard() {

    }
}
