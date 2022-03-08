package com.skypremiuminternational.app.domain.interactor.user;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;

import javax.inject.Inject;

import rx.Observable;
import rx.Single;
import rx.SingleSubscriber;


public class ClearUserData extends UseCase<Boolean, Void> {

  @Inject
  protected ClearUserData(DataManager dataManager, ThreadExecutor subscriberThread,
                          PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<Boolean> provideObservable(Void aVoid) {
    return Single.create(new Single.OnSubscribe<Boolean>() {
      @Override
      public void call(SingleSubscriber<? super Boolean> singleSubscriber) {
        getDataManager().clearAuthToken();
        singleSubscriber.onSuccess(Boolean.TRUE);
      }
    }).toObservable();
  }
}
