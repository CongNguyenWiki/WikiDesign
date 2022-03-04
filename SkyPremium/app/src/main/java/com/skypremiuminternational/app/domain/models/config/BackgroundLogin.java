package com.skypremiuminternational.app.domain.models.config;

import com.google.gson.annotations.SerializedName;

public class BackgroundLogin {

    @SerializedName("background_image")
    String backgroundImage;


    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
}
