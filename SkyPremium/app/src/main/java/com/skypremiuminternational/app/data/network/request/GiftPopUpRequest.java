package com.skypremiuminternational.app.data.network.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GiftPopUpRequest implements Serializable {

    @SerializedName("member_id")
    @Expose
    private int memberId;
    @SerializedName("store_id")
    @Expose
    private int storeId;

    /**
     * No args constructor for use in serialization
     */
    public GiftPopUpRequest() {
    }

    /**
     * @param memberId
     * @param storeId
     */
    public GiftPopUpRequest(int memberId, int storeId) {
        super();
        this.memberId = memberId;
        this.storeId = storeId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
}
