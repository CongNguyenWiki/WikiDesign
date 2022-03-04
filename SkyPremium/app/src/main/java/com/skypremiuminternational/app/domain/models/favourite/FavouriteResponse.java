package com.skypremiuminternational.app.domain.models.favourite;



public class FavouriteResponse {

  public final String message;
  public final boolean status;

  public FavouriteResponse(String message, boolean status) {
    this.message = message;
    this.status = status;
  }

  public static FavouriteResponse numExceeded() {
    return new FavouriteResponse("You have exceeded the number of favourite items", false);
  }
}
