package com.skypremiuminternational.app.data.network.service.credit_card;

import com.skypremiuminternational.app.data.network.URL;
import com.skypremiuminternational.app.data.network.request.AddCreditCardRequest;
import com.skypremiuminternational.app.data.network.request.UpdateCreditCardRequest;
import com.skypremiuminternational.app.domain.models.creditCard.CardItem;
import com.skypremiuminternational.app.domain.models.creditCard.CreditCardResponse;
import com.skypremiuminternational.app.domain.models.creditCard.GetFormDataCreditCardResponse;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

public interface CreditCardService {

  @GET(URL.GET_CREDIT_CARD)
  Observable<CreditCardResponse> getListCreditCard(@Header("Authorization") String userToken);

  @GET(URL.GET_FORM_DATA_CREDIT_CARD)
  Observable<GetFormDataCreditCardResponse> getFormDataCreditCard(@Header("Authorization") String userToken);

  @Headers({"Cache-Control: no-cache", "Content-Type: application/json"})
  @POST(URL.ADD_CREDIT_CARD)
  Observable<CardItem> addCreditCard(@Header("Authorization") String userToken,
                                     @Body AddCreditCardRequest request);


  @Headers({"Cache-Control: no-cache", "Content-Type: application/json"})
  @PUT(URL.UPDATE_CREDIT_CARD)
  Observable<CardItem> updateCreditCard(@Header("Authorization") String userToken,
                                        @Path("cardId") String cardId,
                                        @Body UpdateCreditCardRequest request);


  @Headers({"Cache-Control: no-cache", "Content-Type: application/json"})
  @DELETE(URL.DELETE_CREDIT_CARD_)
  Observable<ResponseBody> deleteCreditCard(@Header("Authorization") String userToken,
                                            @Path("cardId") String cardId);

}
