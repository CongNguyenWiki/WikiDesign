package com.skypremiuminternational.app.data.network.service.config;

import com.skypremiuminternational.app.data.network.URL;
import com.skypremiuminternational.app.data.network.request.GiftPopUpRequest;
import com.skypremiuminternational.app.domain.models.config.BackgroundLogin;
import com.skypremiuminternational.app.domain.models.country_code.CountryCode;
import com.skypremiuminternational.app.domain.models.gift_redeemtion.GiftPopUpResponse;
import com.skypremiuminternational.app.domain.models.nationality.Nationality;
import com.skypremiuminternational.app.domain.models.phone_code.PhoneCode;
import com.skypremiuminternational.app.domain.models.system.OneSignalConfig;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface ConfigService {


  @GET(URL.GET_COUNTRY)
  Observable<List<CountryCode>> getCountryCodes();

  @GET(URL.GET_PHONE_CODE)
  Observable<PhoneCode> getPhoneCodes();

  @GET(URL.GET_NATIONALITIES)
  Observable<List<Nationality>> getNationalities();

  @GET(URL.GET_LOGIN_BG)
  Observable<List<BackgroundLogin>> getBackgroundLogin(@Header("Authorization") String auth);



  @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
  @GET(URL.ONESIGNAL_CONFIG)
  Observable<OneSignalConfig> getOneSignalConfig(@Header("Authorization") String auth);


  @Headers("Content-Type: application/json")
  @POST(URL.GET_GIFT_POPUP)
  Observable<List<GiftPopUpResponse>> showPopUp(@Header("Authorization") String auth, @Body GiftPopUpRequest giftPopUpRequest);


}
