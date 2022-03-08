package com.skypremiuminternational.app.data.network.service.cart;

import com.skypremiuminternational.app.data.network.URL;
import com.skypremiuminternational.app.domain.models.cart.AddCartItemCountRequest;
import com.skypremiuminternational.app.domain.models.cart.CartDetailItem;
import com.skypremiuminternational.app.domain.models.cart.CartDetailResponse;
import com.skypremiuminternational.app.domain.models.cart.CheckLimitResponse;


import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface CartServices {

  @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
  @GET(URL.GET_INFO_SHOPPING_CART)
  Observable<CartDetailResponse> getInfoShoppingCart(@Header("Authorization") String token);


  @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
  @POST(URL.CART)
  Observable<Object> createCart(@Header("Authorization") String auth);

  @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
  @POST(URL.CART_ITEMS)
  Observable<AddCartItemCountRequest.Cart> addToCart(@Header("Authorization") String auth,
                                                     @Body AddCartItemCountRequest addCartItemCountRequest);

  @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
  @GET(URL.CART_DETAIL)
  Observable<CartDetailResponse> getCartDetail(@Header("Authorization") String token);

  @Headers({"Cache-Control: no-cache"})
  @GET(URL.CHECK_CART_LIMIT)
  Observable<CheckLimitResponse> checkIfCartHasReachedLimitBuyNow(
      @Header("Authorization") String userToken,
      @Query("buynow") String isBuyNow);

  @Headers({"Cache-Control: no-cache"})
  @GET(URL.CHECK_CART_LIMIT)
  Observable<CheckLimitResponse> checkIfCartHasReachedLimit(
      @Header("Authorization") String userToken,
      @Query("is_checkout") String isBuyNow);

  @Headers({"Content-Type: application/json", "Cache-Control: no-cache"})
  @GET(URL.CART_ITEMS)
  Observable<List<CartDetailItem>> getCartItems(@Header("Authorization") String auth);









}
