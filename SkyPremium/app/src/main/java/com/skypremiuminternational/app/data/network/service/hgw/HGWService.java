package com.skypremiuminternational.app.data.network.service.hgw;

import com.skypremiuminternational.app.data.network.URL;
import com.skypremiuminternational.app.domain.models.reservation.CancelReservationResponse;
import com.skypremiuminternational.app.domain.models.reservation.ConfigItem;
import com.skypremiuminternational.app.domain.models.reservation.OutletItem;
import com.skypremiuminternational.app.domain.models.reservation.ReservationResultResponse;
import com.skypremiuminternational.app.domain.models.reservation.ReservationTimeResponse;
import com.skypremiuminternational.app.domain.models.reservation.ReserveDetailChefTableItem;
import com.skypremiuminternational.app.domain.models.reservation.ReserveHistoryItem;
import com.skypremiuminternational.app.domain.models.reservation.ReserveHistoryRespone;
import com.skypremiuminternational.app.domain.models.reservation.RestaurantMessageResponse;

import java.util.List;
import java.util.Map;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

public interface HGWService {

  @Headers("Content-Type: application/json")
  @GET(URL.GET_OUTLET_BY_PRODUCT)
  Observable<List<OutletItem>> getOutletByProductID(@Header("Authorization") String userToken,
                                                    @Path("product_id") String productID);



  @Headers("Content-Type: application/json")
  @GET(URL.GET_HISTORY)
  Observable<ReserveHistoryRespone> getHistoryAll(@Header("Authorization") String userToken,
                                                  @Query("searchCriteria[filter_groups][0][filters][0][field]") String membership_id,
                                                  @Query("searchCriteria[filter_groups][0][filters][0][value]") String memberNumber,
                                                  @Query("searchCriteria[filter_groups][0][filters][0][condition_type]") String condition_type,
                                                  @Query("searchCriteria[sortOrders][3][direction]") String director,
                                                  @Query("searchCriteria[sortOrders][3][field]") String reservation_date);


  @Headers("Content-Type: application/json")
  @GET(URL.GET_HISTORY)
  Observable<ReserveHistoryRespone> getHistoryFilter(@Header("Authorization") String userToken,
                                                     @Query("searchCriteria[filter_groups][0][filters][0][field]") String membership_id,
                                                     @Query("searchCriteria[filter_groups][0][filters][0][value]") String memberNumber,
                                                     @Query("searchCriteria[filter_groups][0][filters][0][condition_type]") String condition_type,

                                                     @Query("searchCriteria[sortOrders][2][direction]") String director,
                                                     @Query("searchCriteria[sortOrders][2][field]") String reservation_date,

                                                     @Query("searchCriteria[filter_groups][3][filters][0][field]") String sortField,
                                                     @Query("searchCriteria[filter_groups][3][filters][0][value]") String sortValue,
                                                     @Query("searchCriteria[filter_groups][3][filters][0][condition_type]") String sortCondition
                                                  /*,@Query("searchCriteria[current_page]") String current_page,
                                                  @Query("searchCriteria[page_size]") String page_size*/);


  @Headers("Content-Type: application/json")
  @GET(URL.GET_DETAIL_RESERVATION_BOOKING)
  Observable<ReserveHistoryItem> getDetailReservationBooking(@Header("Authorization") String userToken,
                                                             @Path("booking_id") String bookingId);

  @Headers("Content-Type: application/json")
  @DELETE(URL.CANCEL_RESERVATION)
  Observable<CancelReservationResponse> cancelReservation(@Header("Authorization") String userToken,
                                                          @Path("reservation_id") String reservationId,
                                                          @Path("verificationKey") String verificationKey);

  @Multipart
  @POST(URL.SEND_CREATE_RESERVATION)
  Observable<ReservationResultResponse> sendCreateReservation(@Header("Authorization") String userToken,
                                                              @PartMap() Map<String,String> params);

  @Multipart
  @POST(URL.SEND_EDIT_RESERVATION)
  Observable<ReservationResultResponse> sendEditReservation(@Header("Authorization") String userToken,
                                                            @PartMap() Map<String,String> params,
                                                            @Path("reservation_id") String reservationId,
                                                            @Path("verificationKey") String verificationKey);


  @Headers("Content-Type: application/json")
  @GET
  Observable<ReservationTimeResponse> getReservationTime(
      @Url String path
  );

  @Headers("Content-Type: application/json")
  @GET
  Observable<RestaurantMessageResponse> getRestaurantMsg(
      @Url String path
  );

  @Headers("Content-Type: application/json")
  @GET(URL.GET_HGW_CONFIG)
  Observable<List<ConfigItem>> getHGWConfig(@Header("Authorization") String userToken);

  @Headers("Content-Type: application/json")
  @GET(URL.GET_DETAIL_RESERVATION_BOOKING)
  Observable<ReserveDetailChefTableItem> getDetailReservationBookingChefTable(@Header("Authorization") String userToken,
                                                                              @Path("booking_id") String bookingId);



}