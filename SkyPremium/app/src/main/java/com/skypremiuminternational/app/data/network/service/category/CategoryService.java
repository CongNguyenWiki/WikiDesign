package com.skypremiuminternational.app.data.network.service.category;

import com.skypremiuminternational.app.data.network.URL;
import com.skypremiuminternational.app.domain.models.category.CategoryResponse;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import rx.Observable;

public interface CategoryService {

  @Headers("Content-Type: application/json")
  @GET(URL.GET_CATEGORIES)
  Observable<CategoryResponse> getCategories(@Header("Authorization") String auth);

}
