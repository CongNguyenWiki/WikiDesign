package com.skypremiuminternational.app.domain.models.favourite;

import com.google.gson.annotations.SerializedName;

public class DisplayReservationsCategory {
  @SerializedName("reserve_button_title")
  String title;
  @SerializedName("reserve_button_link")
  String reserveButtonLink;

  @SerializedName("hgw_link")
  String hgwLink;
  @SerializedName("enable")
  Boolean isEnable;
  @SerializedName("restaurant_IDS")
  String restaurantIDS;


  public String getHgwLink() {
    return hgwLink;
  }

  public void setHgwLink(String hgwLink) {
    this.hgwLink = hgwLink;
  }

  public String getReserveButtonLink() {
    return reserveButtonLink;
  }

  public void setReserveButtonLink(String reserveButtonLink) {
    this.reserveButtonLink = reserveButtonLink;
  }

  public String getRestaurantIDS() {
    return restaurantIDS;
  }

  public void setRestaurantIDS(String restaurantIDS) {
    this.restaurantIDS = restaurantIDS;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLink() {
    return reserveButtonLink;
  }

  public void setLink(String reserveButtonLink) {
    this.reserveButtonLink = reserveButtonLink;
  }

  public Boolean getEnable() {
    return isEnable;
  }

  public void setEnable(Boolean enable) {
    isEnable = enable;
  }
}
