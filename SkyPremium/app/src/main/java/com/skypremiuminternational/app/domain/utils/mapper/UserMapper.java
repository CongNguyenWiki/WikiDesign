package com.skypremiuminternational.app.domain.utils.mapper;

import static java.lang.Double.parseDouble;

import android.text.TextUtils;

import com.skypremiuminternational.app.app.utils.DateTimeCountDown;


import com.skypremiuminternational.app.domain.models.user.CustomAttribute;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;

import javax.inject.Inject;

public class UserMapper {






  public static UserDetailResponse flatInfo(UserDetailResponse userDetail){

    String memberExpiryPrompt = "";
    String salutation = "";
    String loyaltyPoints = "";
    String avatar = "";
    String memeberExpiryDate = "";
    String referralCode = "";
    String loyaltyPointExpiryDate = "";
    if (userDetail.getCustomAttributes() != null) {
      for (CustomAttribute customAttribute : userDetail.getCustomAttributes()) {
        if (customAttribute.getAttributeCode().equalsIgnoreCase("member_loyalty_point")) {
          loyaltyPoints = customAttribute.getValue();
        } else if (customAttribute.getAttributeCode().equalsIgnoreCase("referral_code")) {
          referralCode = customAttribute.getValue();
        } else if (customAttribute.getAttributeCode().equalsIgnoreCase("avatar")) {
          avatar = customAttribute.getValue();
        } else if (customAttribute.getAttributeCode().equalsIgnoreCase("salutation")) {
          salutation = customAttribute.getValue();
        } else if (customAttribute.getAttributeCode().equalsIgnoreCase("member_expiry_date")) {
          memeberExpiryDate = customAttribute.getValue();
        } else if (customAttribute.getAttributeCode().equalsIgnoreCase("member_expiry_prompt")) {
          memberExpiryPrompt = customAttribute.getValue();
        } else if (customAttribute.getAttributeCode().equalsIgnoreCase("loyalty_point_expiry_date")) {
          loyaltyPointExpiryDate = customAttribute.getValue();
        }
      }
    }

    if (!TextUtils.isEmpty(memeberExpiryDate) && memeberExpiryDate != null){
      userDetail.setExpiryDate(memeberExpiryDate);
    }
    if (!TextUtils.isEmpty(memeberExpiryDate) && memeberExpiryDate != null){
      userDetail.setDaysLeft(DateTimeCountDown.calculateDaysLeft(memeberExpiryDate));
    }
    if (!TextUtils.isEmpty(referralCode) && referralCode != null){
      userDetail.setReferralCode(referralCode);
    }
    if (!TextUtils.isEmpty(avatar) && avatar != null){
      userDetail.setAvatar(avatar);
    }
    if (!TextUtils.isEmpty(memberExpiryPrompt) && memberExpiryPrompt != null){
      userDetail.setShowMemberExpiryPrompt("1".equalsIgnoreCase(memberExpiryPrompt));
    }
    if (!TextUtils.isEmpty(loyaltyPoints) && loyaltyPoints != null){
      userDetail.setLoyaltyPoints(parseDouble(loyaltyPoints));
    }
    if (!TextUtils.isEmpty(loyaltyPointExpiryDate) && loyaltyPointExpiryDate != null){
      userDetail.setLoyaltyPointExpiryDate(loyaltyPointExpiryDate);
    }
    if (!TextUtils.isEmpty(salutation) && salutation != null){
      userDetail.setSalutation(salutation);
    }



    return userDetail;
  }
}
