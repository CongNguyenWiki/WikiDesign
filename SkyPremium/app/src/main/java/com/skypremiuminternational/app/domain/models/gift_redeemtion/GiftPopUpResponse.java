package com.skypremiuminternational.app.domain.models.gift_redeemtion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GiftPopUpResponse implements Serializable {

    @SerializedName("result")
    @Expose
    public List<Result> result;

    public GiftPopUpResponse() {
    }

    public GiftPopUpResponse(List<Result> result) {
        super();
        this.result = result;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}
