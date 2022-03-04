package com.skypremiuminternational.app.data.network.service.my_membership;

import com.skypremiuminternational.app.data.network.URL;
import com.skypremiuminternational.app.domain.models.mymembership_statement.InforMembershipResponse;
import com.skypremiuminternational.app.domain.models.mymembership_statement.ListMemberShipResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

public interface MembershipService {

    @Headers("Content-Type: application/json")
    @GET(URL.GET_MEMBERSHIP_STATEMENT)
    Observable<List<ListMemberShipResponse>> getMembershipWithFilter(@Header("Authorization") String auth,
                                                                     @Query("filterGroup") String selectedCategory,
                                                                     @Query("sortOrder") String selectedSorting,
                                                                     @Query("memberNumber") String memberNumber,
                                                                     @Query("limit") String limit,
                                                                     @Query("currentPage") String currentPage);

    @Headers("Content-Type: application/json")
    @GET(URL.GET_MEMBERSHIP_INFO)
    Observable<List<InforMembershipResponse>> getMembershipInfo(@Header("Authorization") String auth);



}
