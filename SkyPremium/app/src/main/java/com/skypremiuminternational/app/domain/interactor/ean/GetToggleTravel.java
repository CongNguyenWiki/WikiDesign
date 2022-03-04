package com.skypremiuminternational.app.domain.interactor.ean;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.booking.ToggleTravelResponse;

import javax.inject.Inject;

import rx.Observable;


public class GetToggleTravel extends UseCase<ToggleTravelResponse, Void> {


  @Inject
  protected GetToggleTravel(DataManager dataManager, ThreadExecutor subscriberThread,
                            PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<ToggleTravelResponse> provideObservable(final Void aVoid) {
    return getDataManager().getToggleTravel();
  }
}
