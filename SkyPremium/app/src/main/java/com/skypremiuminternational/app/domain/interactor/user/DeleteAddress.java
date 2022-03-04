package com.skypremiuminternational.app.domain.interactor.user;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;

import javax.inject.Inject;

import rx.Observable;



public class DeleteAddress extends UseCase<Boolean, DeleteAddress.Params> {

  @Inject
  protected DeleteAddress(DataManager dataManager,
                          ThreadExecutor subscriberThread,
                          PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<Boolean> provideObservable(Params params) {
    return getDataManager().deleteAddress(params);
  }

  public static class Params {
    public final Integer addressId;

    public Params(Integer addressId) {
      this.addressId = addressId;
    }
  }
}
