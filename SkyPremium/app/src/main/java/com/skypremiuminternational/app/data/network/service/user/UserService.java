package com.skypremiuminternational.app.data.network.service.user;


import com.skypremiuminternational.app.data.network.URL;
import com.skypremiuminternational.app.data.network.request.UpdateUserDeliveryRequest;
import com.skypremiuminternational.app.data.network.request.UpdateUserRequest;
import com.skypremiuminternational.app.domain.models.InviteFriendDescription;
import com.skypremiuminternational.app.domain.models.permissions.PermissionProfileItem;
import com.skypremiuminternational.app.domain.models.user.BillingAddressPayment;
import com.skypremiuminternational.app.domain.models.user.UserDetailResponse;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

public interface UserService {

  @Headers("Content-Type: application/json")
  @GET(URL.USER_DETAIL)
  Observable<UserDetailResponse> getUserDetail(@Header("Authorization") String auth);

  @Headers("Content-Type: application/json")
  @PUT(URL.USER_UPDATE)
  Observable<UserDetailResponse> getUserDetailDelivery(@Header("Authorization") String auth,
                                               @Path("member_id") String member_number, @Body UpdateUserDeliveryRequest updateUserRequest);


  @Headers("Content-Type: application/json")
  @PUT(URL.USER_UPDATE)
  Observable<UserDetailResponse> updateUserDetail(@Header("Authorization") String auth,
                                               @Path("member_id") Integer member_Id, @Body UpdateUserRequest updateUserRequest);

  @Headers("Content-Type: application/json")
  @GET(URL.GET_PERMISSION_PROFILE)
  Observable<List<PermissionProfileItem>> getPermissionProfile(@Header("Authorization") String userToken);

  @Headers("Content-Type: application/json")
  @GET(URL.GET_INVITE_FRIEND_DESCRIPTION)
  Observable<InviteFriendDescription> getInviteDescription(
      @Header("Authorization") String auth);


  @Headers({"Cache-Control: no-cache", "Content-Type: application/json"})
  @PUT(URL.BILLING_ADDRESS_PAYMENT)
  Observable<BillingAddressPayment.BillingAddressPaymentRespond> requestBillingAddressPayment(
      @Header("Authorization") String userToken, @Body RequestBody params);

  @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
  @DELETE(URL.DELETE_ADDRESS)
  Observable<Boolean> deleteAddress(
      @Header("Authorization") String auth, @Path("address_id") Integer addressId);





}
