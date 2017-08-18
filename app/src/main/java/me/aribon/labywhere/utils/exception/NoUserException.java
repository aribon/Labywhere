package me.aribon.labywhere.utils.exception;

/**
 * Created by anthony.ribon
 * On 16/08/2017
 */

public class NoUserException extends Exception {

  public NoUserException() {
    super("Null user found. Need a valid user.");
  }
}
