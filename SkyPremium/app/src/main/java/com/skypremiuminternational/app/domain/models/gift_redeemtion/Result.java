package com.skypremiuminternational.app.domain.models.gift_redeemtion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Result implements Serializable {

    @SerializedName("category_id")
    @Expose
    public String categoryId;
    @SerializedName("campaign_type_id")
    @Expose
    public String campaignTypeId;
    @SerializedName("is_redeemed")
    @Expose
    public String isRedeemed;
    @SerializedName("campaign_id")
    @Expose
    public String campaignId;
    @SerializedName("button_label")
    @Expose
    public String buttonLabel;
    @SerializedName("is_expired")
    @Expose
    public Boolean isExpired;
    @SerializedName("link")
    @Expose
    public String link;



    public Result() {
    }

    public Result(String categoryId, String campaignTypeId, String isRedeemed, String campaignId, String buttonLabel, Boolean isExpired) {
        super();
        this.categoryId = categoryId;
        this.campaignTypeId = campaignTypeId;
        this.isRedeemed = isRedeemed;
        this.campaignId = campaignId;
        this.buttonLabel = buttonLabel;
        this.isExpired = isExpired;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCampaignTypeId() {
        return campaignTypeId;
    }

    public void setCampaignTypeId(String campaignTypeId) {
        this.campaignTypeId = campaignTypeId;
    }

    public String getIsRedeemed() {
        return isRedeemed;
    }

    public void setIsRedeemed(String isRedeemed) {
        this.isRedeemed = isRedeemed;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public void setButtonLabel(String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }


    public Boolean getExpired() {
        return isExpired;
    }

    public void setExpired(Boolean expired) {
        isExpired = expired;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}