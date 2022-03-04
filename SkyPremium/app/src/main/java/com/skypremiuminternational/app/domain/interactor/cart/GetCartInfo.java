package com.skypremiuminternational.app.domain.interactor.cart;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.cart.CartDetailResponse;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;



public class GetCartInfo extends UseCase<CartDetailResponse,Void> {


  @Inject
  protected GetCartInfo(DataManager dataManager, ThreadExecutor subscriberThread, PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }

  @Override
  public Observable<CartDetailResponse> provideObservable(Void unused) {
    return getDataManager().getCartInfo();
  }
}
