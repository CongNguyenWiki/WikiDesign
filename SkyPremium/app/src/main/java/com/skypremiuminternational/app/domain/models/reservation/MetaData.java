package com.skypremiuminternational.app.domain.models.reservation;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MetaData implements Serializable {


  @SerializedName("Base Url")
  String BaseUrl;
  @SerializedName("Customer Name")
  String CustomerName;
  @SerializedName("First Name")
  String FirstName;
  @SerializedName("Last Name")
  String LastName;
  @SerializedName("Email")
  String Email;

  public String getBaseUrl() {
    return BaseUrl;
  }

  public void setBaseUrl(String baseUrl) {
    BaseUrl = baseUrl;
  }

  public String getCustomerName() {
    return CustomerName;
  }

  public void setCustomerName(String customerName) {
    CustomerName = customerName;
  }

  public String getFirstName() {
    return FirstName;
  }

  public void setFirstName(String firstName) {
    FirstName = firstName;
  }

  public String getLastName() {
    return LastName;
  }

  public void setLastName(String lastName) {
    LastName = lastName;
  }

  public String getEmail() {
    return Email;
  }

  public void setEmail(String email) {
    Email = email;
  }


}
