package com.skypremiuminternational.app.domain.exception.cart;

import com.skypremiuminternational.app.domain.models.cart.CheckLimitResponse;

import java.util.List;



public class CartLimitException extends Exception {

  private List<CheckLimitResponse.LimitError> limitErrors;

  public CartLimitException(List<CheckLimitResponse.LimitError> limitErrors) {
    this.limitErrors = limitErrors;
  }

  public List<CheckLimitResponse.LimitError> getLimitErrors() {
    return limitErrors;
  }
}
