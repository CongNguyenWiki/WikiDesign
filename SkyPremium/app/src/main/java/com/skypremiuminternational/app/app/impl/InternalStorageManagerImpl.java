package com.skypremiuminternational.app.app.impl;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skypremiuminternational.app.BuildConfig;
import com.skypremiuminternational.app.app.utils.Constants;
import com.skypremiuminternational.app.app.utils.DataUtils;
import com.skypremiuminternational.app.app.utils.PreferenceUtils;
import com.skypremiuminternational.app.data.InternalStorageManager;
import com.skypremiuminternational.app.domain.models.category.CategoryResponse;
import com.skypremiuminternational.app.domain.models.comment_rating.RatingOption;
import com.skypremiuminternational.app.domain.models.config.BackgroundLogin;
import com.skypremiuminternational.app.domain.models.country_code.CountryCode;
import com.skypremiuminternational.app.domain.models.nationality.Nationality;
import com.skypremiuminternational.app.domain.models.phone_code.PhoneCode;
import com.skypremiuminternational.app.domain.models.system.OneSignalConfig;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;


import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import timber.log.Timber;


@Singleton
public class InternalStorageManagerImpl implements InternalStorageManager {

  private static final String KEY_CART_ID = "cart_id";
  private final PreferenceUtils preferenceUtils;
  private final DataUtils dataUtils;

  @Inject
  public InternalStorageManagerImpl(PreferenceUtils preferenceUtils, DataUtils dataUtils) {
    this.preferenceUtils = preferenceUtils;
    this.dataUtils = dataUtils;
  }


  @Override
  public void clearAuthToken() {
    preferenceUtils.remove("userToken");

  }

  @Override
  public void saveCartCount(String count) {
    preferenceUtils.save(Constants.CART_COUNT, count);
  }

  @Override
  public String getCartCount() {
    return preferenceUtils.get(Constants.CART_COUNT, "");
  }

  @Override
  public String getPromptedVersion() {
    return dataUtils.readObject("version");
  }

  @Override
  public void savePromptedVersion(String version) {
    dataUtils.writeObject("version",version);
  }

  @Override
  public UserDetailResponse getUserDetail() {
    return new Gson().fromJson(dataUtils.readObject("user_details"), UserDetailResponse.class);
  }

  @Override
  public void saveUserDetail(UserDetailResponse response) {
    dataUtils.writeObject("user_details",response);
  }

  @Override
  public CategoryResponse getCategories() {
    return new Gson().fromJson(dataUtils.readObject("Categories"), CategoryResponse.class);
  }

  @Override
  public void saveUserAuthToken(String token) {
    String separated = token.substring(1, token.length() - 1);
    Timber.e(separated);
    preferenceUtils.save("userToken", separated);
  }

  @Override
  public String getUserAuthToken() {
    return preferenceUtils.get("userToken", "");
  }


  @Override
  public List<Nationality> getNationality() {
    return new Gson().fromJson(dataUtils.readObject("nationality"),
            new TypeToken<List<Nationality>>() {
            }.getType());
  }

  @Override
  public void saveNationality(List<Nationality> listNationality) {
    dataUtils.writeObject("nationality",listNationality);
  }

  @Override
  public List<CountryCode> getCountryCodes() {
    return new Gson().fromJson(dataUtils.readObject("country_code"),
            new TypeToken<List<CountryCode>>() {
            }.getType());
  }

  @Override
  public void saveCountryCodes(List<CountryCode> listCountryCode) {
    dataUtils.writeObject("country_code",listCountryCode);

  }

  @Override
  public void savePhoneCodes(PhoneCode listPhoneCode) {
    dataUtils.writeObject("phone_code",listPhoneCode);

  }

  @Override
  public PhoneCode getPhoneCodes() {
    return new Gson().fromJson(dataUtils.readObject("phone_code"),PhoneCode.class);
  }

  @Override
  public void saveCategories(CategoryResponse response) {
    dataUtils.writeObject("Categories", response);
  }

  @Override
  public void saveRatingOption(RatingOption ratingOption) {
    dataUtils.writeObject("RatingOption", ratingOption);
  }

  @Override
  public RatingOption getRatingOption() {
    return new Gson().fromJson(dataUtils.readObject("RatingOption"), RatingOption.class);
  }

  @Override
  public void saveBackgroungLogin(List<BackgroundLogin> reponse) {
    dataUtils.writeObject("background_image",reponse);
  }

  @Override
  public List<BackgroundLogin> getBackgroundLogin() {
    return new Gson().fromJson(dataUtils.readObject("background_image"),
            new TypeToken<List<BackgroundLogin>>(){}
            .getType());
  }

  @Override
  public void saveTravelFlexBaseUrl(String baseUrl) {
    preferenceUtils.save("travflex_base_url", baseUrl);

  }


  @Override
  public void saveTravelToggle(Boolean isTravFlex) {
    preferenceUtils.save("isTravFlex", isTravFlex);
  }

  @Override
  public Boolean getTravelToggle() {
    return preferenceUtils.get("isTravFlex",false);
  }

  @Override
  public void saveOneSignalConfig(OneSignalConfig oneSignalConfig) {
    dataUtils.writeObject("ONESIGNAL_CONFIG",oneSignalConfig);
  }


}
