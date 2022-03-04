package com.skypremiuminternational.app.data.network.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.skypremiuminternational.app.domain.models.user.CustomAttribute;

import java.io.Serializable;
import java.util.List;

public class UpdateUserDeliveryRequest implements Serializable {

    @SerializedName("customer")
    @Expose
    private Customer customer;
    private transient String member_id;

    public UpdateUserDeliveryRequest(Customer customer, String member_id) {
        this.customer = customer;
        this.member_id = member_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
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
        private List<AddressDeliveryRequest> addresses;
        @SerializedName("custom_attributes")
        @Expose
        private List<CustomAttribute> customAttributes;

        public Customer(Integer id, Integer websiteId, String firstname, String lastname, String email,
                        List<CustomAttribute> customAttributes) {
            this.id = id;
            this.websiteId = websiteId;
            this.firstname = firstname;
            this.lastname = lastname;
            this.email = email;
            this.customAttributes = customAttributes;
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

        public List<AddressDeliveryRequest> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<AddressDeliveryRequest> addresses) {
            this.addresses = addresses;
        }
    }
}
