package com.skypremiuminternational.app.domain.interactor.product;

import com.skypremiuminternational.app.domain.DataManager;
import com.skypremiuminternational.app.domain.executor.PostExecutionThread;
import com.skypremiuminternational.app.domain.executor.ThreadExecutor;
import com.skypremiuminternational.app.domain.interactor.base.UseCase;
import com.skypremiuminternational.app.domain.models.products.ProductListResponse;

import javax.inject.Inject;

import rx.Observable;

public class GetProducts extends UseCase<ProductListResponse,Void> {



  @Inject
  protected GetProducts(DataManager dataManager, ThreadExecutor subscriberThread, PostExecutionThread observerThread) {
    super(dataManager, subscriberThread, observerThread);
  }



  @Override
  public Observable<ProductListResponse> provideObservable(Void unused) {
    return getDataManager().getProducts();
  }
}
