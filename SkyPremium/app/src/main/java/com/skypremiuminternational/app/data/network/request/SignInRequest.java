package com.skypremiuminternational.app.data.network.request;

import com.google.gson.annotations.SerializedName;

public class SignInRequest {

  @SerializedName("username")
  String userName;
  @SerializedName("password")
  String Password;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return Password;
  }

  public void setPassword(String password) {
    Password = password;
  }
}
