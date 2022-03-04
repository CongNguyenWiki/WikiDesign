package com.skypremiuminternational.app.domain.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class CustomAttribute implements Serializable {

  public static final String  MEMBER_NUMBER = "member_number";


  @SerializedName("attribute_code")
  @Expose
  private String attributeCode;
  @SerializedName("value")
  @Expose
  private String value;

  public CustomAttribute() {
  }

  public CustomAttribute(String attributeCode, String value) {
    this.attributeCode = attributeCode;
    this.value = value;
  }

  public String getAttributeCode() {
    return attributeCode;
  }

  public void setAttributeCode(String attributeCode) {
    this.attributeCode = attributeCode;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}