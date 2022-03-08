package com.skypremiuminternational.app.data.network.service.fag;

import com.skypremiuminternational.app.data.network.URL;
import com.skypremiuminternational.app.domain.models.faq.FaqResponse;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import rx.Observable;



public interface FaqService {

  @Headers("Content-Type: application/json")
  @GET(URL.GET_FAQ)
  Observable<FaqResponse> getFaqs(
      @Header("Authorization") String auth);
}
