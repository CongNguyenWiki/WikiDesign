package com.skypremiuminternational.app.domain.models.user;

public class UserAttributeMapped {

  String salutation;
  String memberExpiryDate;
  String loyaltyPointExpiryDate;


  public String getLoyaltyPointExpiryDate() {
    return loyaltyPointExpiryDate;
  }

  public void setLoyaltyPointExpiryDate(String loyaltyPointExpiryDate) {
    this.loyaltyPointExpiryDate = loyaltyPointExpiryDate;
  }

  public String getSalutation() {
    return salutation;
  }

  public void setSalutation(String salutation) {
    this.salutation = salutation;
  }

  public String getMemberExpiryDate() {
    return memberExpiryDate;
  }

  public void setMemberExpiryDate(String memberExpiryDate) {
    this.memberExpiryDate = memberExpiryDate;
  }
}
