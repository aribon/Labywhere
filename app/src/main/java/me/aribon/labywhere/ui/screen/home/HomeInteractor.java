package me.aribon.labywhere.ui.screen.home;

/**
 * Created by anthony.ribon
 * On 27/06/2017
 */

public class HomeInteractor {

  private static final HomeInteractor instance = new HomeInteractor();

  public static HomeInteractor getInstance() {
    return instance;
  }

  private HomeInteractor() {
  }
}
