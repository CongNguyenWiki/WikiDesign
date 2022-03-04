
package com.skypremiuminternational.app.domain.interactor.my_reservation;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.reservation.ReservationTimeResponse;


import javax.inject.Inject;

import rx.Observable;

public class GetReservationTime extends UseCase<ReservationTimeResponse, GetReservationTime.Params> {

  @Inject
  protected GetReservationTime(DataManager dataManager, ThreadExecutor subscriberThread,
                               PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<ReservationTimeResponse> provideObservable(Params params) {
    return getDataManager().getReservationTime(params.path,params.date,
        params.restaurantId,params.partnerCode,params.partnerAuthCode);
  }

  public static class Params {
    String date;
    String path;
    String restaurantId;
    String partnerCode;
    String partnerAuthCode;

    public Params(String path, String date, String restaurantId,
                  String partnerCode, String partnerAuthCode) {
      this.path = path;
      this.date = date;
      this.restaurantId = restaurantId;
      this.partnerCode = partnerCode;
      this.partnerAuthCode = partnerAuthCode;
    }
  }

}
