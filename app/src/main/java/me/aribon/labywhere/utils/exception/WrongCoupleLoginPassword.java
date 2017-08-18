package me.aribon.labywhere.utils.exception;

/**
 * Created by anthony.ribon
 * On 16/08/2017
 */

public class WrongCoupleLoginPassword extends Exception {

  public WrongCoupleLoginPassword() {
    super("Wrong login or password. Retry with good credentials.");
  }
}
