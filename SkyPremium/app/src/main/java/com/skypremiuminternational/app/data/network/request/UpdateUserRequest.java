package com.skypremiuminternational.app.data.network.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.skypremiuminternational.app.domain.models.Address;
import com.skypremiuminternational.app.domain.models.user.CustomAttribute;


import java.io.Serializable;
import java.util.List;


public class UpdateUserRequest implements Serializable {

  @SerializedName("customer")
  @Expose
  private Customer customer;
  private transient String member_number;


  // old
  public UpdateUserRequest(Customer customer, String member_number) {
    this.customer = customer;
    this.member_number = member_number;
  }

  public UpdateUserRequest(Customer customer) {
    this.customer = customer;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public String getMemberNumber() {
    return member_number;
  }

  public void setMemberNumber(String member_number) {
    this.member_number = member_number;
  }

  public static class Customer implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("website_id")
    @Expose
    private Integer websiteId;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("addresses")
    @Expose
    private List<Address> addresses;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("custom_attributes")
    @Expose
    private List<CustomAttribute> customAttributes = null;

    public Customer(Integer id, Integer websiteId, String firstname, String lastname, String email,
                    String dob,int gender,   List<CustomAttribute> customAttributes) {
      this.id = id;
      this.websiteId = websiteId;
      this.firstname = firstname;
      this.lastname = lastname;
      this.email = email;
      this.dob = dob;
      this.gender = gender;
      this.customAttributes = customAttributes;
    }


    public String getDob() {
      return dob;
    }

    public void setDob(String dob) {
      this.dob = dob;
    }

    public Integer getId() {
      return id;
    }

    public void setId(Integer id) {
      this.id = id;
    }

    public Integer getWebsiteId() {
      return websiteId;
    }

    public void setWebsiteId(Integer websiteId) {
      this.websiteId = websiteId;
    }

    public String getFirstname() {
      return firstname;
    }

    public void setFirstname(String firstname) {
      this.firstname = firstname;
    }

    public String getLastname() {
      return lastname;
    }

    public void setLastname(String lastname) {
      this.lastname = lastname;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public List<CustomAttribute> getCustomAttributes() {
      return customAttributes;
    }

    public void setCustomAttributes(List<CustomAttribute> customAttributes) {
      this.customAttributes = customAttributes;
    }

    public List<Address> getAddresses() {
      return addresses;
    }

    public void setAddresses(List<Address> addresses) {
      this.addresses = addresses;
    }

    public Integer getGender() {
      return gender;
    }

    public void setGender(Integer gender) {
      this.gender = gender;
    }
  }
}
