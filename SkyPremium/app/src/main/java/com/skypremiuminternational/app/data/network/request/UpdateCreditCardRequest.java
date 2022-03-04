package com.skypremiuminternational.app.data.network.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;



public class UpdateCreditCardRequest implements Serializable {

  @SerializedName("card")
  @Expose
  private CardItem card;

  public UpdateCreditCardRequest(CardItem card) {
    this.card = card;
  }

  public CardItem getCard() {
    return card;
  }

  public void setCard(CardItem card) {
    this.card = card;
  }



  public static class CardItem implements Serializable {
    @SerializedName("exp_month")
    @Expose
    private Integer expMonth;
    @SerializedName("exp_year")
    @Expose
    private Integer expYear;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("country")
    @Expose
    private String country;
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


    public CardItem() {
    }

    public Integer getExpMonth() {
      return expMonth;
    }

    public void setExpMonth(Integer expMonth) {
      this.expMonth = expMonth;
    }

    public Integer getExpYear() {
      return expYear;
    }

    public void setExpYear(Integer expYear) {
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
  }
}
