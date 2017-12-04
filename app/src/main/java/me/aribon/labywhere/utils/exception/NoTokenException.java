package me.aribon.labywhere.utils.exception;

/**
 * Created by anthony.ribon
 * On 16/08/2017
 */

public class NoTokenException extends Exception {

  public NoTokenException() {
    super("No token found. Please login.");
  }
}
