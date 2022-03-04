package com.skypremiuminternational.app.app.features.profile.edit_profile;

import com.skypremiuminternational.app.app.internal.mvp.contract.Presentable;
import com.skypremiuminternational.app.app.internal.mvp.contract.Viewable;
import com.skypremiuminternational.app.domain.models.country_code.CountryCode;
import com.skypremiuminternational.app.domain.models.nationality.Nationality;
import com.skypremiuminternational.app.domain.models.phone_code.PhoneCode;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;

import java.util.List;

public interface EditProfileView<T extends Presentable> extends Viewable<T> {


  void renderUserDetail(UserDetailResponse response);

  void updateUserProfileSuccess();


  void returnZoneConfig(List<CountryCode> countryCodes, List<Nationality> nationalities, PhoneCode phoneCodes);
}
