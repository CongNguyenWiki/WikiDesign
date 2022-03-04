package com.skypremiuminternational.app.domain.models.mymembership_statement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InforMembershipResponse implements Serializable {

    @SerializedName("account_status")
    @Expose
    private String accountStatus;
    @SerializedName("contract_status")
    @Expose
    private String contractStatus;
    @SerializedName("membership_tier")
    @Expose
    private String membershipTier;
    @SerializedName("expiry_date")
    @Expose
    private String expiryDate;
    @SerializedName("customer_id")
    @Expose
    private String customerId;
    @SerializedName("url")
    @Expose
    private UrlResponse url;
    @SerializedName("payment_plan")
    @Expose
    private String paymentPlan;


    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getMembershipTier() {
        return membershipTier;
    }

    public void setMembershipTier(String membershipTier) {
        this.membershipTier = membershipTier;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public UrlResponse getUrl() {
        return url;
    }

    public void setUrl(UrlResponse url) {
        this.url = url;
    }

    public String getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(String paymentPlan) {
        this.paymentPlan = paymentPlan;
    }
}
