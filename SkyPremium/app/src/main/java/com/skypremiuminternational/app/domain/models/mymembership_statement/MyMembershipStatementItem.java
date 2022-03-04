package com.skypremiuminternational.app.domain.models.mymembership_statement;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MyMembershipStatementItem implements Serializable {
    @SerializedName("membership_founder_program")
    @Expose
    private int membershipFounderProgram;
    @SerializedName("type")
    @Expose
    private String Type;
    @SerializedName("membership_tier")
    @Expose
    private String membershipTier;
    @SerializedName("membership_fee")
    @Expose
    private String membershipFee;
    @SerializedName("membership_start_date")
    @Expose
    private String membershipStartDate;
    @SerializedName("membership_end_date")
    @Expose
    private String membershipEndDate;
    @SerializedName("membership_fee_payment_date")
    @Expose
    private String membershipFeePaymentDate;
    @SerializedName("payment_plan")
    @Expose
    private String paymentPlan;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("next_membership_fee_payment_date")
    @Expose
    private String nextMembershipFeePaymentDate;
    @SerializedName("membership_fee_invoice")
    @Expose
    private String membershipFeeInvoice;
    @SerializedName("associatedCode")
    @Expose
    private String associatedCode;


    public int getMembershipFounderProgram() {
        return membershipFounderProgram;
    }

    public void setMembershipFounderProgram(int membershipFounderProgram) {
        this.membershipFounderProgram = membershipFounderProgram;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getMembershipTier() {
        return membershipTier;
    }

    public void setMembershipTier(String membershipTier) {
        this.membershipTier = membershipTier;
    }

    public String getMembershipFee() {
        return membershipFee;
    }

    public void setMembershipFee(String membershipFee) {
        this.membershipFee = membershipFee;
    }

    public String getMembershipStartDate() {
        return membershipStartDate;
    }

    public void setMembershipStartDate(String membershipStartDate) {
        this.membershipStartDate = membershipStartDate;
    }

    public String getMembershipEndDate() {
        return membershipEndDate;
    }

    public void setMembershipEndDate(String membershipEndDate) {
        this.membershipEndDate = membershipEndDate;
    }

    public String getMembershipFeePaymentDate() {
        return membershipFeePaymentDate;
    }

    public void setMembershipFeePaymentDate(String membershipFeePaymentDate) {
        this.membershipFeePaymentDate = membershipFeePaymentDate;
    }

    public String getPaymentPlan() {
        return paymentPlan;
    }

    public void setPaymentPlan(String paymentPlan) {
        this.paymentPlan = paymentPlan;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getNextMembershipFeePaymentDate() {
        return nextMembershipFeePaymentDate;
    }

    public void setNextMembershipFeePaymentDate(String nextMembershipFeePaymentDate) {
        this.nextMembershipFeePaymentDate = nextMembershipFeePaymentDate;
    }

    public String getMembershipFeeInvoice() {
        return membershipFeeInvoice;
    }

    public void setMembershipFeeInvoice(String membershipFeeInvoice) {
        this.membershipFeeInvoice = membershipFeeInvoice;
    }

    public String getAssociatedCode() {
        return associatedCode;
    }

    public void setAssociatedCode(String associatedCode) {
        this.associatedCode = associatedCode;
    }
}
