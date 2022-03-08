package com.skypremiuminternational.app.domain.models.creditCard;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class CardItem implements Serializable {

  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("brand")
  @Expose
  private String brand;
  @SerializedName("last4")
  @Expose
  private String last4;
  @SerializedName("exp_month")
  @Expose
  private int expMonth;
  @SerializedName("exp_year")
  @Expose
  private int expYear;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("country")
  @Expose
  private String country;
  @SerializedName("country_name")
  @Expose
  private String countryName;
  @SerializedName("state_region_id")
  @Expose
  private String stateRegionId;
  @SerializedName("state")
  @Expose
  private String state;
  @SerializedName("postal_code")
  @Expose
  private String postalCode;
  @SerializedName("city")
  @Expose
  private String city;
  @SerializedName("address_street1")
  @Expose
  private String addressStreet1;
  @SerializedName("address_street2")
  @Expose
  private String addressStreet2;
  @SerializedName("is_default")
  @Expose
  private Boolean isDefault;
  private String card_number=null;
  private String card_cvc=null;







  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getLast4() {
    return last4;
  }

  public void setLast4(String last4) {
    this.last4 = last4;
  }

  public int getExpMonth() {
    return expMonth;
  }

  public void setExpMonth(int expMonth) {
    this.expMonth = expMonth;
  }

  public int getExpYear() {
    return expYear;
  }

  public void setExpYear(int expYear) {
    this.expYear = expYear;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCountryName() {
    return countryName;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public String getStateRegionId() {
    return stateRegionId;
  }

  public void setStateRegionId(String stateRegionId) {
    this.stateRegionId = stateRegionId;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getAddressStreet1() {
    return addressStreet1;
  }

  public void setAddressStreet1(String addressStreet1) {
    this.addressStreet1 = addressStreet1;
  }

  public String getAddressStreet2() {
    return addressStreet2;
  }

  public void setAddressStreet2(String addressStreet2) {
    this.addressStreet2 = addressStreet2;
  }

  public Boolean getDefault() {
    return isDefault;
  }

  public void setDefault(Boolean aDefault) {
    isDefault = aDefault;
  }

  public String getCard_number() {
    return card_number;
  }

  public void setCard_number(String card_number) {
    this.card_number = card_number;
  }

  public String getCard_cvc() {
    return card_cvc;
  }

  public void setCard_cvc(String card_cvc) {
    this.card_cvc = card_cvc;
  }
}
