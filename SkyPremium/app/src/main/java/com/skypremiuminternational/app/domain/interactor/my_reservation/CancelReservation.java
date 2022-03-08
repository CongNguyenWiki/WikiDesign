package com.skypremiuminternational.app.domain.interactor.my_reservation;


import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.reservation.CancelReservationResponse;

import javax.inject.Inject;

import rx.Observable;

public class CancelReservation extends UseCase<CancelReservationResponse, CancelReservation.Params> {

  @Inject
  protected CancelReservation(DataManager dataManager, ThreadExecutor subscriberThread,
                              PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<CancelReservationResponse> provideObservable(Params params) {
    return getDataManager().cancelReservation(params.reservationId, params.verificationKey);
  }

  public static class Params{
    String reservationId;
    String verificationKey;


    public Params(String reservationId, String verificationKey) {
      this.reservationId = reservationId;
      this.verificationKey = verificationKey;
    }
  }
}