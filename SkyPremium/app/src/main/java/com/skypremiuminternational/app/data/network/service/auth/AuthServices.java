package com.skypremiuminternational.app.data.network.service.auth;

import com.skypremiuminternational.app.data.network.URL;
import com.skypremiuminternational.app.data.network.request.ActivityLogRequest;
import com.skypremiuminternational.app.data.network.request.ForgotPasswordRequest;
import com.skypremiuminternational.app.data.network.request.PasswordHashRequest;
import com.skypremiuminternational.app.data.network.request.SignInRequest;
import com.skypremiuminternational.app.data.network.request.UpdatePasswordRequest;
import com.skypremiuminternational.app.data.network.request.UploadAvatarRequest;
import com.skypremiuminternational.app.domain.models.Version;
import com.skypremiuminternational.app.domain.models.user.UploadAvatarResponse;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import rx.Observable;

public interface AuthServices {


  @Headers("Content-Type: application/json")
  @GET(URL.GET_APP_VERSION)
  Observable<Version> getAppVersion(@Header("Authorization") String accessToken);


  @Headers("Content-Type: application/json")
  @POST(URL.SIGN_IN)
  Observable<ResponseBody> signIn(@Header("Authorization") String auth, @Body SignInRequest request);


  @Headers("Content-Type: application/json")
  @POST(URL.PUT_LOGIN)
  Observable<ResponseBody> putLogin(@Header("Authorization") String userToken, @Body ActivityLogRequest request);

  @Headers("Content-Type: application/json")
  @POST(URL.PUT_LOGOUT)
  Observable<ResponseBody> putLogout(@Header("Authorization") String userToken, @Body ActivityLogRequest request);

  @Headers("Content-Type: application/json")
  @PUT(URL.UPDATE_PASSWORD)
  Observable<ResponseBody> updatePassword(@Header("Authorization") String auth,
                                          @Body UpdatePasswordRequest updatePasswordRequest);

  @Headers("Content-Type: application/json")
  @PUT(URL.FORGOT_PASSWORD)
  Observable<ResponseBody> forgotPassword(@Body ForgotPasswordRequest forgotPasswordRequest);

  @Headers("Content-Type: application/json")
  @POST(URL.PASSWORD_HASH)
  Observable<ResponseBody> passwordHash(@Header("Authorization") String auth,
                                        @Body PasswordHashRequest passwordHashRequest);

  @Headers({"Cache-Control: no-cache", "Content-Type: application/json"})
  @PUT(URL.UPLOAD_AVATAR)
  Observable<UploadAvatarResponse> uploadAvatar(@Header("Authorization") String auth,
                                                @Body UploadAvatarRequest uploadAvatarRequest);

  @Headers("Content-Type: application/json")
  @GET(URL.CHECK_ADD_CARD_FIRST_TIME)
  Observable<Boolean> checkAddCardFirstTime(@Header("Authorization") String userToken);

  @Multipart
  @POST(URL.TRAVFLEX_TOKEN)
  Observable<ResponseBody> getTokenTravflex(@Header("Authorization") String auth,@PartMap() Map<String,String> params);

}
