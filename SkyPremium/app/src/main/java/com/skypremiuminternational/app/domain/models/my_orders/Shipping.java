package com.skypremiuminternational.app.domain.models.my_orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



public class Shipping implements Serializable {
  @SerializedName("total")
  @Expose
  private Total total;
  @SerializedName("address")
  @Expose
  private Address address;
  @SerializedName("method")
  @Expose
  private String method;

  public Total getTotal() {
    return total;
  }

  public void setTotal(Total total) {
    this.total = total;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }
}
