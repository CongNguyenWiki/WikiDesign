package com.skypremiuminternational.app.domain.interactor.my_reservation;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.reservation.RestaurantMessageResponse;

import javax.inject.Inject;

import rx.Observable;

public class GetRestaurantMsg extends UseCase<RestaurantMessageResponse, GetRestaurantMsg.Params> {

  @Inject
  protected GetRestaurantMsg(DataManager dataManager, ThreadExecutor subscriberThread,
                             PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<RestaurantMessageResponse> provideObservable(Params params) {
    return getDataManager().getRestaurantMsg(params.path, params.restaurantId);
  }



  public static class Params{
    String path;
    String restaurantId;


    public Params(String path, String restaurantId) {
      this.path = path;
      this.restaurantId = restaurantId;
    }
  }
}
