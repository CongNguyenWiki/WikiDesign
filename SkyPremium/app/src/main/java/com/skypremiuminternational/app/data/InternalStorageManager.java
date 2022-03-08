package com.skypremiuminternational.app.data;


import com.skypremiuminternational.app.domain.models.category.CategoryResponse;
import com.skypremiuminternational.app.domain.models.comment_rating.RatingOption;
import com.skypremiuminternational.app.domain.models.config.BackgroundLogin;
import com.skypremiuminternational.app.domain.models.country_code.CountryCode;
import com.skypremiuminternational.app.domain.models.nationality.Nationality;
import com.skypremiuminternational.app.domain.models.phone_code.PhoneCode;
import com.skypremiuminternational.app.domain.models.system.OneSignalConfig;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;

import java.util.List;

public interface InternalStorageManager {

  void clearAuthToken();

  void saveCartCount(String count);

  String getCartCount();


  String getPromptedVersion();

  void savePromptedVersion(String version);

  void saveUserAuthToken(String token);

  String getUserAuthToken();

  public UserDetailResponse getUserDetail();

  public void saveUserDetail(UserDetailResponse response);

  CategoryResponse getCategories();

  public void saveNationality(List<Nationality> listNationality);

  public List<Nationality> getNationality();

  public void saveCountryCodes(List<CountryCode> listCountryCode);

  public List<CountryCode> getCountryCodes();

  public void savePhoneCodes(PhoneCode listPhoneCode);

  public PhoneCode getPhoneCodes();

  void saveCategories(CategoryResponse response);

  void saveRatingOption(RatingOption response);

  RatingOption getRatingOption();

  void saveBackgroungLogin(List<BackgroundLogin> reponse);

  List<BackgroundLogin> getBackgroundLogin();

  void saveTravelFlexBaseUrl(String baseUrl);

  void saveTravelToggle(Boolean isTravFlex);

  Boolean getTravelToggle();

  void saveOneSignalConfig(OneSignalConfig response);

}
