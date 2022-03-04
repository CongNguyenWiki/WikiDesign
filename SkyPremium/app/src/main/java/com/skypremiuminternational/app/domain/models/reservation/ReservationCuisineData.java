package com.skypremiuminternational.app.domain.models.reservation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReservationCuisineData implements Serializable {


  @SerializedName("cuisine_menu_id")
  String cuisineMenuId;
  @SerializedName("name")
  String name;
  @SerializedName("status")
  String status;
  @SerializedName("price")
  String price;

  public String getCuisineMenuId() {
    return cuisineMenuId;
  }

  public void setCuisineMenuId(String cuisineMenuId) {
    this.cuisineMenuId = cuisineMenuId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }
}
