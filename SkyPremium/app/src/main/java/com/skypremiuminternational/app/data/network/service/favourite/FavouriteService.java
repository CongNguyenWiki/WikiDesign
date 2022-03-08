package com.skypremiuminternational.app.data.network.service.favourite;

import com.skypremiuminternational.app.data.network.URL;
import com.skypremiuminternational.app.domain.models.favourite.FavouriteItem;
import com.skypremiuminternational.app.domain.models.favourite.FavouriteResponse;

import java.util.List;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by aeindraaung on 1/24/18.
 */

public interface FavouriteService {
  @Headers("Content-Type: application/json")
  @GET(URL.GET_ALL_FAVOURITE)
  Observable<List<FavouriteItem>> getAllFavourites(@Header("Authorization") String auth,
                                                   @Query("customer_token") String customerToken);

  @Headers("Content-Type: application/json")
  @GET(URL.GET_ALL_FAVOURITE_NO_ORDERS)
  Observable<List<FavouriteItem>> getAllFavouritesNotOrders(@Header("Authorization") String auth,
                                                   @Query("searchCriteria[filter_groups][0][filters][0][value]") String partnerType,
                                                   @Query("customer_token") String customerToken);

  @Headers("Content-Type: application/json")
  @GET(URL.GET_FAVOURITE_WITHOUT_CATEGORY)
  Observable<List<FavouriteItem>> getFavouriteWithoutCategoty(@Header("Authorization") String auth,
                                                                      @Query("searchCriteria[filter_groups][0][filters][0][value]") String partnerType,
                                                                      @Query("searchCriteria[sortOrders][0][field]") String sortingField,
                                                                      @Query("searchCriteria[sortOrders][0][direction]") String sortingDirection,
                                                                      @Query("customer_token") String customerToken);

  @Headers("Content-Type: application/json")
  @GET(URL.GET_FAVOURITE_WITH_CATEGORY)
  Observable<List<FavouriteItem>> getFavouriteWithCategory(@Header("Authorization") String auth,
                                                                   @Query("searchCriteria[filter_groups][1][filters][0][value]") String categoryId,
                                                                   @Query("searchCriteria[filter_groups][0][filters][0][value]") String partnerType,
                                                                   @Query("searchCriteria[sortOrders][0][field]") String sortingField,
                                                                   @Query("searchCriteria[sortOrders][0][direction]") String sortingDirection,
                                                                   @Query("customer_token") String customerToken);

  @Headers("Content-Type: application/json")
  @PUT(URL.ADD_TO_FAVOURITE)
  Observable<List<FavouriteItem>> addToFav(@Header("Authorization") String auth,
                                               @Path("product_id") String productId);

  @Headers("Content-Type: application/json")
  @DELETE(URL.REMOVE_FROM_FAVOURITE)
  Observable<List<FavouriteResponse>> removeFromFav(@Header("Authorization") String auth,
                                                    @Path("wishlist_item_id") String wishlistItemId);
}
