package com.skypremiuminternational.app.app.utils;

import javax.inject.Inject;


public class CreditNumberValidator {

  @Inject
  public CreditNumberValidator() {

  }

  public boolean validate(String creditNumber) {
    if (creditNumber == null) return false;
    if (creditNumber.trim().equalsIgnoreCase("")) return false;
    if (creditNumber.length() != 16) return false;
    if (creditNumber.contains(" ")) return false;

    boolean alternate = false;
    int sum = 0;
    for (int i = creditNumber.length() - 1; i >= 0; i--) {
      int n = Integer.parseInt(creditNumber.substring(i, i + 1));
      if (alternate) {
        n *= 2;
        if (n > 9) {
          n = (n % 10) + 1;
        }
      }
      sum += n;
      alternate = !alternate;
    }
    return (sum % 10 == 0);
  }
}
