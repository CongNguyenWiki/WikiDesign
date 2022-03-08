package com.skypremiuminternational.app.data.network.service.product;

import com.skypremiuminternational.app.data.network.URL;
import com.skypremiuminternational.app.domain.models.favourite.FavouriteItem;
import com.skypremiuminternational.app.domain.models.products.DetailsItem;
import com.skypremiuminternational.app.domain.models.products.ProductListResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface ProductServices {


  @Headers("Content-Type: application/json")
  @GET(URL.PRODUCTS)
  Observable<ProductListResponse> getProducts(@Header("Authorization") String accessToken);


  @Headers({"Content-Type: application/json"})
  @GET(URL.DETAILS)
  Observable<DetailsItem> getDetails(@Header("Authorization") String auth,
                                     @Path("sku") String sku,
                                     @Query("customer_token") String customerToken);



//  @Headers("Content-Type: application/json")
//  @GET(URL.FAV_LIST)
//  Observable<List<FavouriteItem>> getFavourites(@Header("Authorization") String userToken);
}
