package me.aribon.labywhere.ui.base.lib;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by anthony.ribon
 * On 22/07/2017
 */

public abstract class MvpActivity extends AppCompatActivity implements MvpView {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResource());
    findView();
    initializePresenter();
    initializeData();
    initializeView();
  }

  public abstract int getLayoutResource();

  public void findView() {

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
    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
  }

  @Override
  public void showToastMessage(@StringRes int resId) {
    Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
  }

  @Override
  public void showKeyboard() {

  }

  @Override
  public void hideKeyboard() {

  }
}
