package me.aribon.labywhere.ui.base.lib;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by anthony.ribon
 * On 17/09/2017
 */

public abstract class MvpDialogFragment extends DialogFragment implements MvpView {

  MvpActivity activity;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    activity = (MvpActivity) context;
  }

  @Override
  public void onDetach() {
    activity = null;
    super.onDetach();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initializePresenter();
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Dialog dialog;
    if (getStyleResource() > 0) {
      dialog = new Dialog(activity, getStyleResource());
    } else {
      dialog = new Dialog(activity);
    }

    final View view = activity.getLayoutInflater().inflate(getLayoutResource(), null);

    dialog.setContentView(view);

    addLayoutParams(dialog);

    findView(view);
    initializeData();
    initializeView();

    return dialog;
  }

  protected abstract int getLayoutResource();

  protected abstract int getStyleResource();

  public void findView(View view) {

  }

  public void initializePresenter() {

  }

  public void initializeData() {

  }

  public void initializeView() {

  }

  protected void addLayoutParams(Dialog dialog) {

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

  }

  @Override
  public void showToastMessage(@StringRes int resId) {

  }

  @Override
  public void showKeyboard() {

  }

  @Override
  public void hideKeyboard() {

  }
}
